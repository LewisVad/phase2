package com.phase2.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity(name = "Phone")
@Table(schema = "phase2", name = "phones")
public class Phone {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Column(name = "id", unique=true, nullable = false)
  private Long id;
  private String value;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "type_id")
  private PhoneType phoneType;

  @ManyToMany(mappedBy = "phones", cascade={CascadeType.PERSIST, CascadeType.MERGE})
  private List<Person> persons;

  public Long getId() {return id;}

  public void setId(Long id) {this.id = id;}

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public PhoneType getPhoneType() {
    return phoneType;
  }

  public void setPhoneType(PhoneType phoneType) {
    this.phoneType = phoneType;
  }

  public List<Person> getPersons() {
    return persons;
  }

  public void setPersons(List<Person> persons) {
    this.persons = persons;
  }

  public Phone(Long id, String value, PhoneType phoneType, List<Person> persons) {
    super();
    this.id = id;
    this.value = value;
    this.phoneType = phoneType;
    this.persons = persons;
  }
//  public Phone(Long id, String value,PhoneType phoneType){
//    this.id = id;
//    this.value = value;
//    this.phoneType = phoneType;
//  }

  public Phone() {super();};

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Phone phone = (Phone) obj;
    return Objects.equals(value, phone.value);
  }


}
