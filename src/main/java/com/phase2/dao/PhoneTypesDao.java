package com.phase2.dao;


import com.phase2.model.PhoneType;

import java.util.List;

public interface PhoneTypesDao {

  PhoneType getPhoneTypeById(int id);

  List<PhoneType> getAllPhoneTypes();
}
