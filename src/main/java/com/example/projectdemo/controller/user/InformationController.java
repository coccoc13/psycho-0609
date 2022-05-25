package com.example.projectdemo.controller.user;

import com.example.projectdemo.convertDTO.InformationConvertor;
import com.example.projectdemo.exception.ItemIsExisted;
import com.example.projectdemo.exception.ItemNotFound;
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
    public Object get() {
        List<InformationListDTO> list = service.getAllByStatus(InformationStatusType.ACCEPT)
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
        Information information = service.findById(id).orElseThrow(() -> new ItemNotFound("Information not found"));
        return new ResponseEntity<>(InformationConvertor.toDTO(information), HttpStatus.OK);
    }

    @PostMapping
    private Object save(@RequestBody InformationRequest request) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccountPrincipal userDetails = (AccountPrincipal) auth.getPrincipal();
        Category category = categoryService.findById(request.getCategoryId()).orElseThrow(Exception::new);
        Information information;
        checkUrl(category.getId(), request);
        information = new Information();
        information.setCreatedAt(LocalDateTime.now());
        information.setCategory(category);
        information.setAccount(new Account(userDetails.getId()));
        information.setStatus(InformationStatusType.WAITING);
        InformationConvertor.toEntity(information, request);
        service.save(information);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/contribution")
//    public Object getUserContribution(){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        AccountPrincipal userDetails = (AccountPrincipal) auth.getPrincipal();
//        List<Information> list = service.getAllByAccountId(userDetails.getId());
//        return new ResponseEntity<>(list.stream().map(InformationConvertor::toDTO).collect(Collectors.toList()), HttpStatus.OK);
//    }

//    @PostMapping("/delete/{id}")
//    private Object delete(@PathVariable Integer id) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        AccountPrincipal userDetails = (AccountPrincipal) auth.getPrincipal();
//        Information information = service.findById(id).orElseThrow(() -> new ItemNotFound("Information not found"));
//        if(!information.getAccount().getId().equals(userDetails.getId())){
//            throw new ItemNotFound("Information not found");
//        }
//        information.setDelFlag(DelFlag.DELETE);
//        service.save(information);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    private void checkUrl(Integer categoryId, InformationRequest request) {
        if (service.existsByTypeAndUrlIn(categoryId, request.getUrlIn())) {
            throw new ItemIsExisted("Url in is existed");
        }
        if (service.existsByTypeAndUrlOut(categoryId, request.getUrlOut())) {
            throw new ItemIsExisted("Url out is existed");
        }
    }

}
