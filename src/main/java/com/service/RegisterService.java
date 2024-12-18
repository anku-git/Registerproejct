package com.service;
import org.springframework.data.domain.Page;
import com.entity.Register;

import java.util.List;

public interface RegisterService {
	Register save(Register register);
	 Register getById(int id);
	 Page<Register> getAllRegister(Integer page,Integer size, String sort, Boolean sortDirection);
	 Register getByTicketNo(String ticketNo);
	 Register  updateComplain(Integer id,Register register);
	 void closeComplaint(int id);
	 void deleteById(Integer id);

}