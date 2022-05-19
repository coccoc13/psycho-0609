package com.example.projectdemo.service.imp;

import com.example.projectdemo.model.entity.Information;
import com.example.projectdemo.model.type.InformationStatusType;
import com.example.projectdemo.model.type.InformationType;
import com.example.projectdemo.repository.AccountRepository;
import com.example.projectdemo.repository.InformationRepository;
import com.example.projectdemo.service.inter.IInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformationServiceImp implements IInformationService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private InformationRepository informationRepository;

    @Override
    public List<Information> getAllByStatus(InformationStatusType status) {
        return informationRepository.getAllByStatus(status);
    }

    @Override
    public Information save(Information information){
        return informationRepository.save(information);
    }

    @Override
    public Boolean existsByTypeAndUrlIn(Integer categoryId, String urlIn) {
        return informationRepository.existsByCategoryIdAndUrlIn(categoryId, urlIn).isPresent();
    }

    @Override
    public Boolean existsByTypeAndUrlOut(Integer categoryId, String urlOut) {
        return informationRepository.existsByCategoryIdAndUrlOut(categoryId, urlOut);
    }

    @Override
    public Boolean existsByTypeAndUrlInEdit(Integer categoryId, String urlIn, Integer id) {
        return informationRepository.existsByCategoryIdAndUrlInEdit(categoryId, urlIn, id).isPresent();
    }

    @Override
    public Boolean existsByTypeAndUrlOutEdit(Integer categoryId, String urlOut, Integer id) {
        return informationRepository.existsByCategoryIdAndUrlOutEdit(categoryId, urlOut, id).isPresent();
    }

    @Override
    public Optional<Information> findById(Integer id) {
        return informationRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        informationRepository.deleteById(id);
    }
}
