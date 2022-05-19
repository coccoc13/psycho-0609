package com.example.projectdemo.model.type;

import java.util.stream.Stream;

public enum DelFlag {
    DELETE(0), NOT_DELETE(1);

    Integer value;

    DelFlag(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }

    public static DelFlag fromValue(Integer value){
        return Stream.of(DelFlag.values()).filter(c -> c.getValue().equals(value)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
