package com.example.projectdemo.model.entity;

import com.example.projectdemo.model.type.DelFlag;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Category extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private DelFlag delFlag;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
