package com.controller;

import com.entity.Register;
import com.entity.Remarks;
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
    public ResponseEntity<Remarks> save(@PathVariable Integer registerId, @RequestBody  Remarks remarks){
        return new ResponseEntity<>(remarksServiceImp.add(registerId,remarks), HttpStatus.OK);

    }
}
