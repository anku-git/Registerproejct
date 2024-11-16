package com.entity;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Entity
@Data
public class Register {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ticketNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate date;
	@NotBlank(message = "project name is required field")
	private String projectName;
	@NotBlank(message = "moduleName is required field")
	private String modelName;
	@NotBlank(message = "subModule is required field")
	private String subModule;
	@NotBlank(message = "frequency is required field")
	private String frequency;
	@NotBlank(message = "priority is required field")
	private String priority;
	@NotBlank(message = "issue is required field")
	private String issue;
	@NotBlank(message = "status is required field")
	private String status;
	
	

}