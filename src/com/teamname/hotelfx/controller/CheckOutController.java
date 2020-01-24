package com.teamname.hotelfx.controller;

import com.teamname.hotelfx.data.Booking;
import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckOutController {
    @FXML
    public TextField guest_ID;
    @FXML
    public TextField startDate;
    @FXML
    public TextField endDate;
    @FXML
    public TextField guestID;
    @FXML
    public TextField roomCount;
    @FXML
    public TextField agentID;
    @FXML
    public TextField hotelID;
    @FXML
    public ComboBox paymentComboBox;
    @FXML
    private ListView rooms_booking_listView;
    @FXML
    private TableView<Booking> bookings_tableView;
    @FXML
    private Button booking_updateBtn;

    private List<String> bookingTableColumnNames = new ArrayList<>();
    private List<String> roomTableColumnNames = new ArrayList<>();

    public void initialize() throws SQLException, ParseException {
        bookingTableColumnNames.add("startDate");
        bookingTableColumnNames.add("endDate");
        bookingTableColumnNames.add("guestID");
        bookingTableColumnNames.add("roomCount");
        bookingTableColumnNames.add("agentID");
        bookingTableColumnNames.add("hotelID");

        roomTableColumnNames.add("roomID");

        HotelfxAccess.addColumnsToTable(bookingTableColumnNames, bookings_tableView);
        //HotelfxAccess.addColumnsToTable(roomTableColumnNames, rooms_booking_listView);


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

                try {
                    rooms_booking_listView.getItems().setAll(HotelfxAccess.getAllRoomsByBookingID(newBooking.getBookingID()));
                    HotelfxAccess.getAllRoomsByBookingID(newBooking.getBookingID());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Booking booking = bookings_tableView.getSelectionModel().getSelectedItem();
                startDate.setText(String.valueOf(booking.getStartDate()));
                endDate.setText(String.valueOf(booking.getEndDate()));
                guestID.setText(String.valueOf(booking.getGuestID()));
                roomCount.setText(String.valueOf(booking.getRoomCount()));
                agentID.setText(String.valueOf(booking.getAgentID()));
                hotelID.setText(String.valueOf(booking.getHotelID()));

            }
        });

        booking_updateBtn.setOnAction(event -> {
            try {
                HotelfxAccess.updateBooking(bookings_tableView.getSelectionModel().getSelectedItem().getBookingID());
                List<String> roomList = new ArrayList<>();
                roomList.add(HotelfxAccess.getAllRoomsByBookingIDINT(bookings_tableView.getSelectionModel().getSelectedItem().getBookingID()));

                for (String string : roomList) {
                    if(!HotelfxAccess.checkIfRoomIsInBooking(Integer.parseInt(string))){
                        HotelfxAccess.updateRoomStatus(Integer.parseInt(string), 1);
                    }
                }

                bookings_tableView.getItems().setAll(HotelfxAccess.getAllBookings());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }



    public TableView<Booking> getBookings_tableView() {
        return bookings_tableView;
    }




}
