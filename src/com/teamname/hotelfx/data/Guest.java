package com.teamname.hotelfx.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Guest {

    private final IntegerProperty guestID = new SimpleIntegerProperty(this, "guestID");


    public IntegerProperty guestIProperty(){
        return guestID;
    }
    private final StringProperty guest_firstName = new SimpleStringProperty(this, "firstName");
    public StringProperty firstNameProperty(){
        return guest_firstName;
    }
    private final StringProperty guest_lastName = new SimpleStringProperty(this, "lastName");
    public StringProperty lastNameProperty(){
        return guest_lastName;
    }
    private final StringProperty address = new SimpleStringProperty(this, "address");
    public StringProperty addressProperty(){
        return address;
    }
    private final StringProperty city = new SimpleStringProperty(this, "city");
    public StringProperty cityProperty(){
        return city;
    }
    private final StringProperty state = new SimpleStringProperty(this, "state");
    public StringProperty stateProperty(){
        return state;
    }
    private final StringProperty zipCode = new SimpleStringProperty(this, "zipCode");
    public StringProperty zipCodeProperty(){
        return zipCode;
    }
    private final StringProperty country = new SimpleStringProperty(this, "country");
    public StringProperty countryProperty(){
        return country;
    }
    private final StringProperty phoneNumber = new SimpleStringProperty(this, "phoneNumber");
    public StringProperty phoneNumberProperty(){
        return phoneNumber;
    }
    private final StringProperty emailAdress = new SimpleStringProperty(this, "emailAdress");
    public StringProperty emailAddressProperty(){
        return emailAdress;
    }
    private final StringProperty gender = new SimpleStringProperty(this, "gender");
    public StringProperty genderProperty(){
        return gender;
    }

//    public IntegerProperty guestIProperty(){
//        return guestID;
//    }
//    private final StringProperty guest_firstName = new SimpleStringProperty(this, "firstName");
//    public StringProperty firstNameProperty(){
//        return guest_firstName;
//    }
//    private final StringProperty guest_lastName = new SimpleStringProperty(this, "lastName");
//    public StringProperty lastNameProperty(){
//        return guest_lastName;
//    }
//    private final StringProperty address = new SimpleStringProperty(this, "address");
//    public StringProperty addressProperty(){
//        return address;
//    }
//    private final StringProperty city = new SimpleStringProperty(this, "city");
//    public StringProperty cityProperty(){
//        return gender;
//    }
//    private final StringProperty state = new SimpleStringProperty(this, "state");
//    public StringProperty stateProperty(){
//        return gender;
//    }
//    private final StringProperty zipCode = new SimpleStringProperty(this, "zipCode");
//    public StringProperty zipCodeProperty(){
//        return state;
//    }
//    private final StringProperty country = new SimpleStringProperty(this, "country");
//    public StringProperty countryProperty(){
//        return zipCode;
//    }
//    private final StringProperty phoneNumber = new SimpleStringProperty(this, "phoneNumber");
//    public StringProperty phoneNumberProperty(){
//        return country;
//    }
//    private final StringProperty emailAdress = new SimpleStringProperty(this, "emailAdress");
//    public StringProperty emailAddressProperty(){
//        return phoneNumber;
//    }
//    private final StringProperty gender = new SimpleStringProperty(this, "gender");
//    public StringProperty genderProperty(){
//        return emailAdress;
//    }


    public Guest(int guestID, String guest_firstName, String guest_lastName,
                             String address, String city, String zipCode, String country,
                             String phoneNumber, String emailAddress, String gender, String state) {

        setGuestID(guestID);
        setGuest_firstName(guest_firstName);
        setGuest_lastName(guest_lastName);
        setAddress(address);
        setCity(city);
        setState(state);
        setZipCode(zipCode);
        setCountry(country);
        setPhoneNumber(phoneNumber);
        setEmailAdress(emailAddress);
        setGender(gender);
    }

    public int getGuestID() {
        return guestID.get();
    }

    public IntegerProperty guestIDProperty() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID.set(guestID);
    }

    public String getGuest_firstName() {
        return guest_firstName.get();
    }

    public StringProperty guest_firstNameProperty() {
        return guest_firstName;
    }

    public void setGuest_firstName(String guest_firstName) {
        this.guest_firstName.set(guest_firstName);
    }

    public String getGuest_lastName() {
        return guest_lastName.get();
    }

    public StringProperty guest_lastNameProperty() {
        return guest_lastName;
    }

    public void setGuest_lastName(String guest_lastName) {
        this.guest_lastName.set(guest_lastName);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getState() {
        return state.get();
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public String getZipCode() {
        return zipCode.get();
    }

    public void setZipCode(String zipCode) {
        this.zipCode.set(zipCode);
    }

    public String getCountry() {
        return country.get();
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getEmailAdress() {
        return emailAdress.get();
    }

    public StringProperty emailAdressProperty() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress.set(emailAdress);
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }
}

