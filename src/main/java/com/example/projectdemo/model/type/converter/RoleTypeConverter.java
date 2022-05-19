package com.example.projectdemo.model.type.converter;

import com.example.projectdemo.model.type.RoleType;

import javax.persistence.AttributeConverter;

public class RoleTypeConverter implements AttributeConverter<RoleType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(RoleType attribute) {
        if(attribute == null){
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public RoleType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return RoleType.fromValue(dbData);
    }
}
