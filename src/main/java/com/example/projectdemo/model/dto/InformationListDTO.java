package com.example.projectdemo.model.dto;

import com.example.projectdemo.model.response.AccountResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationListDTO {
    private Integer id;

    private String note;

    private String clientId;

    private String clientSecret;

    private String urlIn;

    private String urlOut;

    private CategoryListDTO category;

    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private AccountResponse account;
}
