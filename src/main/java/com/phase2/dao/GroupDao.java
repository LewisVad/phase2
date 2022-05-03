package com.phase2.dao;

import com.phase2.model.Group;

import java.util.List;

/**
 * DAO class to manage {@link Group}
 */
public interface GroupDao {

  void init();

  /**
   * Method to find all groups
   *
   * @return a list of {@link Group}s
   */
  List<Group> getAllGroups();

  /**
   *
   * @param id - {@link Group#id}
   * @return
   */
   Group findById(long id);

  /**
   * Method to delete group
   *
   * @return list with remaining group, without {@link com.phase2.model.Student}s
   * @param id
   */
  void deleteGroup(long id);

  /**
   * Method to edit group
   *
   * @return edited {@link Group#id}
   */
  Group saveGroup(Group group);

  /**
   * Method to get groups ids with students
   *
   * @return ids of {@link Group#id}
   */
  List<Long> groupsWithStudents();
}
