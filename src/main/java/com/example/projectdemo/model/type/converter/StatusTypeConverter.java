package com.example.projectdemo.model.type.converter;

import com.example.projectdemo.model.type.InformationStatusType;

import javax.persistence.AttributeConverter;

public class StatusTypeConverter implements AttributeConverter<InformationStatusType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(InformationStatusType attribute) {
        if(attribute == null){
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public InformationStatusType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return InformationStatusType.fromValue(dbData);
    }
}
