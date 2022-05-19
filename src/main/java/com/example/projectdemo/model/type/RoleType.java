package com.example.projectdemo.model.type;

import java.util.stream.Stream;

public enum RoleType {

    ADMIN(1), USER(2);

    private Integer value;

    private RoleType(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }

    public static RoleType fromValue(Integer value){
        return Stream.of(RoleType.values()).filter(c -> c.getValue().equals(value)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
