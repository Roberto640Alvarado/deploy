package com.grupo9.blueTicket.models.dtos;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SaveUserRoleDTO {
    @NotEmpty
	private UUID id_user;
	@NotEmpty
	private UUID id_role;
    @NotEmpty
	private String status;

    
}
