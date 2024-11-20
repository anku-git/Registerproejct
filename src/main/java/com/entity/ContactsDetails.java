package com.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Entity
@Data
public class ContactsDetails {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "mdp is required field")
    private String mdp;
    @NotBlank(message = "contacts is required field")
    private String contacts;
    @NotBlank(message = "contactType is required field")
    private String contactType;
    private Boolean flag;
    private String status;
    private String activeDate;
    private String inActiveDate;
    @NotBlank(message = "dncType is required field")
    private  String dncType;
    @Column(name = "register_id")
    private Integer registerId;

}
