package com.example.projectdemo.controller.admin;

import com.example.projectdemo.convertDTO.InformationConvertor;
import com.example.projectdemo.exception.ItemIsExisted;
import com.example.projectdemo.exception.ItemNotFound;
import com.example.projectdemo.exception.InputRequireEx;
import com.example.projectdemo.model.dto.InformationListDTO;
import com.example.projectdemo.model.entity.Account;
import com.example.projectdemo.model.entity.Category;
import com.example.projectdemo.model.entity.Information;
import com.example.projectdemo.model.request.InformationRequest;
import com.example.projectdemo.model.type.DelFlag;
import com.example.projectdemo.model.type.InformationStatusType;
import com.example.projectdemo.model.userdetail.AccountPrincipal;
import com.example.projectdemo.service.inter.ICategoryService;
import com.example.projectdemo.service.inter.IInformationService;
import org.apache.logging.log4j.util.Strings;
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
@RequestMapping("api/admin/information")
public class InformationAdminController {

    @Autowired
    private IInformationService service;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public Object get(@RequestParam(required = false) Integer status){
        InformationStatusType inforStatus;
        if(status != null){
            inforStatus = InformationStatusType.fromValue(status);
        }else {
            inforStatus = InformationStatusType.ACCEPT;
        }
        List<InformationListDTO> list = service.getAllByStatus(inforStatus)
                .stream()
                .map(InformationConvertor::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public Object getOne(@PathVariable Integer id) {
        Information information = service.findById(id).orElseThrow(() -> new ItemNotFound("Information not found"));
        return new ResponseEntity<>(InformationConvertor.toDTO(information), HttpStatus.OK);
    }

    @PostMapping
    public Object save(@RequestBody InformationRequest request) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccountPrincipal userDetails = (AccountPrincipal) auth.getPrincipal();
        validateForm(request);
        Category category = categoryService.findById(request.getCategoryId()).orElseThrow(Exception::new);
        Information information;
        if(request.getId() == null){
            checkUrl(category.getId(), request);
            information = new Information();
            information.setCreatedAt(LocalDateTime.now());
        }else {
            information = service.findById(request.getId()).orElseThrow(() -> new ItemNotFound("Information not found"));
            checkUrlEdit(category.getId(), information, request);
            information.setUpdateAt(LocalDateTime.now());
        }
        information.setCategory(category);
        information.setAccount(new Account(userDetails.getId()));
        information.setStatus(InformationStatusType.ACCEPT);
        InformationConvertor.toEntity(information, request);
        service.save(information);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}/active")
    public Object activeStatus(@PathVariable Integer id) throws Exception {
        Information information = service.findById(id).orElseThrow(() -> new ItemNotFound("Information not found"));
        information.setStatus(InformationStatusType.ACCEPT);
        service.save(information);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}/disable")
    public Object disableStatus(@PathVariable Integer id) {
        Information information = service.findById(id).orElseThrow(() -> new ItemNotFound("Information not found"));
        information.setStatus(InformationStatusType.REJECT);
        service.save(information);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    private Object delete(@PathVariable Integer id) {
        Information information = service.findById(id).orElseThrow(() -> new ItemNotFound("Information not found"));
        information.setDelFlag(DelFlag.DELETE);
        service.save(information);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void checkUrl(Integer categoryId, InformationRequest request) {
        if(service.existsByTypeAndUrlIn(categoryId, request.getUrlIn())){
            throw new ItemIsExisted("Url In is existed");
        }
        if(service.existsByTypeAndUrlOut(categoryId, request.getUrlOut())){
            throw new ItemIsExisted("Url Out out is existed");
        }
    }

    private void checkUrlEdit(Integer categoryId, Information information, InformationRequest request) throws Exception {
        if(information.getCategory().getId().equals(categoryId)){
            if(service.existsByTypeAndUrlInEdit(categoryId, request.getUrlIn(), request.getId())){
                throw new ItemIsExisted("Url in is existed");
            }
            if(service.existsByTypeAndUrlOutEdit(categoryId, request.getUrlOut(), request.getId())){
                throw new ItemIsExisted("Url out is existed");
            }
        }else{
            checkUrl(categoryId, request);
        }
    }

    private void validateForm(InformationRequest request){
        if(request.getCategoryId() == null){
            throw new InputRequireEx("Category is require");
        }
        if(Strings.isBlank(request.getClientId())){
            throw new InputRequireEx("Client ID is require");
        }
        if(Strings.isBlank(request.getClientSecret())){
            throw new InputRequireEx("Client Secret is require");
        }
        if(Strings.isBlank(request.getUrlIn())){
            throw new InputRequireEx("URL In is require");
        }
        if(Strings.isBlank(request.getUrlOut())){
            throw new InputRequireEx("URL Out is require");
        }


    }


}
