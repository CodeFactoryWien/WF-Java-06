package com.teamname.hotelfx;

import com.teamname.hotelfx.controller.BookingTableWindowController;
import com.teamname.hotelfx.data.Guest;
import com.teamname.hotelfx.data.Room;
import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class Controller {
    @FXML
    public TextField guest_firstName;
    @FXML
    public TextField guest_lastName;
    @FXML
    public TextField guest_gender;
    @FXML
    public TextField guest_address;
    @FXML
    public TextField guest_city;
    @FXML
    public TextField guest_state;
    @FXML
    public TextField guest_zipCode;
    @FXML
    public TextField guest_country;
    @FXML
    public TextField guest_phoneNumber;
    @FXML
    public TextField guest_email;
    @FXML
    public Button guest_saveBtn;
    @FXML
    public Button guest_cancelBtn;
    @FXML
    public TextField room_number;
    @FXML
    public TextField room_floor;
    @FXML
    public TextField room_description;
    @FXML
    public DatePicker startDatePicker;
    @FXML
    public DatePicker endDatePicker;
    @FXML
    public Button room_saveBtn;
    @FXML
    public Button room_cancelBtn;
    @FXML
    public Tab bookingTable;
    @FXML
    private BookingTableWindowController bookingTableWindowController;
    @FXML
    private ComboBox hotelComboBox;
    @FXML
    private TableView<Guest> guest_tableView;
    @FXML
    private TableView<Room> room_tableView;

    private List<Guest> listGuest = FXCollections.observableArrayList();
    private List<Room> listRoom = FXCollections.observableArrayList();


    public void initialize() throws SQLException {

        listGuest = HotelfxAccess.getInstance().getAllGuests();

        /* sql queries for columns headers*/
        String guestColumnsSQL = "SELECT * FROM guests";
        String roomsColumnsSQL = "SELECT rooms.roomNumber, rooms.floor, rooms.description, roomstatus.roomStatus, roomtype.roomType, hotels.hotelName FROM rooms " +
                "LEFT JOIN roomstatus ON roomstatus.roomStatusID = rooms.fk_roomStatusID " +
                "LEFT JOIN roomtype ON roomtype.roomtypeID = rooms.fk_roomTypeID " +
                "LEFT JOIN hotels ON hotels.hotelID = rooms.fk_hotelID";


        /* add column headers to tableViews*/
        addColumnsToTable(HotelfxAccess.getInstance().getColumnNames(guestColumnsSQL), guest_tableView);
        addColumnsToTable(HotelfxAccess.getInstance().getColumnNames(roomsColumnsSQL), room_tableView);

        /* add guests data to guest tableView*/
        guest_tableView.getItems().setAll(HotelfxAccess.getInstance().getAllGuests());

        /*add change listeners to tableViews*/
        guest_tableView.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListener(guest_tableView)
        );
        room_tableView.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListener(room_tableView)
        );

        /*add hotels form database to chotel omboBox*/
        hotelComboBox.getItems().setAll(HotelfxAccess.getInstance().getAllHotels());

        /*add eventlistener to hotel Combobox to change shown rooms in tableView based on selected hotel*/
        hotelComboBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (selected, oldHotel, newHotel) -> {
            if(newHotel != null){
                try {
                    room_tableView.getItems().setAll(HotelfxAccess.getInstance().getAllRooms(newHotel));
                    listRoom = HotelfxAccess.getInstance().getAllRooms(newHotel);
                    room_tableView.getSelectionModel().selectFirst();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        hotelComboBox.getSelectionModel().selectFirst();
        guest_tableView.getSelectionModel().selectFirst();
    }
    
    public void addColumnsToTable(List<String> columnNames, TableView tableView) {
        for (int i = 0; i < columnNames.size(); i++) {
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(
                    columnNames.get(i)
            );
            column.setCellValueFactory(new PropertyValueFactory<>(columnNames.get(i)));

            if (tableView == guest_tableView) { /* adjusts individual column width*/
                if(column.getText().equals("gender")) {
                    column.prefWidthProperty().bind(tableView.widthProperty().divide(29));
                }else if(column.getText().equals("address")){
                    column.prefWidthProperty().bind(tableView.widthProperty().divide(8.5));
                }else if(column.getText().equals("emailAddress")){
                    column.prefWidthProperty().bind(tableView.widthProperty().divide(8.5));
                }else{
                    column.prefWidthProperty().bind(tableView.widthProperty().divide(11));
                }
            }else {
                column.prefWidthProperty().bind(tableView.widthProperty().divide(6));
            }
            tableView.getColumns().add(column);
        }
    }

    private class ListSelectChangeListener implements ChangeListener<Number> {
        private TableView tableView;

        ListSelectChangeListener(TableView tableView) {
            this.tableView = tableView;
        }

        @Override
        public void changed(ObservableValue<? extends Number> selected,
                            Number old_val, Number new_val) {

            if(tableView.getId().equals("guest_tableView")){
                Guest guest = listGuest.get(new_val.intValue());
                guest_firstName.setText((String.valueOf(guest.getGuest_firstName())));
                guest_lastName.setText(guest.getGuest_lastName());
                guest_gender.setText(guest.getGender());
                guest_address.setText(guest.getAddress());
                guest_city.setText(guest.getCity());
                guest_country.setText(guest.getCountry());
                guest_email.setText(guest.getEmail());
                guest_phoneNumber.setText(guest.getPhoneNumber());
                guest_state.setText(guest.getState());
                guest_zipCode.setText(guest.getZipCode());            }
            else{
                Room room = listRoom.get(new_val.intValue());
                room_number.setText(room.getRoomNumber());
                room_floor.setText(String.valueOf(room.getFloor()));
                room_description.setText(room.getDescription());
            }



        }
    }

}




