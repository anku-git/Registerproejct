package com.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(min=10,max = 10,message = "contacts number should be 10 digit")
    private String contacts;
    @NotBlank(message = "contactType is required field")
    private String contactType;
    private String flag;
    private String status;
    @NotBlank(message = "email is required field")
    @Email(message = "email id should be valid format")
    private String email;
    private String activeDate;
    private String inActiveDate;
    @NotBlank(message = "dncType is required field")
    private  String dncType;
    @Column(name = "register_id")
    private Integer registerId;

}
