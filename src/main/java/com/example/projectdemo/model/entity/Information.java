package com.example.projectdemo.model.entity;

import com.example.projectdemo.model.type.DelFlag;
import com.example.projectdemo.model.type.InformationStatusType;
import com.example.projectdemo.model.type.converter.StatusTypeConverter;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Information extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100000)
    private String note;

    private String clientId;

    private String clientSecret;

    private String urlIn;

    private String urlOut;

    private DelFlag delFlag;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Convert(converter = StatusTypeConverter.class)
    private InformationStatusType status;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


}
