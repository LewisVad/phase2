package com.phase2.controller;

import com.phase2.dao.StudentDao;
import com.phase2.dao.impl.DefaultAddressDao;
import com.phase2.dao.impl.DefaultPhoneDao;
import com.phase2.model.Address;
import com.phase2.model.Group;
import com.phase2.model.Phone;
import com.phase2.model.Student;
import com.phase2.service.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/test-config.xml")
@EnableTransactionManagement
class StudentServiceIT {

  @PersistenceContext
  private EntityManager entityManger;
  @Autowired
  private StudentService service;
  @Autowired
  private StudentDao studentDao;
  @Autowired
  private DefaultPhoneDao phoneDao;
  @Autowired
  private DefaultAddressDao addressDao;

  @BeforeEach
  void setUp() {
  }

  @Test
  @Transactional
  void submitStudent() {
    Student student = new Student();
    student.setFirstName("Student");
    student.setLastName("Student");
    Phone phone = new Phone();
    phone.setValue("123");
    student.setPhones(new ArrayList<>());
    student.getPhones().add(phone);
    Group group = new Group();
    group.setId(1);
    student.setGroup(group);
    Address address = new Address();
    address.setCity("MD");
    address.setCountry("MD");
    address.setStreet("MD");
    student.setAddress(address);

//    studentDao.save(student);
    service.saveStudent(student);

//    Student updatedStudent = studentDao.getStudentById(1);
    assertEquals(1, student.getPhones().size(), "Wrong number of phones");
    assertNotNull(student.getAddress(), "Wrong address");
  }
}