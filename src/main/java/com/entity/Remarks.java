package com.entity;

import com.constant.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@Entity
public class Remarks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "remarks is required field")
    private String remarks;
    @DateTimeFormat(pattern = "DD/MM/YYYY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "register_id")
    private Integer registerId;
    @PrePersist
    protected  void onCreate(){
        this.date=new Date();
    }
}
