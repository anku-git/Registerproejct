package com.service;

import com.entity.RemarksManagement;

public interface RemarksService {
    RemarksManagement add(Integer registerId, RemarksManagement remarks);
    RemarksManagement getById(Integer id);
}
