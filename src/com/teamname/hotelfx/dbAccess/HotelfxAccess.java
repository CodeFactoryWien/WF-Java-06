
package com.teamname.hotelfx.dbAccess;

import com.teamname.hotelfx.data.Booking;
import com.teamname.hotelfx.data.Guest;
import com.teamname.hotelfx.data.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HotelfxAccess {

    private static Connection conn = null;
    public static final Logger logger = Logger.getLogger(HotelfxAccess.class.getName());
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_DATABASE = "hotelfx";
    public static final String DB_CONNECTION = "jdbc:mysql://localhost/" + DB_DATABASE + "?useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "";


    private static final String roomTable = "rooms";
    private static final String guestTable = "guests";
    private static HotelfxAccess instance;
    private static PreparedStatement pstmnt;


    public static Connection getDBConnection() {

        try {
            Class.forName(DB_DRIVER);
            instance = new HotelfxAccess();
            conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            ResultSet rs = conn.getMetaData().getCatalogs();
            System.out.println("connecting...");


            while (rs.next()) {
                System.out.println(rs.getString("TABLE_CAT"));
            }

            return conn;
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println("cannot establish connection");
            logger.log(Level.SEVERE, exception.getMessage());
        }
        System.out.println(conn);
        return conn;
    }


    public HotelfxAccess() throws SQLException, ClassNotFoundException {

        //database connection
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
//
//        conn.setAutoCommit(true);
//        conn.setReadOnly(false);

    }

    public static void closeDb() throws SQLException {
        conn.close();
    }


    public static List<Room> getAllRooms(String hotelName) throws SQLException {
//        String sql = "SELECT * FROM " + roomTable + " ORDER BY roomID";
        String sql = "SELECT rooms.roomID, rooms.roomNumber, rooms.floor, rooms.description, roomstatus.roomStatus, roomtype.roomType, hotels.hotelName FROM rooms " +
                "LEFT JOIN roomstatus ON roomstatus.roomStatusID = rooms.fk_roomStatusID " +
                "LEFT JOIN roomtype ON roomtype.roomtypeID = rooms.fk_roomTypeID " +
                "LEFT JOIN hotels ON hotels.hotelID = rooms.fk_hotelID " +
                "WHERE hotels.hotelName = '" + hotelName + "' AND rooms.roomNumber NOT LIKE 'S%'";

        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<Room> list = FXCollections.observableArrayList();

        while (rs.next()) {
            int id = rs.getInt("roomID");
            String number = rs.getString("roomNumber");
            int floor = rs.getInt("floor");
            String description = rs.getString("description");
            String status = rs.getString("roomStatus");
            String type = rs.getString("roomType");
            String hotel = rs.getString("hotelName");

            list.add(new Room(id, floor, number, description, status, type, hotel));
        }

        pstmnt.close();
        return list;
    }

    public static List<Guest> getAllGuests() throws SQLException {
        String sql = "SELECT * FROM " + guestTable + " ORDER BY guestID";
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<Guest> list = FXCollections.observableArrayList();

        while (rs.next()) {
            int id = rs.getInt("guestID");
            String guest_firstName = rs.getString("firstName");
            String guest_lastName = rs.getString("lastName");
            String address = rs.getString("address");
            String city = rs.getString("city");
            String state = rs.getString("state");
            String zipCode = rs.getString("zipCode");
            String country = rs.getString("country");
            String phoneNumber = rs.getString("phoneNumber");
            String email = rs.getString("emailAddress");
            String gender = rs.getString("gender");

            list.add(new Guest(id, guest_firstName, guest_lastName,
                    address, city, zipCode, country, phoneNumber, email, gender, state));
        }

        pstmnt.close();
        return list;
    }

    public static List<String> getAllHotels() throws SQLException {
        String sql = "SELECT hotelName FROM hotels ORDER BY hotelID";
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<String> list = FXCollections.observableArrayList();

        while (rs.next()) {
            String hotelName = rs.getString("hotelName");
            list.add(hotelName);
        }

        pstmnt.close();
        return list;
    }

    public static List<String> getAllPaymentTypes() throws SQLException {
        String sql = "SELECT paymenttype FROM paymenttype ORDER BY paymentTypeID";
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<String> list = FXCollections.observableArrayList();

        while (rs.next()) {
            String paymenttype = rs.getString("paymenttype");
            list.add(paymenttype);
        }

        pstmnt.close();
        return list;
    }

    public static Room getRoomsByID(int roomID) throws SQLException {
        String sql = "SELECT rooms.roomID, rooms.roomNumber, rooms.floor, rooms.description, roomstatus.roomStatus, roomtype.roomType, hotels.hotelName FROM rooms " +
                "LEFT JOIN roomstatus ON roomstatus.roomStatusID = rooms.fk_roomStatusID " +
                "LEFT JOIN roomtype ON roomtype.roomtypeID = rooms.fk_roomTypeID " +
                "LEFT JOIN hotels ON hotels.hotelID = rooms.fk_hotelID " +
                "WHERE rooms.roomID = " + roomID;
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        Room room = null;
        while (rs.next()) {
            int id = rs.getInt("roomID");
            String number = rs.getString("roomNumber");
            int floor = rs.getInt("floor");
            String description = rs.getString("description");
            String status = rs.getString("roomStatus");
            String type = rs.getString("roomType");
            String hotel = rs.getString("hotelName");

            room = new Room(id, floor, number, description, status, type, hotel);
        }

        pstmnt.close();
        return room;
    }

    public static List<String> getColumnNames(String sql) throws SQLException {
        List<String> list = new ArrayList<>();
//        String sql = "SELECT * FROM " + tableName;
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
            list.add(rsmd.getColumnName(i));
        }

        pstmnt.close();
        return list;
    }


    public static void addColumnsToTable(List<String> columnNames, TableView tableView) {
        for (String columnName : columnNames) {
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(
                    columnName
            );
            column.setCellValueFactory(new PropertyValueFactory<>(columnName));

            if (tableView.getId().equals("guest_tableView")) { /* adjusts individual column width*/
                switch (column.getText()) {
                    case "gender":
                        column.prefWidthProperty().bind(tableView.widthProperty().divide(29));
                        break;
                    case "address":
                    case "emailAddress":
                        column.prefWidthProperty().bind(tableView.widthProperty().divide(8.5));
                        break;
                    default:
                        column.prefWidthProperty().bind(tableView.widthProperty().divide(11));
                        break;
                }
            } else {
                column.prefWidthProperty().bind(tableView.widthProperty().divide(6));
            }
            tableView.getColumns().add(column);
        }
    }

    public static List<Booking> getAllBookings () throws SQLException {
        // String sql = "SELECT * from bookings";
        String sql = "SELECT * from bookings WHERE fk_bookingStatusID = 1";
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<Booking> bookingList = FXCollections.observableArrayList();
        while (rs.next()) {
            int bookingID = rs.getInt("bookingID");
            String startDate = rs.getString("dateFrom");
            String endDate = rs.getString("dateTo");
            int guestID = rs.getInt("fk_guestID");
            String number = rs.getString("roomCount");
            int agentID = rs.getInt("fk_reservationAgentID");
            int hotelID = rs.getInt("fk_hotelID");

            Booking b = new Booking(startDate, endDate, guestID, agentID, hotelID);
            b.setBookingID(bookingID);
            bookingList.add(b);

        }
        pstmnt.close();
        return bookingList;
    }

    public static List<String> getAllRoomsByBookingID (int bookingID) throws SQLException {
        // String sql = "Select roomID from roomsbooked where fk_bookingID = 1";
        String sql = "Select roomID from roomsbooked where fk_bookingID = " + bookingID;
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<String> roomList = FXCollections.observableArrayList();
        while (rs.next()) {
            int roomID = rs.getInt("roomID");

            roomList.add("RoomID: " + roomID);
        }
        pstmnt.close();
        return roomList;
    }

    private static final String bookings = "bookings";

    public static void updateBooking(int bookingID) throws SQLException {
        String sql = "UPDATE "+  bookings + " SET fk_bookingStatusID = ? where bookings.bookingID = ?";
        pstmnt = conn.prepareStatement(sql);
        pstmnt.setInt(2, bookingID);
        pstmnt.setInt(1, 2);
        pstmnt.executeUpdate();
        pstmnt.close();
    }

    public static HotelfxAccess getInstance() {
        return instance;
    }

    public Connection getConn() {
        return conn;
    }
}