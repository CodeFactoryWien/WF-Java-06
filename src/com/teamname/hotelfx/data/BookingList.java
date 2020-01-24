package com.teamname.hotelfx.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookingList {
    private ObservableList<Booking> bookingList = FXCollections.observableArrayList();
    private static final BookingList instance = new BookingList();

    private BookingList() {
    }

    public ObservableList<Booking> getBookingList() {
        return bookingList;
    }

    public static BookingList getInstance() {
        return instance;
    }
}
