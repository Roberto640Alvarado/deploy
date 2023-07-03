package com.grupo9.blueTicket.models.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class CategoryDTO {
    private UUID id;
    private String name;
    private String description;
}
