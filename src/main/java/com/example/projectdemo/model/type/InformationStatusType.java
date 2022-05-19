package com.example.projectdemo.model.type;

import java.util.stream.Stream;

public enum InformationStatusType {
    ENABLE(1), DISABLE(2);

    Integer value;

    InformationStatusType(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }

    public static InformationStatusType fromValue(Integer value){
        return Stream.of(InformationStatusType.values()).filter(c -> c.getValue().equals(value)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
