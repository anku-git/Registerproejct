package com.service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import com.entity.AddressDetails;
import com.entity.ContactsDetails;
import com.entity.RemarksManagement;
import com.helper.MailHelper;
import com.repository.AddressDetailsRepository;
import com.repository.ContactDetailsRepository;
import com.repository.RemarksManagementRepository;
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

	//Aadhaar Regex
	private static final String AADHAAR_REGEX = "^[0-9]{4}[ -]?[0-9]{4}[ -]?[0-9]{4}$";

	// PAN Regex
	private static final String PAN_REGEX = "^[A-Z]{5}[0-9]{4}[A-Z]$";
	LocalDate currentDate = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String formatDate = formatter.format(currentDate);
	@Autowired
	private RegisterRepository registerRepository;
	@Autowired
	private RemarksManagementRepository remarksRepository;
	@Autowired
	private ContactDetailsRepository contactDetailsRepository;
	@Autowired
	private AddressDetailsRepository addressDetailsRepository;
	@Autowired
	private MailHelper mailHelper;

	public Register save(Register register) {
		//Frequency frequency;
		String ticketNo = UUID.randomUUID().toString();
		register.setTicketNo(ticketNo);
		List<AddressDetails> addressList = register.getAddressDetails();
		if (addressList != null && !addressList.isEmpty()) {
			for (AddressDetails address : addressList) {
				if (address.getAadhaarNumber() != null) {
					String aadhaar = address.getAadhaarNumber().trim();
					String panCard = address.getPanNumber().trim();
					if (!aadhaar.matches(AADHAAR_REGEX)) {
						throw new IllegalArgumentException("Invalid Aadhaar number: " + aadhaar);
					} else if (!panCard.matches(PAN_REGEX)) {
						throw new IllegalArgumentException("Invalid PAN number: " + panCard);
					}
				} else {
					throw new IllegalArgumentException("please enter a valid value for address");
				}
			}

			//List<String> listOfStatus = List.of("ACTIVE", "INACTIVE");
			List<ContactsDetails> contactList = register.getContactsDetails();
			for (ContactsDetails contactsDetails : contactList) {
				if (contactsDetails.getFlag().equals("yes")) {
					contactsDetails.setStatus("ACTIVE");
					contactsDetails.setActiveDate(formatDate);
					contactsDetails.setInActiveDate(formatDate);
				} else {
					contactsDetails.setStatus("INACTIVE");
				}
				mailHelper.sendMail(contactsDetails.getEmail(),register.getTicketNo());
			}
		}
		register = registerRepository.save(register);
		RemarksManagement remarksManagement = new RemarksManagement();
		remarksManagement.setStatus(register.getStatus());
		remarksManagement.setRemarks(register.getRemarks());
		remarksManagement.setRegisterId(register.getId());
		remarksRepository.save(remarksManagement);


        return register;
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
		rs.setStatus(register.getStatus());
		rs.setProjectName(register.getProjectName());
		rs.setSubModule(register.getSubModule());
		RemarksManagement remarksManagement=new RemarksManagement();
		remarksManagement.setRegisterId(id);
		remarksManagement.setStatus(register.getStatus());
		remarksManagement.setRemarks(register.getRemarks());
		for(AddressDetails updateAddress : register.getAddressDetails()) {
			for (AddressDetails existingAddressDetails : rs.getAddressDetails()) {
				existingAddressDetails.setAddressType(updateAddress.getAddressType());
				existingAddressDetails.setIdentification(updateAddress.getIdentification());
				existingAddressDetails.setAddress(updateAddress.getAddress());
				existingAddressDetails.setCountry(updateAddress.getCountry());
				existingAddressDetails.setState(updateAddress.getState());
				existingAddressDetails.setPinCode(updateAddress.getPinCode());
				existingAddressDetails.setAction(updateAddress.getAction());
				existingAddressDetails.setPanNumber(updateAddress.getPanNumber());
				existingAddressDetails.setAadhaarNumber(updateAddress.getAadhaarNumber());
				existingAddressDetails.setStatus(updateAddress.getStatus());
				addressDetailsRepository.save(updateAddress);
			}
		}
		for(ContactsDetails updateDetails: register.getContactsDetails()){
			for(ContactsDetails existingDetails : rs.getContactsDetails()){
				//if(existingDetails.getId().equals(updateDetails.getId())){
					existingDetails.setFlag(updateDetails.getFlag());
					if(!updateDetails.getFlag().equals("yes")){
						existingDetails.setActiveDate(null);
						existingDetails.setInActiveDate(null);
                      existingDetails.setStatus("INACTIVE");
					}
					existingDetails.setStatus("ACTIVE");
					existingDetails.setActiveDate(formatDate);
					existingDetails.setInActiveDate(formatDate);
					existingDetails.setContacts(updateDetails.getContacts());
					existingDetails.setMdp(updateDetails.getMdp());
					existingDetails.setDncType(updateDetails.getDncType());
					existingDetails.setContactType(updateDetails.getContactType());
					existingDetails.setEmail(updateDetails.getEmail());
					contactDetailsRepository.save(updateDetails);
				}
		}
		remarksRepository.save(remarksManagement);
		return registerRepository.save(rs);
		}
		throw new IllegalArgumentException("register value not updated");
	}

	public void closeComplaint(int id) {
		registerRepository.closeComplaintStatus(id);
	}

	@Override
	public void deleteById(Integer id) {
		registerRepository.deleteById(id);
	}


}
