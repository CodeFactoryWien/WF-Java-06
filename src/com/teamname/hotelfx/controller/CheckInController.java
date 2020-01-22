package com.teamname.hotelfx.controller;

import com.teamname.hotelfx.Controller;
import com.teamname.hotelfx.data.Booking;
import com.teamname.hotelfx.data.BookingList;
import com.teamname.hotelfx.data.Guest;
import com.teamname.hotelfx.data.Room;
import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckInController {
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
    public TextField fullPrice;
    @FXML
    public TextArea priceDetails;
    @FXML
    private TableView rooms_booking_tableView;
    @FXML
    private TableView bookings_tableView;

    private List<String> bookingTableColumnNames = new ArrayList<>();
    private List<String> roomTableColumnNames = new ArrayList<>();
    private HashMap<String, Integer> priceByRoomType = new HashMap<>();


    public void initialize() throws SQLException, ParseException {
        bookingTableColumnNames.add("startDate");
        bookingTableColumnNames.add("endDate");
        bookingTableColumnNames.add("guestID");
        bookingTableColumnNames.add("roomCount");
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

                rooms_booking_tableView.setItems(newBooking.getRoomCountList());
                Booking booking = BookingList.getInstance().getBookingList().get(bookings_tableView.getSelectionModel().getSelectedIndex());
                startDate.setText(String.valueOf(booking.getStartDate()));
                endDate.setText(String.valueOf(booking.getEndDate()));
                guestID.setText(String.valueOf(booking.getGuestID()));
                roomCount.setText(String.valueOf(booking.getRoomCount()));
                agentID.setText(String.valueOf(booking.getAgentID()));
                hotelID.setText(String.valueOf(booking.getHotelID()));
                try {
                    calculatePrice(newBooking);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    public void calculatePrice(Booking booking) throws SQLException {
        priceDetails.clear();
        List<Room> currentRooms = booking.getRoomCountList();
        HashMap<String, Integer> roomTypes = new HashMap<>();
        for (Room room:currentRooms) {
            String currentType = room.getRoomType();
            if(!roomTypes.containsKey(currentType)){
                roomTypes.put(currentType, 1);
            }else{
                int count = roomTypes.get(currentType);
                roomTypes.replace(currentType, count, ++count);
            }
        }

        for (String roomType: roomTypes.keySet()) {
            if(!priceByRoomType.containsKey(roomType)){
                priceByRoomType.put(roomType, HotelfxAccess.getPriceByRoomType(roomType));
            }
                priceDetails.appendText("\n" + roomTypes.get(roomType) + " " + roomType + "  =  " + roomTypes.get(roomType) * priceByRoomType.get(roomType) + "€");
        }

    }

    private long getDateDifference(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.DAYS);
    }



    public TableView getBookings_tableView() {
        return bookings_tableView;
    }


}
