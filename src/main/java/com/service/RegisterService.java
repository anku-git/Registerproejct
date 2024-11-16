package com.service;
import org.springframework.data.domain.Page;
import com.entity.Register;
public interface RegisterService {
	Register save(Register register);
	 Register getById(int id);
	 Page<Register> getAllRegister(Integer size);
	 Register getByTicketNo(String ticketNo);
	 Register  updateComplain(Integer id,Register register);
	 void closeComplaint(int id);

}
