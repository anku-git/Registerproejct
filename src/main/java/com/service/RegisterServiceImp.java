package com.service;
import java.util.*;

import com.constant.Frequency;
import com.constant.Priority;
import com.entity.Remarks;
import com.repository.RemarksRepository;
import com.request.RequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.entity.Register;
import com.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterServiceImp implements RegisterService {
	@Autowired
	private RegisterRepository registerRepository;

	public Register save(Register register) {
		//Frequency frequency;
		String ticketNo = UUID.randomUUID().toString();
		register.setTicketNo(ticketNo);
		return registerRepository.save(register);
	}
	public Register getById(int id) {
		return registerRepository.findById(id).orElseThrow();

	}

	public Page<Register> getAllRegister(Integer page ,Integer size,String sortBy,Boolean sortDirection) {
		//Sort sort=Sort.by(Sort.Direction.ASC,"id");
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return registerRepository.findAll(pageable);
	}


	public Register getByTicketNo(String ticketNo) {
		return registerRepository.findByTicketNo(ticketNo).orElseThrow();
	}

	public Register updateComplain( Integer id,Register register) {
		Optional<Register> res=registerRepository.findById(id);
		if(res.isPresent()) {
			Register rs=res.get();
		rs.setDate(register.getDate());
		rs.setFrequency(register.getFrequency());
		rs.setIssue(register.getIssue());
		rs.setModelName(register.getModelName());
		rs.setPriority(rs.getPriority());
		rs.setProjectName(register.getProjectName());
		rs.setSubModule(register.getSubModule());
		return registerRepository.save(rs);
		
		
		}
		return null;
	}

	public void closeComplaint(int id) {
		registerRepository.closeComplaintStatus(id);
	}

}
