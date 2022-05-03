package com.phase2.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Person")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "phase2", name = "persons")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique=true, nullable = false)
  private long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "gender")
  private char gender;

  @Column(name = "picture")
  private String picture;

  @Column(name = "email")
  private String email;

  @Column(name = "date_of_birth")
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private LocalDate dateOfBirth;

  @ManyToMany(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinTable(schema = "phase2", name = "persons_to_phones",
      joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "phone_id", referencedColumnName = "id"))
  private List<Phone> phones;

  @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
  @JoinColumn(name = "address_id")
  private Address address;

  public Person() {
  }

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public char getGender() {
    return gender;
  }

  public void setGender(char gender) {
    this.gender = gender;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public List<Phone> getPhones() {return phones;}

  public void setPhones(List<Phone> phones) { this.phones = phones; }

  public Address getAddress() {return address;}

  public void setAddress(Address address) {this.address = address;}

  public Person(long id, String firstName, String lastName, char gender, String picture, String email, LocalDate dateOfBirth, List<Phone> phones, Address address) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.picture = picture;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
    this.phones = phones;
    this.address = address;
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Person)) return false;
    return id != 0 && id == (((Person) obj).getId());
  }

}
