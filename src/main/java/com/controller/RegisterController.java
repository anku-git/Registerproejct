package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.entity.Register;
import com.service.RegisterServiceImp;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Register/complaint")
public class RegisterController {
	@Autowired
	private RegisterServiceImp registerServiceImp;

	@PostMapping("/save")
	public ResponseEntity<Register> addComlaint(@Valid @RequestBody Register register) {
		Register register2 = registerServiceImp.save(register);
		return new ResponseEntity<Register>(register2, HttpStatus.OK);

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Register> getComlaint(@PathVariable Integer id) {
		Register register2 = registerServiceImp.getById(id);
		return new ResponseEntity<Register>(register2, HttpStatus.OK);

	}

	@GetMapping("/get/all/{size}")
	public ResponseEntity<Page<Register>> getAllComlaints(@PathVariable Integer size) {
		Page<Register> register2 = registerServiceImp.getAllRegister(size);
		return new ResponseEntity<Page<Register>>(register2, HttpStatus.OK);
	}

	@GetMapping("/get/by/{ticketNo}")
	public ResponseEntity<Register> getComlaint(@PathVariable String ticketNo) {
		Register register2 = registerServiceImp.getByTicketNo(ticketNo);
		return new ResponseEntity<Register>(register2, HttpStatus.OK);

	}

	@PutMapping("/close/{id}")
	public ResponseEntity<String> closeComplaint(@PathVariable Integer id) {
		registerServiceImp.closeComplaint(id);
		return new ResponseEntity<String>("complain staus update successfully", HttpStatus.OK);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Register> updateRegisterComplain(@PathVariable Integer id,@RequestBody Register register) {
		return new ResponseEntity<>(registerServiceImp.updateComplain(id,register), HttpStatus.OK);

	}

}
