package com.example.projectdemo.model.entity;

import com.example.projectdemo.model.type.RoleType;
import com.example.projectdemo.model.type.converter.RoleTypeConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @Convert(converter = RoleTypeConverter.class)
    private RoleType roleType;

    private String name;

    public Account(Integer id) {
        this.id = id;
    }
}
