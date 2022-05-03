package com.phase2.dao;

import com.phase2.model.Phone;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * DAO class to manage {@link Phone}
 */
@Transactional
public interface PhoneDao {

  /**
   * Method to create phone
   *
   * @return
   */
  Phone addPhone(Phone phone);

  /**
   * Method to update phone
   *
   * @return
   */
  Phone updatePhone(Phone phone);

  /**
   *
   * @param id - {@link Phone#id}
   * @return
   */
  Phone getPhoneById(Long id);

  /**
   * Method to find all phones
   *
   * @return a list of {@link Phone}s
   */
  List<Phone> getAllPhones();

  /**
   * Method to delete phone
   * @param id - {@link Phone#id}
   */
  Phone deletePhone(Phone id);
}
