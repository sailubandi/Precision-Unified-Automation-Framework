package com.framework.api.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * POJO class for User data
 * Represents user account information for API testing
 */
public class User {
    
    private String name;
    private String email;
    private String password;
    private String title;
    private String birth_date;
    private String birth_month;
    private String birth_year;
    private String firstname;
    private String lastname;
    private String company;
    private String address1;
    private String address2;
    private String country;
    private String zipcode;
    private String state;
    private String city;
    private String mobile_number;
    
    // Default constructor
    public User() {
    }
    
    // Constructor with essential fields
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getBirth_date() {
        return birth_date;
    }
    
    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }
    
    public String getBirth_month() {
        return birth_month;
    }
    
    public void setBirth_month(String birth_month) {
        this.birth_month = birth_month;
    }
    
    public String getBirth_year() {
        return birth_year;
    }
    
    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }
    
    public String getFirstname() {
        return firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastname() {
        return lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getAddress1() {
        return address1;
    }
    
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    
    public String getAddress2() {
        return address2;
    }
    
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getZipcode() {
        return zipcode;
    }
    
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getMobile_number() {
        return mobile_number;
    }
    
    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }
    
    /**
     * ✅ Convert User object → Map<String, String>
     * Used for formParams in API requests
     */
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        if (name != null) map.put("name", name);
        if (email != null) map.put("email", email);
        if (password != null) map.put("password", password);
        if (title != null) map.put("title", title);
        if (birth_date != null) map.put("birth_date", birth_date);
        if (birth_month != null) map.put("birth_month", birth_month);
        if (birth_year != null) map.put("birth_year", birth_year);
        if (firstname != null) map.put("firstname", firstname);
        if (lastname != null) map.put("lastname", lastname);
        if (company != null) map.put("company", company);
        if (address1 != null) map.put("address1", address1);
        if (address2 != null) map.put("address2", address2);
        if (country != null) map.put("country", country);
        if (zipcode != null) map.put("zipcode", zipcode);
        if (state != null) map.put("state", state);
        if (city != null) map.put("city", city);
        if (mobile_number != null) map.put("mobile_number", mobile_number);

        return map;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", title='" + title + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", birth_month='" + birth_month + '\'' +
                ", birth_year='" + birth_year + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", company='" + company + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", country='" + country + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", mobile_number='" + mobile_number + '\'' +
                '}';
    }
}
