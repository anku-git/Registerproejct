package com.repository;

import com.entity.ContactsDetails;
import org.springframework.data.repository.CrudRepository;

public interface ContactDetailsRepository extends CrudRepository<ContactsDetails,Integer> {
}
