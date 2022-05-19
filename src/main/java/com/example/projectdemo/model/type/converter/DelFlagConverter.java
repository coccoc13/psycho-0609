package com.example.projectdemo.model.type.converter;

import com.example.projectdemo.model.type.DelFlag;
import com.example.projectdemo.model.type.InformationStatusType;

import javax.persistence.AttributeConverter;

public class DelFlagConverter implements AttributeConverter<DelFlag, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DelFlag attribute) {
        if(attribute == null){
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public DelFlag convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return DelFlag.fromValue(dbData);
    }
}
