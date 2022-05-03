package com.phase2.dao.impl;

import com.phase2.dao.MembershipDao;
import com.phase2.model.Address;
import com.phase2.model.Membership;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Qualifier("membership")
@Repository
public class DefaultMembershipDao implements MembershipDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Membership> getAllMembership() {
    return entityManager.createQuery("FROM Membership order by id desc", Membership.class)
        .getResultList();
  }

  @Override
  public Membership getMembershipById(Long id) {
    return entityManager.createQuery("FROM Membership lm where lm.id = :id", Membership.class)
        .setParameter("id", id).getSingleResult();
  }

  @Transactional
  public Membership addMembership(Membership membership) {
    entityManager.persist(membership);
    return membership;
  }

  @Transactional
  public Membership updateMembership(Membership membership) {
    return entityManager.merge(membership);
  }

  @Transactional
  public void deleteMembership(long id) {
    entityManager.remove(getMembershipById(id));
  }
}
