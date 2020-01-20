package com.teamname.hotelfx.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Booking {

//    private final IntegerProperty bookingID = new SimpleIntegerProperty(this, "bookingID");
//    public IntegerProperty bookingIDProperty(){
//        return bookingID;
//    }
    private final StringProperty booking_startDate = new SimpleStringProperty(this, "booking_startDate");
    public StringProperty booking_startDateProperty(){
        return booking_startDate;
    }
    private final StringProperty booking_endDate = new SimpleStringProperty(this, "booking_endDate");
    public StringProperty booking_endDateProperty(){
        return booking_endDate;
    }
    private final IntegerProperty booking_guestID = new SimpleIntegerProperty(this, "booking_guestID");
    public IntegerProperty booking_guestIDProperty(){
        return booking_guestID;
    }
    private final IntegerProperty booking_agentID = new SimpleIntegerProperty(this, "booking_agentID");
    public IntegerProperty booking_agentIDProperty(){
        return booking_agentID;
    }
//    private final IntegerProperty booking_statusID = new SimpleIntegerProperty(this, "booking_statusID");
//    public IntegerProperty booking_statusIDProperty(){
//        return booking_statusID;
//    }
//    private final IntegerProperty booking_hotelID = new SimpleIntegerProperty(this, "booking_hotelID");
//    public IntegerProperty booking_hotelIDProperty(){
//        return booking_hotelID;
//    }
    private List<Integer> roomCount = new ArrayList<>();
    public int roomProperty(){
        return  roomCount.size();
    }



    public Booking(String booking_startDate, String booking_endDate,
                   int booking_guestID, int booking_agentID) {

//        setBookingID(bookingID);
        setBooking_startDate(booking_startDate);
        setBooking_endDate(booking_endDate);
        setBooking_guestID(booking_guestID);
        setBooking_agentID(booking_agentID);
//        setBooking_statusID(booking_statusID);
//        setBooking_hotelID(booking_hotelID);
    }

//    public int getBookingID() {
//        return bookingID.get();
//    }
//
//    public void setBookingID(int bookingID) {
//        this.bookingID.set(bookingID);
//    }

    public String getBooking_startDate() {
        return booking_startDate.get();
    }

    public void setBooking_startDate(String booking_startDate) {
        this.booking_startDate.set(booking_startDate);
    }

    public String getBooking_endDate() {
        return booking_endDate.get();
    }

    public void setBooking_endDate(String booking_endDate) {
        this.booking_endDate.set(booking_endDate);
    }

    public int getBooking_guestID() {
        return booking_guestID.get();
    }

    public void setBooking_guestID(int booking_guestID) {
        this.booking_guestID.set(booking_guestID);
    }

    public int getBooking_agentID() {
        return booking_agentID.get();
    }

    public void setBooking_agentID(int booking_agentID) {
        this.booking_agentID.set(booking_agentID);
    }

//    public int getBooking_statusID() {
//        return booking_statusID.get();
//    }
//
//    public void setBooking_statusID(int booking_statusID) {
//        this.booking_statusID.set(booking_statusID);
//    }

//    public int getBooking_hotelID() {
//        return booking_hotelID.get();
//    }
//
//    public void setBooking_hotelID(int booking_hotelID) {
//        this.booking_hotelID.set(booking_hotelID);
//    }

    public List<Integer> getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(List<Integer> roomCount) {
        this.roomCount = roomCount;
    }
}

