package com.service;
import com.request.RequestData;
import org.springframework.data.domain.Page;
import com.entity.Register;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface RegisterService {
	Register save(Register register);
	 Register getById(int id);
	 Page<Register> getAllRegister(Integer page,Integer size, String sort, Boolean sortDirection);
	 Register getByTicketNo(String ticketNo);
	 Register  updateComplain(Integer id,Register register);
	 void closeComplaint(int id);

}