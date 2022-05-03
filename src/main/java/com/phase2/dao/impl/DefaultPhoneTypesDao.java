package com.phase2.dao.impl;

import com.phase2.dao.PhoneTypesDao;
import com.phase2.model.PhoneType;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Qualifier("phoneType")
@Repository
public class DefaultPhoneTypesDao implements PhoneTypesDao {

  //  @Autowired
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public PhoneType getPhoneTypeById(int id) {
    return entityManager.createQuery("FROM PhoneType pt where pt.id = :id", PhoneType.class)
        .setParameter("id", id).getSingleResult();
  }

  @Override
//  @SuppressWarnings("unchecked")
  public List<PhoneType> getAllPhoneTypes() {
    return entityManager.createQuery("SELECT DISTINCT pt FROM PhoneType pt", PhoneType.class)
        .getResultList();
  }
}
