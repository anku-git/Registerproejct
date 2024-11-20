package com.entity;

import com.exception.GlobalExceptionHandler;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class AddressDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "AddressType is required field")
    private String addressType;
    @NotBlank(message = "identification is required field")
    private String identification;
    @NotBlank(message = "address is required field")
    private String address;
    @NotBlank(message = "country is required field")
    private String country;
    @NotBlank(message = "state is required field")
    private String state;
    @NotBlank(message = "pinCode is required field")
    private String pinCode;
    @NotBlank(message = "gstNo  is required field")
    private String gstNo;
    @NotBlank(message = "Action is required field")
    private String action;
    @NotBlank(message = "panNumber is required field")
    private String panNumber;
    @NotBlank(message = "aadhaarNumber is required field")
    private String aadhaarNumber;
    @NotBlank(message = "status is required field")
    private String status;
   @Column(name = "register_id")
   private Integer registerId;

}
