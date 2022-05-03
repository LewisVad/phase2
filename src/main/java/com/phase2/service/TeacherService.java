package com.phase2.service;

import com.phase2.dao.AddressDao;
import com.phase2.dao.PhoneDao;
import com.phase2.dao.PhoneTypesDao;
import com.phase2.dao.TeacherDao;
import com.phase2.model.Address;
import com.phase2.model.Phone;
import com.phase2.model.Teacher;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class TeacherService {

  @PersistenceContext
  private EntityManager entityManger;

  @Resource
  private TeacherDao teacherDao;
  @Resource
  private PhoneDao phoneDao;
  @Resource
  private PhoneTypesDao phoneTypesDao;
  @Resource
  private AddressDao addressDao;

  public TeacherService(TeacherDao teacherDao, PhoneDao phoneDao, PhoneTypesDao phoneTypesDao, AddressDao addressDao) {
    this.teacherDao = teacherDao;
    this.phoneDao = phoneDao;
    this.phoneTypesDao = phoneTypesDao;
    this.addressDao = addressDao;
  }

  public ModelAndView saveTeacher(@ModelAttribute("teacher") Teacher teacherForm) {

    Teacher teacher = (teacherForm.getId() == 0 ? new Teacher() : teacherDao.getTeacherById(teacherForm.getId()));
    teacher.setFirstName(teacherForm.getFirstName());
    teacher.setLastName(teacherForm.getLastName());
    teacher.setGender(teacherForm.getGender());
    teacher.setPicture(teacherForm.getPicture() == null ? teacher.getPicture() : teacherForm.getPicture());
    teacher.setEmail(teacherForm.getEmail());
    teacher.setDateOfBirth(teacherForm.getDateOfBirth());
    teacher.setSalary(teacherForm.getSalary());

    Address address = new Address();
    Long addressId = Long.valueOf(teacherForm.getAddress().getId());
    String country = teacherForm.getAddress().getCountry();
    String city = teacherForm.getAddress().getCity();
    String street = teacherForm.getAddress().getStreet();
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
    if (addressId == 0) {
      addressDao.addAddress(address);
    } else {
      addressDao.updateAddress(address);
    }
    teacher.setAddress(address);

    if (teacherForm.getId() != 0) {

      List<Phone> phonesItems = new ArrayList();
      List<Phone> phonesBD = new ArrayList();
      if (teacherForm.getId() != 0) {
        phonesBD = teacherDao.getTeacherById(teacher.getId()).getPhones();
      }
      List<Phone> phonesFR = (teacherForm.getPhones() == null ? new ArrayList() : teacherForm.getPhones());
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

      teacher.setPhones(phonesItems);
    }

    teacherDao.save(teacher);
    return new ModelAndView("redirect:/editTeacher/" + teacher.getId());
  }
}
