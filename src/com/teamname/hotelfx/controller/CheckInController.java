package com.teamname.hotelfx.controller;

import com.teamname.hotelfx.Controller;
import com.teamname.hotelfx.data.Booking;
import com.teamname.hotelfx.data.BookingList;
import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CheckInController {
    @FXML
    private TableView bookings_tableView;
    private List<String> bookingTableColumnNames = new ArrayList<>();


    public void initialize() throws SQLException, ParseException {
        bookingTableColumnNames.add("startDate");
        bookingTableColumnNames.add("endDate");
        bookingTableColumnNames.add("guestID");
        bookingTableColumnNames.add("agentID");
        bookingTableColumnNames.add("hotelID");

        HotelfxAccess.addColumnsToTable(bookingTableColumnNames, bookings_tableView);

        bookings_tableView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (selected, oldHotel, newHotel) -> {
            if (newHotel != null) {
                bookings_tableView.getItems().setAll(BookingList.getInstance().getBookingList());
                bookings_tableView.getSelectionModel().selectFirst();
            }
        });
    }

    public TableView getBookings_tableView() {
        return bookings_tableView;
    }

    public void setBookings_tableView(TableView bookings_tableView) {
        this.bookings_tableView = bookings_tableView;
    }
}
