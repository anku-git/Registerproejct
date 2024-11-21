package com.controller;

import com.entity.ContactsDetails;
import com.service.ContactDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactDetailsController {
    @Autowired
    private ContactDetailsServiceImp contactDetailsServiceImp;
    @PostMapping("/save/{id}")
    public ResponseEntity<ContactsDetails> save(@PathVariable Integer id,
                                                @Valid @RequestBody ContactsDetails contactsDetails){
        return new ResponseEntity<>(contactDetailsServiceImp.add(id,contactsDetails), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ContactsDetails> getDetailsById(@PathVariable Integer id){
        return new ResponseEntity<>(contactDetailsServiceImp.get(id),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ContactsDetails> updateAddress(@PathVariable Integer id, @RequestBody ContactsDetails contactsDetails){
        return  new ResponseEntity<>(contactDetailsServiceImp.update(id,contactsDetails),HttpStatus.OK);
    }
}
