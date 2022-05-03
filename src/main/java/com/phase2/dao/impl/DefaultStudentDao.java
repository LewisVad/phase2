package com.phase2.dao.impl;

import com.phase2.dao.GroupDao;
import com.phase2.dao.StudentDao;
import com.phase2.model.Student;
import com.phase2.model.StudentSearchForm;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Qualifier("student")
@Repository
public class DefaultStudentDao implements StudentDao {

  @PersistenceContext
  private EntityManager entityManager;
  private StudentSearchForm studentSearchForm = new StudentSearchForm();
  private GroupDao groupDao;

  @Override
  public void init() {
  }

  public List<Student> findAll() {
    return entityManager.createQuery("SELECT DISTINCT s FROM Student s left join fetch s.phones p order by s.id asc", Student.class)
        .getResultList();
  }

  public Student getStudentById(long id) {
    return entityManager.createQuery("from Student s where s.id = :id", Student.class)
        .setParameter("id", id).getSingleResult();
  }

  @Transactional
  public Student save(Student student) {
    if (student.getId() == 0) {
      entityManager.persist(student);
      return student;
    } else {
      return entityManager.merge(student);
    }
  }

  @Transactional
  public void deleteStudent(long id) {
    Student student = getStudentById(id);
      student.setGroup(null);
    entityManager.remove(student);
  }

//  @Transactional
//  public void deleteStudent(Student student) {
//    entityManager.remove(student);
//  }

  @Transactional
  public List<Student> findStudent(StudentSearchForm studentSearchForm) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Student> query = cb.createQuery(Student.class);
    Root<Student> root = query.from(Student.class);

    List<Predicate> predicates = new ArrayList<>();
    if(studentSearchForm.getFullName() != null && !("".equals(studentSearchForm.getFullName()))){
      Predicate first = cb.like(cb.upper(root.get("firstName")), studentSearchForm.getFullName().toUpperCase());
      Predicate last = cb.like(cb.upper(root.get("lastName")), studentSearchForm.getFullName().toUpperCase());
      predicates.add(cb.or(first, last));
    }

    if(studentSearchForm.getGender() != 'A') {    predicates.add(cb.equal(root.get("gender"), studentSearchForm.getGender()));}
    if(studentSearchForm.getFullAddress() != null  && !("".equals(studentSearchForm.getFullAddress()))) {
      Predicate country = cb.like(cb.upper(root.get("address").get("country")), studentSearchForm.getFullAddress().toUpperCase());
      Predicate city = cb.like(cb.upper(root.get("address").get("city")), studentSearchForm.getFullAddress().toUpperCase());
      Predicate street = cb.like(cb.upper(root.get("address").get("street")), studentSearchForm.getFullAddress().toUpperCase());
      predicates.add(cb.or(country, city, street));
    }
    if(studentSearchForm.getStartDate() != null && studentSearchForm.getEndDate() == null) {
      predicates.add(cb.greaterThanOrEqualTo(root.get("dateOfBirth"), studentSearchForm.getStartDate()));    }
    if(studentSearchForm.getEndDate() != null && studentSearchForm.getStartDate() == null) {
      predicates.add(cb.lessThanOrEqualTo(root.get("dateOfBirth"), studentSearchForm.getEndDate()));    }
    if(studentSearchForm.getStartDate() != null && studentSearchForm.getEndDate() != null) {
      predicates.add(cb.between(root.get("dateOfBirth"), studentSearchForm.getStartDate(), studentSearchForm.getEndDate()));    }
    if(studentSearchForm.getGroup() != null ) {
      predicates.add(cb.equal(root.get("group"), studentSearchForm.getGroup()));    }

    query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));

    return entityManager.createQuery(query).getResultList();
  }

//  @Override
//  public void setGroupToNull(long id) {
//    Query query = entityManager.createQuery("UPDATE Student s SET s.group_id = null WHERE s.id = ?1").setParameter(1, id);
//    query.executeUpdate();
//    TypedQuery<Student> m = entityManager.createQuery("update Student s set s.group_id = null where s.id = ?1 ", Student.class);
//    int executeUpdate = m.setParameter(1,id).executeUpdate();
//  }


}