package com.teamname.hotelfx.controller;

import com.teamname.hotelfx.data.Booking;
import com.teamname.hotelfx.data.BookingList;
import com.teamname.hotelfx.data.Room;
import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
    public Button booking_saveBtn;
    @FXML
    public Button booking_deleteBtn;
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
                } catch (SQLException | ParseException e) {
                    e.printStackTrace();
                }

            }
        });
        booking_deleteBtn.setOnAction(event -> {
            if(bookings_tableView.getItems().size() > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm deletion");
                alert.setHeaderText("Sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (!result.isPresent()) {
                } else if (result.get() == ButtonType.OK) {
                    updateTablesAndRemoveLast();
                }
            }
        });

        booking_saveBtn.setOnAction(event -> {
            if(bookings_tableView.getItems().size() > 0) {
                try {
                    int bookingID = HotelfxAccess.saveBooking((Booking) bookings_tableView.getSelectionModel().getSelectedItem());
                    if (bookingID > 0) {
                        Booking booking = (Booking) bookings_tableView.getSelectionModel().getSelectedItem();
                        for(Room room :  booking.getRoomCountList()){
                            int roomid = HotelfxAccess.saveRoom(room.getRoomID(), bookingID);
                            int price = Integer.parseInt(fullPrice.getText().substring(0, fullPrice.getText().length() - 1));
                            int paymentID = HotelfxAccess.savePayment(room.getRoomID(), price,
                                            paymentComboBox.getSelectionModel().getSelectedIndex() + 1);
                            HotelfxAccess.updateRoomStatus(room.getRoomID(), 2);
                            if(roomid < 1 || paymentID < 1){
                                this.alert("Error", "Failed!", Alert.AlertType.ERROR);
                                break;
                            }
                        }
                        this.alert("Save", "Successful!", Alert.AlertType.INFORMATION);
                        updateTablesAndRemoveLast();
                    } else {
                        this.alert("Error", "Failed!", Alert.AlertType.ERROR);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

    }

    public void updateTablesAndRemoveLast(){
        BookingList.getInstance().getBookingList().remove(bookings_tableView.getSelectionModel().getSelectedIndex());
        bookings_tableView.getItems().setAll(BookingList.getInstance().getBookingList());
        rooms_booking_tableView.setItems(null);
        bookings_tableView.getSelectionModel().selectLast();
    }


    public void calculatePrice(Booking booking) throws SQLException, ParseException {
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

        int finalPrice = 0;
        for (String roomType: roomTypes.keySet()) {
            if(!priceByRoomType.containsKey(roomType)){
                priceByRoomType.put(roomType, HotelfxAccess.getPriceByRoomType(roomType));
            }
            int roomPrice = roomTypes.get(roomType) * priceByRoomType.get(roomType);
            priceDetails.appendText("\n" + roomTypes.get(roomType) + " " + roomType + "(" + priceByRoomType.get(roomType) + ") " + "  =  " + roomPrice + "€");
            finalPrice += roomPrice;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDATE = formatter.parse(booking.getStartDate());
        Date endDATE = formatter.parse(booking.getEndDate());

        long dateDiff = getDateDifference(startDATE, endDATE, TimeUnit.DAYS);
        String night_nights = (dateDiff > 1)? " nights" : " night";
        priceDetails.appendText("\n\t\t for " + dateDiff + night_nights );
        finalPrice *= dateDiff;

        fullPrice.setText(finalPrice + "€");

    }

    public long getDateDifference(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    public void alert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public TableView getBookings_tableView() {
        return bookings_tableView;
    }


}