package com.example.projectdemo.model.type;

import java.util.stream.Stream;

public enum InformationStatusType {
    ACCEPT(1), REJECT(2), WAITING(3);

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
