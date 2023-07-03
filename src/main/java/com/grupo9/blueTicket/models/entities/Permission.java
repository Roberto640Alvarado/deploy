package com.grupo9.blueTicket.models.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = "rolePermission")
@Entity
@Table(name = "permission")
public class Permission {
    
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "permission")
    private String permission;
    
    //Creo que faltaba este
    @OneToMany(mappedBy = "id_permission", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Role_permission> rolePermission;
}