package com.service;

import com.entity.ContactsDetails;
import com.entity.Register;
import com.helper.MailHelper;
import com.repository.ContactDetailsRepository;
import com.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactDetailsServiceImp implements ContactDetailsService{
    @Autowired
    private ContactDetailsRepository contactDetailsRepository;
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private MailHelper mailHelper;
    LocalDate currentDate=LocalDate.now();
    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String formatDate=formatter.format(currentDate);
    @Override
    public ContactsDetails add(Integer id,ContactsDetails contactsDetails) {
        Register register=registerRepository.findById(id).orElseThrow();
      ContactsDetails contactsDetails1=new ContactsDetails();
        List<String> listOfStatus=List.of("ACTIVE","INACTIVE");
      if(contactsDetails.getFlag().equals("yes")) {
          contactsDetails.setStatus("ACTIVE");
          contactsDetails.setActiveDate(formatDate);
          contactsDetails.setInActiveDate(formatDate);
      }else{
          contactsDetails.setStatus("INACTIVE");
      }
      contactsDetails.setRegisterId(register.getId());
       mailHelper.sendMail(contactsDetails.getEmail(),"123");
        return contactDetailsRepository.save(contactsDetails);
    }

    @Override
    public ContactsDetails get(Integer id) {
        return contactDetailsRepository.findById(id).orElseThrow();
    }

    @Override
    public ContactsDetails update(Integer id, ContactsDetails contactsDetails) {
        Optional<ContactsDetails> details=contactDetailsRepository.findById(id);
        if(details.isPresent()){
            ContactsDetails cd=details.get();
            cd.setContactType(contactsDetails.getContactType());
            cd.setContacts(contactsDetails.getContacts());
            cd.setDncType(contactsDetails.getDncType());
            cd.setMdp(contactsDetails.getMdp());
            cd.setEmail(contactsDetails.getEmail());
            if(!contactsDetails.getFlag().equals("yes")){
                cd.setActiveDate(null);
                cd.setInActiveDate(null);
               cd.setStatus("INACTIVE");
            }
            cd.setStatus("ACTIVE");
            cd.setActiveDate(formatDate);
           cd.setInActiveDate(formatDate);
            return contactDetailsRepository.save(cd);
        }
        return null;
    }
}
