package com.service;

import com.entity.AddressDetails;
import com.entity.Register;
import com.helper.MailHelper;
import com.repository.AddressDetailsRepository;
import com.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class AddressDetailsServiceImp implements AddressDetailsService{
    //Aadhaar Regex
    private static final String AADHAAR_REGEX = "^[0-9]{4}[ -]?[0-9]{4}[ -]?[0-9]{4}$";

    // PAN Regex
    private static final String PAN_REGEX = "^[A-Z]{5}[0-9]{4}[A-Z]$";
    @Autowired
    private AddressDetailsRepository addressDetailsRepository;
    @Autowired
    private RegisterRepository registerRepository;
    @Override
    public AddressDetails add(Integer id,AddressDetails addressDetails) {
        Register register=registerRepository.findById(id).orElseThrow();
        AddressDetails addressDetails1=new AddressDetails();
        if (addressDetails.getAadhaarNumber() != null) {
            String aadhaar = addressDetails.getAadhaarNumber().trim();
            String panCard=addressDetails.getPanNumber().trim();
            if (!aadhaar.matches(AADHAAR_REGEX)) {
                throw new IllegalArgumentException("Invalid Aadhaar number: " + aadhaar);
            } else if (!panCard.matches(panCard)) {
                throw new IllegalArgumentException("Invalid Aadhaar number: " + aadhaar);
            }
        } else {
            throw new IllegalArgumentException("Aadhaar number cannot be null");
        }

        addressDetails.setRegisterId(register.getId());
        return addressDetailsRepository.save(addressDetails);
    }

    @Override
    public AddressDetails get(Integer id) {
        return addressDetailsRepository.findById(id).orElseThrow();
    }

    @Override
    public AddressDetails update(Integer id, AddressDetails details) {
        Optional<AddressDetails> addressDetails = addressDetailsRepository.findById(id);
        if (addressDetails.isPresent()) {
            AddressDetails ad = addressDetails.get();
            ad.setAadhaarNumber(details.getAadhaarNumber());
            ad.setAddress(details.getAddress());
            ad.setCountry(details.getCountry());
            ad.setAddressType(details.getAddressType());
            ad.setIdentification(details.getIdentification());
           ad.setState(details.getState());
           ad.setGstNo(details.getGstNo());
           ad.setStatus(details.getStatus());
           ad.setAction(details.getAction());
           ad.setPinCode(details.getPinCode());
           ad.setPanNumber(details.getPanNumber());
            return addressDetailsRepository.save(ad);
        }
        return null;
    }
}
