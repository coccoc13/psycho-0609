package com.example.projectdemo.model.type;


import java.util.stream.Stream;

public enum InformationType {

    LINE(1, "Line"), GOOGLE(2, "Google");

    private Integer value;
    private String label;

    InformationType(Integer value, String label){
        this.value = value;
        this.label = label;
    }

    public Integer getValue(){
        return value;
    }

    public String getLabel(){
        return label;
    }

    public static InformationType fromValue(Integer value){
        return Stream.of(InformationType.values()).filter(c -> c.getValue().equals(value)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
