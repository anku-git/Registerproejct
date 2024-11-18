package com.service;

import com.constant.Status;
import com.entity.Register;
import com.entity.Remarks;
import com.repository.RegisterRepository;
import com.repository.RemarksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RemarksServiceImp implements RemarksService{
   @Autowired
   private RemarksRepository remarksRepository;
   @Autowired
   private RegisterRepository registerRepository;
    @Override
    public Remarks add(Integer registerId,Remarks remarks) {
        Register register=registerRepository.findById(registerId).orElseThrow();
        Remarks rm=new Remarks();
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
    public Remarks getById(Integer id) {
        return null;
    }

}
