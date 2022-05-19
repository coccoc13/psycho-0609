package com.example.projectdemo.controller.user;

import com.example.projectdemo.convertDTO.InformationConvertor;
import com.example.projectdemo.exception.InformationExisted;
import com.example.projectdemo.exception.InformationNotFound;
import com.example.projectdemo.model.dto.InformationListDTO;
import com.example.projectdemo.model.entity.Account;
import com.example.projectdemo.model.entity.Category;
import com.example.projectdemo.model.entity.Information;
import com.example.projectdemo.model.request.InformationRequest;
import com.example.projectdemo.model.type.InformationStatusType;
import com.example.projectdemo.model.userdetail.AccountPrincipal;
import com.example.projectdemo.service.inter.ICategoryService;
import com.example.projectdemo.service.inter.IInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user/information")
public class InformationController {

    @Autowired
    private IInformationService service;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public Object get(){
        List<InformationListDTO> list = service.getAllByStatus(InformationStatusType.ENABLE)
                .stream()
                .map(t -> {
                    t.setAccount(t.getAccount());
                    t.setCategory(t.getCategory());
                    return InformationConvertor.toDTO(t);
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public Object getOne(@PathVariable Integer id) {
        Information information = service.findById(id).orElseThrow(() -> new InformationNotFound("Information not found"));
        return new ResponseEntity<>(InformationConvertor.toDTO(information), HttpStatus.OK);
    }

    @PostMapping
    private Object save(@RequestBody InformationRequest request) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccountPrincipal userDetails = (AccountPrincipal) auth.getPrincipal();
        Category category = categoryService.findById(request.getCategoryId()).orElseThrow(Exception::new);
        Information information;
        if(request.getId() != null){
            information = service.findById(request.getId()).orElseThrow(() -> new InformationNotFound("Information not found"));
            checkUrlEdit(category.getId(), information, request);
            information.setUpdateAt(LocalDateTime.now());
        }else{
            checkUrl(category.getId(), request);
            information = new Information();
            information.setCreatedAt(LocalDateTime.now());
        }
        information.setCategory(category);
        information.setAccount(new Account(userDetails.getId()));
        information.setStatus(InformationStatusType.DISABLE);
        InformationConvertor.toEntity(information, request);
        service.save(information);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void checkUrl(Integer categoryId, InformationRequest request) throws Exception {
        if(service.existsByTypeAndUrlIn(categoryId, request.getUrlIn())){
            throw new InformationExisted("Url in is existed");
        }
        if(service.existsByTypeAndUrlOut(categoryId, request.getUrlOut())){
            throw new InformationExisted("Url out is existed");
        }
    }

    private void checkUrlEdit(Integer categoryId, Information information, InformationRequest request) throws Exception {
        if(information.getCategory().getId().equals(categoryId)){
            if(service.existsByTypeAndUrlInEdit(categoryId, request.getUrlIn(), request.getId())){
                throw new InformationExisted("Url in is existed");
            }
            if(service.existsByTypeAndUrlOutEdit(categoryId, request.getUrlOut(), request.getId())){
                throw new InformationExisted("Url out is existed");
            }
        }else{
            checkUrl(categoryId, request);
        }
    }
}
