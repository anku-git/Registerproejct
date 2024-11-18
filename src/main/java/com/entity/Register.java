package com.entity;
import java.time.LocalDate;
import java.util.List;

import com.constant.Frequency;
import com.constant.Priority;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@Enumerated(EnumType.STRING)
	private Frequency frequency;
	@Enumerated(EnumType.STRING)
	private Priority priority;
	@NotBlank(message = "issue is required field")
	private String issue;
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="register_id")
	private List<Remarks> remarks;

}