package com.grupo9.blueTicket.models.dtos;


import java.sql.Date;
import java.sql.Time;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SaveEventDTO {

	@NotEmpty(message = "Title is required")
	private String title;
	
	@NotEmpty(message = "Involved is required")
	private String involved;
	
	@NotEmpty(message = "Image 1 is required")
	private String image1;
	
	@NotEmpty(message = "Image 2 is required")
	private String image2;
	
	//@NotEmpty(message = "Date is required")
	private Date date;
	
	//@NotEmpty(message = "Hour is required")
	private Time hour;
	
	@NotEmpty(message = "Duration is required")
	private String duration;
	
	//@NotEmpty(message = "Category is required")
	private int category;
	
	@NotEmpty(message = "Sponsor is required")
	private String sponsor;
	
	
	
	
	
}
