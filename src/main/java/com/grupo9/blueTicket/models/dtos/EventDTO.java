package com.grupo9.blueTicket.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EventDTO {
    @NotEmpty
    private String id;

    @NotEmpty
    private String id_category;

    @NotEmpty
    private List<UUID> id_locality; //Se le envía una lista de localidades

    @NotEmpty
    private String id_user;

    @NotEmpty
    private String title;

    @NotEmpty
    private String image1;

    @NotEmpty
    private String image2;

    @NotEmpty
    private String date;

    @NotEmpty
    private String hour;

    @NotEmpty
    private String duration;

    @NotEmpty
    private String sponsor;

    @NotEmpty
    private String involved;
    
}
