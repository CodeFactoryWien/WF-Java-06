package com.teamname.hotelfx.controller;

import com.teamname.hotelfx.Controller;
import com.teamname.hotelfx.data.Booking;
import com.teamname.hotelfx.data.BookingList;
import com.teamname.hotelfx.data.Room;
import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CheckInController {
    @FXML
    private TableView rooms_booking_tableView;
    @FXML
    private TableView bookings_tableView;

    private List<String> bookingTableColumnNames = new ArrayList<>();
    private List<String> roomTableColumnNames = new ArrayList<>();


    public void initialize() throws SQLException, ParseException {
        bookingTableColumnNames.add("startDate");
        bookingTableColumnNames.add("endDate");
        bookingTableColumnNames.add("guestID");
        bookingTableColumnNames.add("agentID");
        bookingTableColumnNames.add("hotelID");

        roomTableColumnNames.add("roomNumber");
        roomTableColumnNames.add("floor");
        roomTableColumnNames.add("description");
        roomTableColumnNames.add("roomType");
        roomTableColumnNames.add("hotelName");

        HotelfxAccess.addColumnsToTable(bookingTableColumnNames, bookings_tableView);
        HotelfxAccess.addColumnsToTable(roomTableColumnNames, rooms_booking_tableView);


        bookings_tableView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Booking>) (selected, oldBooking, newBooking) -> {
            if (newBooking != null) {
//                List<Room> rooms = newBooking.getRoomCount();
//                System.out.println("newbooking room count:  "+newBooking.getRoomCount());
//                List<Room> roomList = FXCollections.observableArrayList();
//                for (Room room : rooms) {
//                    try {
//                        roomList.add(HotelfxAccess.getRoomsByID(room.getRoomID()));
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }

                rooms_booking_tableView.setItems(newBooking.getRoomCount());

            }
        });
    }

    public TableView getBookings_tableView() {
        return bookings_tableView;
    }


}
