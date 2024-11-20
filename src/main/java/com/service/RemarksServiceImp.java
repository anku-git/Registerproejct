package com.service;

import com.entity.Register;
import com.entity.RemarksManagement;
import com.repository.RegisterRepository;
import com.repository.RemarksManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RemarksServiceImp implements RemarksService{
   @Autowired
   private RemarksManagementRepository remarksRepository;
   @Autowired
   private RegisterRepository registerRepository;
    @Override
    public RemarksManagement add(Integer registerId, RemarksManagement remarks) {
        Register register=registerRepository.findById(registerId).orElseThrow();
        RemarksManagement rm=new RemarksManagement();
        //rm.setDate(LocalDateTime.now());
        rm.setRemarks(remarks.getRemarks());
        List<String> listOfStatus=List.of("NEW","ASSIGN","RESOLVED","REVERT","CLOSED");
//		if (!listOfStatus.contains(remarks.getStatus())) {
//				throw new IllegalArgumentException("INVALID value PLEASE ENTER THE CORRECT VALUE");
//			}
        //rm.setStatus(remarks.getStatus());
        return remarksRepository.save(rm);
    }
    @Override
    public RemarksManagement getById(Integer id) {
        return remarksRepository.findById(id).orElseThrow();
    }

}
