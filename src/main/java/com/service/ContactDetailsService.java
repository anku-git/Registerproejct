package com.service;

import com.entity.ContactsDetails;

public interface ContactDetailsService {
    ContactsDetails add(Integer id,ContactsDetails contactsDetails);
    ContactsDetails get(Integer id);
    ContactsDetails update(Integer id,ContactsDetails contactsDetails);
}
