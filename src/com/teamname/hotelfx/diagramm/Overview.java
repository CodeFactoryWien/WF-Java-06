package com.teamname.hotelfx.diagramm;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Overview {
//
//    //Declare Overview Table Columns
//    private StringProperty room_Number;
//    private StringProperty start_Date;
//    private StringProperty end_Date;
//    private StringProperty firstName;
//    private StringProperty last_Name;
//
//
//    //Constructor
//    public Overview() {
//        this.room_Number = new SimpleStringProperty();
//        this.start_Date = new SimpleStringProperty();
//        this.end_Date = new SimpleStringProperty();
//        this.firstName = new SimpleStringProperty();
//        this.last_Name = new SimpleStringProperty();
//    }



    private final StringProperty roomNumber = new SimpleStringProperty(this, "roomNumber");

    public StringProperty roomNumberProperty() {
        return roomNumber;
    }
    private final StringProperty startDate = new SimpleStringProperty(this, "startDate");

    public StringProperty startDateProperty() {
        return startDate;
    }
    private final StringProperty endDate = new SimpleStringProperty(this, "endDate");

    public StringProperty endDateProperty() {
        return endDate;
    }
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName");

    public StringProperty firstNameProperty() {
        return firstName;
    }
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName");

    public StringProperty agentIDProperty() {
        return lastName;
    }


    public Overview(String roomNumber, String startDate, String endDate, String firstName, String lastName) {
        setRoomNumber(roomNumber);
        setStartDate(startDate);
        setEndDate(endDate);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public String getRoomNumber() {
        return roomNumber.get();
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber.set(roomNumber);
    }

    public String getStartDate() {
        return startDate.get();
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

    public String getEndDate() {
        return endDate.get();
    }

    public void setEndDate(String endDate) {
        this.endDate.set(endDate);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
}
