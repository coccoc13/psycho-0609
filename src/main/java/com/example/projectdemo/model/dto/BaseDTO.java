package com.example.projectdemo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDTO {

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
