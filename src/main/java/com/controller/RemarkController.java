package com.controller;

import com.entity.RemarksManagement;
import com.service.RemarksServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RemarkController {
    @Autowired
    private RemarksServiceImp remarksServiceImp;
    @PostMapping("/add/{registerId}")
    public ResponseEntity<RemarksManagement> save(@PathVariable Integer registerId, @RequestBody RemarksManagement remarks){
        return new ResponseEntity<>(remarksServiceImp.add(registerId,remarks), HttpStatus.OK);

    }
    @GetMapping("/get/{id}")
    public ResponseEntity<RemarksManagement> getRemarks(@PathVariable Integer id){
        return  new ResponseEntity<>(remarksServiceImp.getById(id),HttpStatus.OK);
    }
}
