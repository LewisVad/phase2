package com.phase2.dao.impl;

import com.phase2.dao.AddressDao;
import com.phase2.model.Address;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DefaultAddressDao implements AddressDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void init() {
  }

  @Override
  public List<Address> getAllAddresses() {
    return entityManager.createQuery("FROM Address order by id desc", Address.class).getResultList();
  }

  @Override
  public Address getAddressById(Long id) {
    return entityManager.createQuery("FROM Address ad where ad.id = :id", Address.class)
        .setParameter("id", id).getSingleResult();
  }

  @Transactional
  public Address addAddress(Address address) {
    entityManager.persist(address);
    return address;
  }

  @Transactional
  public Address updateAddress(Address address) {
    entityManager.merge(address);
    return address;
  }

  @Override
  public void deleteAddress(long id) {
    entityManager.remove(getAddressById(id));
  }
}