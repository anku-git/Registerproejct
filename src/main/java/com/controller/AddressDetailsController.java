package com.controller;

import com.entity.AddressDetails;
import com.entity.ContactsDetails;
import com.service.AddressDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressDetailsController {
    @Autowired
    private AddressDetailsServiceImp addressDetailsServiceImp;
    @PostMapping("/save/{id}")
    public ResponseEntity<AddressDetails> saveAddressDetails(@PathVariable Integer id,@Valid @RequestBody AddressDetails addressDetails){
        return new ResponseEntity<>(addressDetailsServiceImp.add(id,addressDetails), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<AddressDetails> getDetailsById(@PathVariable Integer id){
        return new ResponseEntity<>(addressDetailsServiceImp.get(id),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<AddressDetails> updateAddress(@PathVariable Integer id,@Valid @RequestBody AddressDetails addressDetails){
        return  new ResponseEntity<>(addressDetailsServiceImp.update(id,addressDetails),HttpStatus.OK);
    }
}
