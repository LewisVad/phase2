package com.phase2.dao.impl;

import com.phase2.dao.PhoneDao;
import com.phase2.dao.StudentDao;
import com.phase2.model.Phone;

import org.hibernate.jpa.QueryHints;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Qualifier("phone")
@Repository
public class DefaultPhoneDao implements PhoneDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  public Phone addPhone(Phone phone) {
    entityManager.persist(phone);
    return phone;
  }

  @Transactional
  public Phone updatePhone(Phone phone) {
    entityManager.merge(phone);
    return phone;
  }

  @Override
  public Phone getPhoneById(Long id) {
    return entityManager.createQuery("FROM Phone ph where pt.id = :id", Phone.class)
        .setParameter("id", id).getSingleResult();
  }

  @Override
  public List<Phone> getAllPhones() {
    return entityManager.createQuery("FROM Phone", Phone.class).getResultList();
  }

  @Transactional
  public Phone deletePhone(Phone phone) {
    entityManager.remove(phone);
    return phone;
  }
}
