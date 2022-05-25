package com.example.projectdemo.service.inter;

import com.example.projectdemo.model.entity.Information;
import com.example.projectdemo.model.type.InformationStatusType;

import java.util.List;
import java.util.Optional;

public interface IInformationService {

    List<Information> getAllByStatus(InformationStatusType status);
    Information save(Information information);
    Boolean existsByTypeAndUrlIn(Integer categoryId, String urlIn);
    Boolean existsByTypeAndUrlOut(Integer categoryId, String urlOut);
    Boolean existsByTypeAndUrlInEdit(Integer categoryId, String urlIn, Integer id);
    Boolean existsByTypeAndUrlOutEdit(Integer categoryId, String urlOut, Integer id);
    Optional<Information> findById(Integer id);
    Integer countByCategoryId(Integer id);
    List<Information> getAllByAccountId(Integer id);
    void delete(Integer id);
}
