package com.teamname.hotelfx;

import com.teamname.hotelfx.controller.CheckInController;
import com.teamname.hotelfx.data.*;
import com.teamname.hotelfx.dbAccess.BackupScheduler;
import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller {
    private static final Logger logger = Logger.getLogger(Controller.class.getName());
    private GuestSave guestRepository = new GuestSave();
    private HashMap<String, String> textFieldData;
    String glass = "";
    String night = "";

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
    public TextField room_id;
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
    public TabPane tabPane;
    @FXML
    public Tab bookingWindowTab;
    @FXML
    public Tab checkInTab;
    @FXML
    public Tab chartTableTab;
    @FXML
    public Pane chartTable;
    @FXML
    public AnchorPane chartTableAnchor;
    @FXML
    private CheckInController checkInController;
    //    @FXML
//    private ChartTableController chartTableController;
    @FXML
    private ComboBox hotelComboBox;
    @FXML
    private TableView<Guest> guest_tableView;
    @FXML
    private TableView<Guest> chartTableView;
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
    @FXML
    private ToggleButton connectButton;
    @FXML
    private ToggleButton nightBtn;
    @FXML
    private ToggleButton glassBtn;
    @FXML
    private ToggleButton connectBtn;
    @FXML
    private Label labelConnect;


    private List<Guest> listGuest = FXCollections.observableArrayList();
    private List<Room> listRoom = FXCollections.observableArrayList();
    public List<Booking> listBooking = FXCollections.observableArrayList();
    double scrW = Screen.getPrimary().getVisualBounds().getWidth();
    double scrH = Screen.getPrimary().getVisualBounds().getHeight();

    private String guestColumnsSQL = "SELECT * FROM guests";
    private String roomsColumnsSQL = "SELECT rooms.roomNumber, rooms.floor, rooms.description, roomstatus.roomStatus, roomtype.roomType, hotels.hotelName FROM rooms " +
            "LEFT JOIN roomstatus ON roomstatus.roomStatusID = rooms.fk_roomStatusID " +
            "LEFT JOIN roomtype ON roomtype.roomtypeID = rooms.fk_roomTypeID " +
            "LEFT JOIN hotels ON hotels.hotelID = rooms.fk_hotelID";


    @FXML
    public void nightMode(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Hotel FX");

        Parent stageRoot = stage.getScene().getRoot();
        Blend blend = new Blend();
        blend.setMode(BlendMode.DIFFERENCE);
        ColorInput topInput = new ColorInput(0, 0, scrW, scrH, Color.WHITE);

        if (nightBtn.isSelected()) {
            night = " NIGHT ";
            blend.setTopInput(topInput);
            stage.setAlwaysOnTop(true);
            nightBtn.setText("NIGHTMODE ON");
            stage.setTitle(stage.getTitle() + glass + night);
            blend.setMode(BlendMode.DIFFERENCE);
            stageRoot.setEffect(blend);
            stageRoot.setOpacity(0.9);
            toggleButton.setStyle("-fx-background-color: rgb(255,127,255);");
            if (toggleButton.getText().equals("OFF")) {
                toggleButton.setStyle("-fx-background-color: grey");
            }

        } else {
            night = "";
            System.out.println("DB Conn closed");
            nightBtn.setText("NIGHTMODE OFF");
            blend.setMode(BlendMode.SRC_OVER);
            stageRoot.setEffect(blend);
            stageRoot.setOpacity(1);
            stage.setTitle(stage.getTitle() + glass + night);
            toggleButton.setStyle("-fx-background-color: rgb(0,127,0);");
            if (toggleButton.getText().equals("OFF")) {
                toggleButton.setStyle("-fx-background-color: grey");
            }
//            labelConnect.setStyle("-fx-background-color: green;");
//            labelConnect.setText("  Connection to Database closed --");

        }
    }

    @FXML
    public void glassMode(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Hotel FX");

        if (glassBtn.isSelected()) {
            glass = " GLASS ";
            glassBtn.setText("GLASSMODE ON");
            stage.setTitle(stage.getTitle() + glass + night);
            stage.setOpacity(0.5);

        } else {
            glass = "";
            glassBtn.setText("GLASSMODE OFF");
            stage.setTitle("Hotel FX");
            stage.setOpacity(1);
            stage.setTitle(stage.getTitle() + glass + night);
        }
    }




    @FXML
    public void dbConnection() {

        if (connectBtn.isSelected()) {
            try {
                HotelfxAccess.getDBConnection();
                HotelfxAccess.getInstance().getAllGuests();
                HotelfxAccess.addColumnsToTable(HotelfxAccess.getColumnNames(guestColumnsSQL), guest_tableView);
                HotelfxAccess.addColumnsToTable(HotelfxAccess.getColumnNames(roomsColumnsSQL), room_tableView);
                guest_tableView.getItems().setAll(HotelfxAccess.getAllGuests());
                hotelComboBox.getItems().setAll(HotelfxAccess.getAllHotels());
                hotelComboBox.getSelectionModel().selectFirst();
                guest_tableView.getSelectionModel().selectFirst();
                listGuest = HotelfxAccess.getAllGuests();

                connectBtn.setText("DISCONNECTED");


                labelConnect.setStyle("-fx-background-color: red;");
                labelConnect.setText("  Failed to connect to database --");

                if (HotelfxAccess.getDBConnection().isClosed()) {
                    labelConnect.setStyle("-fx-background-color: grey;");
                    labelConnect.setText("  Connection to Database closed --");
                } else {
                    labelConnect.setStyle("-fx-background-color: green;");
                    labelConnect.setText("  Connection to Database established --");
                }
                connectBtn.setText("CONNECTED");

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                HotelfxAccess.getDBConnection().close();
                System.out.println("DB Conn closed");
                connectBtn.setText("DISCONNECTED");

                labelConnect.setStyle("-fx-background-color: grey;");
                labelConnect.setText("  Connection to Database closed --");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    /************************************* SAVE FEATURE ******************************************
     * -- package error in Controller > Textfield Guests
     * @param
     */
    @FXML
    public void saveTextFieldsGuests() {

        getTextFieldData("guest");

        int c = 1;
        boolean filledOut = false;
        for (String i : textFieldData.values()) {

            if (c == textFieldData.size()) {
                filledOut = true;
            } else if (!StringPool.BLANK.equals(i)) {
                c++;
            } else {
                this.alert("Error", "Please complete fields!", Alert.AlertType.ERROR);
                break;
            }
        }

        if (filledOut) {
            try {
                if (!guestRepository.guestExists(Integer.parseInt(textFieldData.get("id")))) {
                    Guest guest = new Guest(Integer.parseInt(textFieldData.get("id")), textFieldData.get("firstName"), textFieldData.get("lastName"),
                            textFieldData.get("address"), textFieldData.get("city"), textFieldData.get("state"),
                            textFieldData.get("zipCode"), textFieldData.get("country"), textFieldData.get("phoneNumber"),
                            textFieldData.get("emailAddress"), textFieldData.get("gender"));
                    int guestId = guestRepository.saveGuest(guest);
                    if (guestId > 0) {
                        this.alert("Save", "Successful!", Alert.AlertType.INFORMATION);
                        guest_tableView.getItems().setAll(HotelfxAccess.getAllGuests());
                        listGuest = HotelfxAccess.getAllGuests();
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
        }

    }

    public void saveTextFieldsRooms() {
        getTextFieldData("room");

        int c = 1;
        boolean filledOut = false;
        for (String i : textFieldData.values()) {
            if (c == textFieldData.size()) {
                filledOut = true;
            } else if (i.equals("null")) {
                this.alert("Error", "Please complete fields!", Alert.AlertType.ERROR);
                break;
            }else if (!StringPool.BLANK.equals(i)) {
                c++;
            } else {
                this.alert("Error", "Please complete fields!", Alert.AlertType.ERROR);
                break;
            }
        }
        if (filledOut) {
            if (checkIfBookingExists(Integer.parseInt(guest_ID.getText()))) {
                Booking booking = new Booking(textFieldData.get("startDate"), textFieldData.get("endDate"),
                        Integer.parseInt(textFieldData.get("guestID")), 1, Integer.parseInt(textFieldData.get("hotelID")));

                booking.getRoomCountList().add(room_tableView.getSelectionModel().getSelectedItem());
                BookingList.getInstance().getBookingList().add(booking);
                checkInController.getBookings_tableView().getItems().setAll(BookingList.getInstance().getBookingList());
            } else {
                Booking b = loopBookings(guest_tableView.getSelectionModel().getSelectedItem().getGuestID());
                b.getRoomCountList().add(room_tableView.getSelectionModel().getSelectedItem());
                checkInController.getBookings_tableView().getItems().setAll(BookingList.getInstance().getBookingList());
            }
        }
    }

    public void alert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public void getTextFieldData(String dataName) {
        textFieldData = new HashMap<>();
        switch (dataName) {
            case ("guest"): {
                String tempId = (guest_ID.getText().equals("")) ? "0" : guest_ID.getText().trim();
                textFieldData.put("id", tempId);
                textFieldData.put("firstName", guest_firstName.getText().trim());
                textFieldData.put("lastName", guest_lastName.getText().trim());
                textFieldData.put("address", guest_address.getText().trim());
                textFieldData.put("city", guest_city.getText());
                textFieldData.put("state", guest_state.getText().trim());
                textFieldData.put("zipCode", guest_zipCode.getText().trim());
                textFieldData.put("country", guest_country.getText().trim());
                textFieldData.put("phoneNumber", guest_phoneNumber.getText());
                textFieldData.put("emailAddress", guest_email.getText().trim());
                textFieldData.put("gender", guest_gender.getText().trim());
                break;
            }
            case ("room"): {
                String tempId = (room_id.getText().equals("")) ? "0" : room_id.getText().trim();
                textFieldData.put("roomID", tempId);
                textFieldData.put("guestID", guest_ID.getText().trim());
                textFieldData.put("hotelID", String.valueOf(hotelComboBox.getSelectionModel().getSelectedIndex() + 1));
                textFieldData.put("startDate", String.valueOf(startDatePicker.getValue()));
                textFieldData.put("endDate", String.valueOf(endDatePicker.getValue()));
                break;
            }
        }
    }

    public boolean checkIfBookingExists(int guestID){
        boolean bExists = true;
        if(!BookingList.getInstance().getBookingList().isEmpty()){
            for(Booking booking: BookingList.getInstance().getBookingList()){
                if(booking.getGuestID() == guestID){
                    bExists = false;
                    break;
                }
            }
        }
        return bExists;
    }

    public Booking loopBookings (int guestID){
        Booking b = null;
        for(Booking booking: BookingList.getInstance().getBookingList()){
            if(booking.getGuestID() == guestID){
                b = booking;
                break;
            }
        }
        return b;
    }


    /*********************************** CLEAR BUTTON *******************************************
     *
     * @param
     */
    @FXML
    private void clearTextFields(GridPane gridpane) {

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

        room_gridPane.setOpacity(1);
        room_number.setEditable(false);
        room_floor.setEditable(false);
        room_description.setEditable(false);
        startDatePicker.setEditable(true);
        endDatePicker.setEditable(true);
        hotelComboBox.setEditable(true);

        if (nightBtn.getText().equals("NIGHTMODE ON") && toggleButton.getText().equals("ON")) {
            toggleButton.setStyle("-fx-background-color: rgb(0,127,0);");
        }


        if (nightBtn.isSelected()) {
            toggleButton.setStyle("-fx-background-color: rgb(0,127,0);");
        } else {
            toggleButton.setStyle("-fx-background-color: green");
        }
        toggleButton.setText("ON");

        toggleButton.setOnAction(event -> {
            guest_gridPane.setOpacity(1);
            room_gridPane.setOpacity(1);
            //Call all elements from a specific FXML container
            /*
            for (Node node : guest_gridPane.getChildren()) {
                node.setOpacity(1);
            }
            */
            if (toggleButton.isSelected()) {

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

                room_gridPane.setOpacity(1);
                startDatePicker.setEditable(true);
                endDatePicker.setEditable(true);
                hotelComboBox.setEditable(true);

                if (nightBtn.isSelected()) {
                    toggleButton.setStyle("-fx-background-color: rgb(255,127,255);");
                } else {
                    toggleButton.setStyle("-fx-background-color: green");
                }
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

                room_gridPane.setOpacity(0.5);
                room_number.setEditable(false);
                room_floor.setEditable(false);
                room_description.setEditable(false);
                startDatePicker.setEditable(false);
                endDatePicker.setEditable(false);
                hotelComboBox.setEditable(false);

                toggleButton.setStyle("-fx-background-color: grey");
                toggleButton.setText("OFF");
            }
        });
    }

    /*********************************** INITIALIZE *******************************************
     *
     * @param
     */

    private static boolean netIsAvailable() {
        try {
            final URL url = new URL("127.0.0.1:3306");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }


    public void initialize() throws SQLException, ParseException {

        /*add change listeners to tableViews*/
        guest_tableView.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListener(guest_tableView)
        );
        room_tableView.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListener(room_tableView)
        );

        /*add eventlistener to hotel Combobox to change shown rooms in tableView based on selected hotel*/
        hotelComboBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (selected, oldHotel, newHotel) -> {
            if (newHotel != null) {
                try {
                    room_tableView.getItems().setAll(HotelfxAccess.getAllRooms(newHotel));
                    listRoom = HotelfxAccess.getAllRooms(newHotel);
                    room_tableView.getSelectionModel().selectFirst();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        HotelfxAccess.getDBConnection();
        BackupScheduler.backupScheduler();

            nightBtn.setLayoutX(scrW - 115);
            glassBtn.setLayoutX(scrW - 232);
        room_floor.setEditable(false);
        room_number.setEditable(false);
        room_description.setEditable(false);

//        GanttChartSample gc = new GanttChartSample();
//        gc.calcChart();

        //textFieldListener.changed(null, null, guest_country.getText());
        try {
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

        listGuest = HotelfxAccess.getAllGuests();


            /* add column headers to tableViews*/
            HotelfxAccess.addColumnsToTable(HotelfxAccess.getColumnNames(guestColumnsSQL), guest_tableView);
            HotelfxAccess.addColumnsToTable(HotelfxAccess.getColumnNames(roomsColumnsSQL), room_tableView);

            /* add guests data to guest tableView*/
            guest_tableView.getItems().setAll(HotelfxAccess.getAllGuests());                                    //-------RECONNECT


            /*add hotels form database to chotel omboBox*/
            hotelComboBox.getItems().setAll(HotelfxAccess.getAllHotels());


        } catch (Exception ignore) {
            //
        }


        hotelComboBox.getSelectionModel().selectFirst();
        guest_tableView.getSelectionModel().selectFirst();

        guest_clearBtn.setOnAction(event -> {
            clearTextFields(guest_gridPane);
        });

        room_clearBtn.setOnAction(event -> {
            clearTextFields(room_gridPane);
        });

        guest_saveBtn.setOnAction(event -> {
            saveTextFieldsGuests();
        });

        room_saveBtn.setOnAction(event -> {
            saveTextFieldsRooms();
        });

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab != null) {
                if (newTab.getId().equals("checkInTab")) {
                        checkInController.getBookings_tableView().getSelectionModel().selectLast();
                }
            }
        });


    }



    private class ListSelectChangeListener implements ChangeListener<Number> {
        private TableView tableView;

        ListSelectChangeListener(TableView tableView) {
            this.tableView = tableView;
        }

        @Override
        public void changed(ObservableValue<? extends Number> selected,
                            Number old_val, Number new_val) {

            if (tableView.getId().equals("guest_tableView")) {
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
                guest_zipCode.setText(guest.getZipCode());
            } else {
                if (new_val.intValue() < 0) return;
                Room room = listRoom.get(new_val.intValue());
                room_number.setText(room.getRoomNumber());
                room_floor.setText(String.valueOf(room.getFloor()));
                room_description.setText(room.getDescription());
            }


        }
    }

    public List<Booking> getListBooking() {
        return listBooking;
    }

    public void setListBooking(List<Booking> listBooking) {
        this.listBooking = listBooking;
    }
}