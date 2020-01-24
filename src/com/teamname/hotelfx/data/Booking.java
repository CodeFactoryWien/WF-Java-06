package com.teamname.hotelfx.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Booking {

    private final IntegerProperty bookingID = new SimpleIntegerProperty(this, "bookingID");
    public IntegerProperty bookingIDProperty(){
        return bookingID;
    }

    private final StringProperty startDate = new SimpleStringProperty(this, "startDate");
    public StringProperty startDateProperty(){
        return startDate;
    }
    private final StringProperty endDate = new SimpleStringProperty(this, "endDate");
    public StringProperty endDateProperty(){
        return endDate;
    }
    private final IntegerProperty guestID = new SimpleIntegerProperty(this, "guestID");
    public IntegerProperty guestIDProperty(){
        return guestID;
    }
    private final IntegerProperty agentID = new SimpleIntegerProperty(this, "agentID");
    public IntegerProperty agentIDProperty(){
        return agentID;
    }
//    private final IntegerProperty statusID = new SimpleIntegerProperty(this, "statusID");
//    public IntegerProperty statusIDProperty(){
//        return statusID;
//    }
    private final IntegerProperty hotelID = new SimpleIntegerProperty(this, "hotelID");
    public IntegerProperty hotelIDProperty(){
        return hotelID;
    }
    private ObservableList<Room> roomCount = FXCollections.observableArrayList();
    public int roomProperty(){
        return  roomCount.size();
    }



    public Booking(String startDate, String endDate,
                   int guestID, int agentID, int hotelID) {

//        setBookingID(bookingID);
        setStartDate(startDate);
        setEndDate(endDate);
        setGuestID(guestID);
        setAgentID(agentID);
//        setstatusID(statusID);
        setHotelID(hotelID);
    }

    public int getBookingID() {
        return bookingID.get();
    }

    public void setBookingID(int bookingID) {
        this.bookingID.set(bookingID);
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

    public int getGuestID() {
        return guestID.get();
    }

    public void setGuestID(int guestID) {
        this.guestID.set(guestID);
    }

    public int getAgentID() {
        return agentID.get();
    }

    public void setAgentID(int agentID) {
        this.agentID.set(agentID);
    }

    public ObservableList<Room> getRoomCountList() {
        return roomCount;
    }

    public int getRoomCount() {
        return roomCount.size();
    }

    public void setRoomCount(ObservableList<Room> roomCount) {
        this.roomCount = roomCount;
    }

    public int getHotelID() {
        return hotelID.get();
    }

    public void setHotelID(int hotelID) {
        this.hotelID.set(hotelID);
    }
}

