package com.phase2.service;

import com.phase2.dao.AddressDao;
import com.phase2.dao.GroupDao;
import com.phase2.dao.MembershipDao;
import com.phase2.dao.PhoneDao;
import com.phase2.dao.PhoneTypesDao;
import com.phase2.dao.StudentDao;
import com.phase2.model.Address;
import com.phase2.model.Group;
import com.phase2.model.Membership;
import com.phase2.model.Phone;
import com.phase2.model.Student;
import com.phase2.model.StudentSearchForm;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@Service
@Transactional
public class StudentService {

  @Resource
  private StudentDao studentDao;
  @Resource
  private GroupDao groupDao;
  @Resource
  private PhoneDao phoneDao;
  @Resource
  private PhoneTypesDao phoneTypesDao;
  @Resource
  private AddressDao addressDao;
  @Resource
  private MembershipDao membershipDao;

  public StudentService(StudentDao studentDao, PhoneDao phoneDao, PhoneTypesDao phoneTypesDao, AddressDao addressDao, MembershipDao membershipDao) {
    this.studentDao = studentDao;
    this.phoneDao = phoneDao;
    this.phoneTypesDao = phoneTypesDao;
    this.addressDao = addressDao;
    this.membershipDao = membershipDao;
  }

  public ModelAndView saveStudent(@ModelAttribute("student") Student studentForm) {

    Student student = (studentForm.getId() == 0 ? new Student() : studentDao.getStudentById(studentForm.getId()));
    student.setFirstName(studentForm.getFirstName());
    student.setLastName(studentForm.getLastName());
    student.setGender(studentForm.getGender());
    student.setPicture(studentForm.getPicture() == null ? student.getPicture() : studentForm.getPicture());
    student.setEmail(studentForm.getEmail());
    student.setDateOfBirth(studentForm.getDateOfBirth());
    if(studentForm.getGroup().getId() == 0){
      student.setGroup(null);
    } else {
    student.setGroup(groupDao.findById(studentForm.getGroup().getId()));}

    Address address = new Address();
    Long addressId = Long.valueOf(studentForm.getAddress().getId());
    String country = studentForm.getAddress().getCountry();
    String city = studentForm.getAddress().getCity();
    String street = studentForm.getAddress().getStreet();
    if ("".equals(country)) {
      address.setCountry(null);
    } else {
      address.setCountry(country);
    }
    if ("".equals(city)) {
      address.setCity(null);
    } else {
      address.setCity(city);
    }
    if ("".equals(street)) {
      address.setStreet(null);
    } else {
      address.setStreet(street);
    }
    student.setAddress(address);

    if (studentForm.getId() != 0) {
      Membership membership = (studentForm.getMembership().getId() == 0 ? new Membership() : membershipDao.getMembershipById(studentForm.getMembership().getId()));
      Long membershipId = Long.valueOf(studentForm.getMembership().getId());
      String status = studentForm.getMembership().getStatus();
      LocalDate startDate = studentForm.getMembership().getStartDate();
      LocalDate endDate = studentForm.getMembership().getEndDate();
      if ("".equals(status) || status == null) {membership.setStatus(null);} else {membership.setStatus(status);}
      if ("".equals(startDate) || startDate == null) {membership.setStartDate(null);} else {membership.setStartDate(startDate);}
      if ("".equals(endDate) || endDate == null) { membership.setEndDate(null);} else {membership.setEndDate(endDate);}
      if (membershipId == 0) {membershipDao.addMembership(membership);} else {membershipDao.updateMembership(membership);}
      student.setMembership(membership);

      List<Phone> phonesItems = new ArrayList();
      List<Phone> phonesBD = new ArrayList();
      if (studentForm.getId() != 0) {
        phonesBD = studentDao.getStudentById(student.getId()).getPhones();
      }
      List<Phone> phonesFR = (studentForm.getPhones() == null ? new ArrayList() : studentForm.getPhones());
      List<Phone> deletePhone = new ArrayList();

      if (phonesFR.size() > 0) {
        for (Phone phone : phonesFR) {
          if (phone.getValue() != null) {
            if (phone.getId() == null) {
              phonesItems.add(phoneDao.addPhone(phone));
            } else if (phonesBD.size() > 0) {
              if (phonesBD.contains(phone)) {
                phonesItems.add(phoneDao.updatePhone(phone));
              } else {
                if (phonesBD.contains(phone)) {
                  deletePhone.add(phoneDao.addPhone(phone));
                }
              }
            }
          }
        }
      }

      if (phonesBD.size() > 0) {
        for (Phone phone : phonesBD) {
          if (!(phonesFR.contains(phone))) {
            phoneDao.deletePhone(phone);
          }
        }
      }

      student.setPhones(phonesItems);
    }

    studentDao.save(student);
    return new ModelAndView("redirect:/editStudent/" + student.getId());
  }

  public List<Student> searchStudent(StudentSearchForm studentSearchForm) {

    String fullName = studentSearchForm.getFullName();
    String fullAddress = studentSearchForm.getFullAddress();
    char gender = studentSearchForm.getGender() != 'A' ? studentSearchForm.getGender() : 'A';
    LocalDate startDate = studentSearchForm.getStartDate() == null ? null : studentSearchForm.getStartDate();
    LocalDate endDate = studentSearchForm.getEndDate() == null ? null : studentSearchForm.getEndDate();
//    Group group = studentSearchForm.getGroup().getId() == 0 ? null : groupDao.findById(studentSearchForm.getGroup().getId());
//    Group group = null;
    long groupId = studentSearchForm.getGroup().getId();
    Group group = groupId == 0 ? null : groupDao.findById(groupId);

    StudentSearchForm studentSearch = new StudentSearchForm(fullName,fullAddress,gender,startDate,endDate,group);

    return studentDao.findStudent(studentSearch);
  }

//  public ModelAndView deleteStudentById(@ModelAttribute("student") long id) {
//    Student student = studentDao.getStudentById(id);
//    student.setGroup(null);
//    return studentDao.deleteStudent(student.getId());
//  }

}
