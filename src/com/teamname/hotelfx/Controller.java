package com.teamname.hotelfx;

import com.teamname.hotelfx.controller.BookingTableWindowController;
import com.teamname.hotelfx.data.Guest;
import com.teamname.hotelfx.data.GuestSave;
import com.teamname.hotelfx.data.Room;
import com.teamname.hotelfx.data.StringPool;
import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller {
    private static final Logger logger = Logger.getLogger(Controller.class.getName());
    private GuestSave guestRepository = new GuestSave();

    private boolean toggleState;
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
    public TextField guest_ID;
    @FXML
    public Button guest_saveBtn;
    @FXML
    public Button guest_clearBtn;
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
    public Button room_clearBtn;
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
    @FXML
    private BorderPane borderPane;
    @FXML
    private GridPane guest_gridPane;
    @FXML
    public GridPane room_gridPane;
    @FXML
    private ToggleButton toggleButton;

    private List<Guest> listGuest = FXCollections.observableArrayList();
    private List<Room> listRoom = FXCollections.observableArrayList();


    /************************************* SAVE FEATURE ******************************************
     * -- package error in Controller > Textfield Guests
     * @param
     */
    @FXML
    public void saveTextFieldsGuests() {

        String tempID = guest_ID.getText();
        int guestID = (tempID.equals("")) ? 0 : Integer.parseInt(guest_ID.getText().trim());
        String firstName = guest_firstName.getText().trim();
        String lastName = guest_lastName.getText().trim();
        String address = guest_address.getText().trim();
        String city = guest_city.getText();
        String state = guest_state.getText().trim();
        String zipCode = guest_zipCode.getText().trim();
        String country = guest_country.getText().trim();
        String phoneNumber = guest_phoneNumber.getText();
        String emailAddress = guest_email.getText().trim();
        String gender = guest_gender.getText().trim();

        if (!StringPool.BLANK.equals(firstName) && !StringPool.BLANK.equals(lastName)
                && !StringPool.BLANK.equals(city) && !StringPool.BLANK.equals(state)
                && !StringPool.BLANK.equals(zipCode) && !StringPool.BLANK.equals(country)
                && !StringPool.BLANK.equals(phoneNumber) && !StringPool.BLANK.equals(emailAddress)
                && !StringPool.BLANK.equals(gender)
        ) {
            try {
                if (!guestRepository.guestExists(guestID)) {
                    Guest guest = new Guest(guestID, firstName, lastName, address, city, state, zipCode, country, phoneNumber, emailAddress, gender);
                    int guestId = guestRepository.saveGuest(guest);
                    if (guestId > 0) {
                        this.alert("Save", "Successful!", Alert.AlertType.INFORMATION);
                        guest_tableView.getItems().setAll(HotelfxAccess.getInstance().getAllGuests());
                        listGuest = HotelfxAccess.getInstance().getAllGuests();
                        guest_tableView.getSelectionModel().selectLast();
                    } else {
                        this.alert("Error", "Failed!", Alert.AlertType.ERROR);
                    }
                } else {
                    this.alert("Error", "User already exists!", Alert.AlertType.ERROR);
                    /* update method*/
                }
            } catch (Exception exception) {
                logger.log(Level.SEVERE, exception.getMessage());
            }
        } else {
            this.alert("Error", "Please complete fields!", Alert.AlertType.ERROR);
        }


    }


    public void alert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }


    /*********************************** CLEAR BUTTON *******************************************
     *
     * @param
     */
    @FXML
    protected void clearTextFields(GridPane gridpane) {

        for (Node node : gridpane.getChildren()) {
                //System.out.println("Id: " + node.getId());
                if (node instanceof TextField) {
                    // clear fields
                    ((TextField) node).setText("");
                }
            }
    }

    /*********************************** TOGGLE BUTTON *******************************************
     *
     * @param
     */
    @FXML
    protected void toggleOnAction(ActionEvent t) {

        guest_gridPane.setOpacity(1);
        guest_firstName.setEditable(true);
        guest_lastName.setEditable(true);
        guest_state.setEditable(true);
        guest_address.setEditable(true);
        guest_city.setEditable(true);
        guest_country.setEditable(true);
        guest_email.setEditable(true);
        guest_gender.setEditable(true);
        guest_phoneNumber.setEditable(true);
        guest_zipCode.setEditable(true);

        toggleButton.setStyle("-fx-background-color: green;");
        toggleButton.setText("ON");

        toggleButton.setOnAction(event -> {
            guest_gridPane.setOpacity(1);
            //Call all elements from a specific FXML container
            /*
            for (Node node : guest_gridPane.getChildren()) {
                node.setOpacity(1);
            }
            */
            if (toggleButton.isSelected()) {
                guest_firstName.setEditable(true);
                guest_lastName.setEditable(true);
                guest_state.setEditable(true);
                guest_address.setEditable(true);
                guest_city.setEditable(true);
                guest_country.setEditable(true);
                guest_email.setEditable(true);
                guest_gender.setEditable(true);
                guest_phoneNumber.setEditable(true);
                guest_zipCode.setEditable(true);

                toggleButton.setStyle("-fx-background-color: green;");
                toggleButton.setText("ON");
            } else {

                //node.setStyle("-fx-background-color:lightgrey");
                guest_gridPane.setOpacity(0.5);
                guest_firstName.setEditable(false);
                guest_lastName.setEditable(false);
                guest_state.setEditable(false);
                guest_address.setEditable(false);
                guest_city.setEditable(false);
                guest_country.setEditable(false);
                guest_email.setEditable(false);
                guest_gender.setEditable(false);
                guest_phoneNumber.setEditable(false);
                guest_zipCode.setEditable(false);
                toggleButton.setStyle("-fx-background-color: grey;");
                toggleButton.setText("OFF");
            }
        });
    }

    ;


    /*********************************** INITIALIZE *******************************************
     *
     * @param
     */
    public void initialize() throws SQLException {
        guest_gridPane.setOpacity(0.5);
        guest_firstName.setEditable(false);
        guest_lastName.setEditable(false);
        guest_state.setEditable(false);
        guest_address.setEditable(false);
        guest_city.setEditable(false);
        guest_country.setEditable(false);
        guest_email.setEditable(false);
        guest_gender.setEditable(false);
        guest_phoneNumber.setEditable(false);
        guest_zipCode.setEditable(false);

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

        guest_clearBtn.setOnAction(event -> {
            clearTextFields(guest_gridPane);
        });

        room_clearBtn.setOnAction(event -> {
            clearTextFields(room_gridPane);
        });

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
                if (new_val.intValue() < 0) return;
                Guest guest = listGuest.get(new_val.intValue());
                guest_ID.setText(String.valueOf(guest.getGuestID()));
                guest_firstName.setText(guest.getGuest_firstName());
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
                if (new_val.intValue() < 0) return;
                Room room = listRoom.get(new_val.intValue());
                room_number.setText(room.getRoomNumber());
                room_floor.setText(String.valueOf(room.getFloor()));
                room_description.setText(room.getDescription());
            }



        }
    }

    public List<Guest> getListGuest() {
        return listGuest;
    }

    public void setListGuest(List<Guest> listGuest) {
        this.listGuest = listGuest;
    }
}




