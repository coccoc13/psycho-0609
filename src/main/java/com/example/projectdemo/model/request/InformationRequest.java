package com.example.projectdemo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationRequest {

    private Integer id;
    private String note;
    private String clientId;
    private String clientSecret;
    private String urlIn;
    private String urlOut;
    private Integer categoryId;
    private Integer status;

}
