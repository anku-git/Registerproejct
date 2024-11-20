package com.controller;
import com.entity.AddressDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
//		String frequency= String.valueOf(requestData.getFrequency());
//		String priority=String.valueOf(requestData.getPriority());
		Register register2 = registerServiceImp.save(register);
		return new ResponseEntity<Register>(register2, HttpStatus.OK);

	}
	@GetMapping("/get/{id}")
	public ResponseEntity<Register> getComlaint(@PathVariable Integer id) {
		Register register2 = registerServiceImp.getById(id);
		return new ResponseEntity<Register>(register2, HttpStatus.OK);

	}
	@GetMapping("/get/all")
	public ResponseEntity<Page<Register>> getAllComlaints(@RequestParam (defaultValue = "0")int page,
														  @RequestParam (defaultValue = "10")int size,
														  @RequestParam(defaultValue = "id") String sortBy,
														  @RequestParam(defaultValue = "true") Boolean sortDirection){
		Page<Register>register2=registerServiceImp.getAllRegister(page,size,sortBy,sortDirection);
return new ResponseEntity<>(register2,HttpStatus.OK);}
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
 @DeleteMapping("/delete/{id}")
	public void deleteRegisterComplaint(@PathVariable Integer id){
		registerServiceImp.deleteById(id);
 }

}
