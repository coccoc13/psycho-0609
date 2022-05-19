package com.example.projectdemo.convertDTO;

import com.example.projectdemo.model.dto.InformationListDTO;
import com.example.projectdemo.model.entity.Information;
import com.example.projectdemo.model.request.InformationRequest;
import com.example.projectdemo.model.response.AccountResponse;
import com.example.projectdemo.model.type.DelFlag;

public class InformationConvertor {

    public static InformationListDTO toDTO(Information information){
        return new InformationListDTO(
                information.getId(),
                information.getNote(),
                information.getClientId(),
                information.getClientSecret(),
                information.getUrlIn(),
                information.getUrlOut(),
                CategoryConvertor.toDTO(information.getCategory()),
                information.getStatus().getValue(),
                information.getCreatedAt(),
                information.getUpdateAt(),
                new AccountResponse(information.getAccount().getId(), information.getAccount().getName(), information.getAccount().getRoleType().getValue())
        );
    }

    public static void toEntity(Information information, InformationRequest request){
        information.setClientId(request.getClientId());
        information.setClientSecret(request.getClientSecret());
        information.setNote(request.getNote());
        information.setUrlIn(request.getUrlIn());
        information.setUrlOut(request.getUrlOut());
        information.setDelFlag(DelFlag.NOT_DELETE);
    }

}
