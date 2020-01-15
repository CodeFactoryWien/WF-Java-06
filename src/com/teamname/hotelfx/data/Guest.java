package com.teamname.hotelfx.data;

public class Guest {
    private int guestID;
    private String guest_firstName;
    private String guest_lastName;
    private String address;
    private String city;
    private String zipCode;
    private String country;
    private String phoneNumber;
    private String email;
    private String gender;


    public Guest(int guestID, String guest_firstName, String guest_lastName,
                 String address, String city, String zipCode, String country,
                 String phoneNumber, String email, String gender) {
        this.guestID = guestID;
        this.guest_firstName = guest_firstName;
        this.guest_lastName = guest_lastName;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public String getGuest_firstName() {
        return guest_firstName;
    }

    public void setGuest_firstName(String guest_firstName) {
        this.guest_firstName = guest_firstName;
    }

    public String getGuest_lastName() {
        return guest_lastName;
    }

    public void setGuest_lastName(String guest_lastName) {
        this.guest_lastName = guest_lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
