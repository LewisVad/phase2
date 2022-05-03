package com.phase2.dao;

import com.phase2.model.Membership;

import java.util.List;

public interface MembershipDao {

  /**
   * Method to find all membership
   *
   * @return a list of {@link Membership}s
   */
  List<Membership> getAllMembership();

  /**
   *
   * @param id - {@link Membership#id}
   * @return
   */
  Membership getMembershipById(Long id);

  /**
   * Method to create address
   *
   * @return
   */
  Membership addMembership(Membership membership);

  Membership updateMembership(Membership membership);

  void deleteMembership(long id);
}
