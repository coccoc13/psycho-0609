package com.example.projectdemo.model.type.converter;

import com.example.projectdemo.model.type.InformationType;

import javax.persistence.AttributeConverter;

public class InformationTypeConverter implements AttributeConverter<InformationType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(InformationType attribute) {
        if(attribute == null){
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public InformationType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return InformationType.fromValue(dbData);
    }
}
