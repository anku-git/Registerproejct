package com.service;

import com.entity.AddressDetails;

public interface AddressDetailsService {
    AddressDetails add(Integer registerId,AddressDetails addressDetails);
    AddressDetails get(Integer id);
    AddressDetails update(Integer id,AddressDetails details);
}
