package com.trial.onetomany.onetomany.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usersProfile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    @Size(max = 15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dateOfBirth;

    @Size(max = 100)
    private String address1;

    @Size(max = 100)
    private String address2;

    @Size(max = 100)
    private String street;

    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String state;

    @Size(max = 100)
    private String country;

    @Column(name = "zip_code")
    @Size(max = 32)
    private String zipCode;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserProfile() {
    }

    public UserProfile(@Size( max = 15 ) String phoneNumber, Gender gender, Date dateOfBirth, @Size( max = 100 ) String address1, @Size( max = 100 ) String address2, @Size( max = 100 ) String street, @Size( max = 100 ) String city, @Size( max = 100 ) String state, @Size( max = 100 ) String country, @Size( max = 32 ) String zipCode) {
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address1 = address1;
        this.address2 = address2;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public UserProfile setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserProfile setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public UserProfile setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public UserProfile setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getAddress1() {
        return address1;
    }

    public UserProfile setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getAddress2() {
        return address2;
    }

    public UserProfile setAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public UserProfile setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserProfile setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public UserProfile setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserProfile setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public UserProfile setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserProfile setUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", user=" + user +
                '}';
    }


}
