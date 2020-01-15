package com.teamname.hotelfx;

import com.teamname.hotelfx.data.Guest;
import com.teamname.hotelfx.data.Room;
import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class Controller {

    @FXML
    private ComboBox hotelComboBox;

    @FXML
    private TableView<Guest> guest_tableView;

    @FXML
    private TableView<Room> room_tableView;


    public void initialize() throws SQLException {

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

        /*add hotels form database to chotel omboBox*/
        hotelComboBox.getItems().setAll(HotelfxAccess.getInstance().getAllHotels());

        /*add eventlistener to hotel Combobox to change shown rooms in tableView based on selected hotel*/
        hotelComboBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (selected, oldHotel, newHotel) -> {
            if(newHotel != null){
                try {
                    room_tableView.getItems().setAll(HotelfxAccess.getInstance().getAllRooms(newHotel));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        hotelComboBox.getSelectionModel().selectFirst();
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

}




