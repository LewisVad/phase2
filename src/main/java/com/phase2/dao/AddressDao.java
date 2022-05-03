package com.phase2.dao;

import com.phase2.model.Address;
import com.phase2.model.Group;

import java.util.List;

public interface AddressDao {

  void init();

  /**
   * Method to find all students
   *
   * @return a list of {@link Group}s
   */
  List<Address> getAllAddresses();

  /**
   *
   * @param id - {@link Group#id}
   * @return
   */
  Address getAddressById(Long id);

  /**
   * Method to create address
   *
   * @return
   */
  Address addAddress(Address address);

  Address updateAddress(Address address);

  /**
   * Method to delete address
   * @param id - {@link Address#id}
   */
  void deleteAddress(long id);
}
