
package com.teamname.hotelfx.dbAccess;

import com.teamname.hotelfx.data.Booking;
import com.teamname.hotelfx.data.Guest;
import com.teamname.hotelfx.data.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public static String roomStatusByID(int id) throws SQLException {
        String sql = "SELECT roomstatus.roomStatus FROM rooms " +
                "INNER JOIN roomstatus ON roomstatus.roomStatusID = rooms.fk_roomStatusID " +
                "WHERE rooms.roomID = " + id;
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        String roomStatus = "";

        while (rs.next()) {
            roomStatus = rs.getString("roomStatus");
        }

        pstmnt.close();
        return roomStatus;
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

    public static List<Integer> getRoomsByDate(Timestamp startDate, Timestamp endDate) throws SQLException {
        String sql = "SELECT rooms.roomID FROM rooms " +
                     "INNER JOIN roomsbooked ON roomsbooked.roomID = rooms.roomID " +
                     "INNER JOIN bookings    ON bookings.bookingID = roomsbooked.fk_bookingID " +
                     "WHERE bookings.fk_bookingStatusID = 1 " +
                     "AND     ((bookings.dateFrom <= ? AND bookings.dateTo >= ?) " +
                     "      OR (bookings.dateFrom <= ? AND bookings.dateTo >= ?) " +
                     "      OR (bookings.dateFrom >= ? AND bookings.dateTo <= ?)) ";
        pstmnt = conn.prepareStatement(sql);
        pstmnt.setTimestamp(1, startDate);
        pstmnt.setTimestamp(2, startDate);
        pstmnt.setTimestamp(3, endDate);
        pstmnt.setTimestamp(4, endDate);
        pstmnt.setTimestamp(5, startDate);
        pstmnt.setTimestamp(6, endDate);

        ResultSet rs = pstmnt.executeQuery();
        List<Integer> roomList = FXCollections.observableArrayList();
            while (rs.next()) {
                roomList.add(rs.getInt("roomID"));
            }

        pstmnt.close();
        System.out.println(roomList);
        return roomList;
    }


    public static int saveBooking(Booking booking) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = HotelfxAccess.getInstance().getConn();
            connection.setAutoCommit(false);
            String query = "INSERT INTO bookings(bookingID, dateFrom, dateTo, roomCount, fk_guestID, fk_reservationAgentID, fk_bookingStatusID, fk_hotelID) VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int counter = 1;

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDATE = formatter.parse(booking.getStartDate());
            Date endDATE = formatter.parse(booking.getEndDate());
            long x = 3600000;
            Timestamp timestamp = new Timestamp(startDATE.getTime() + x);
            statement.setTimestamp(counter++, timestamp);

            timestamp = new Timestamp(endDATE.getTime() + x);
            statement.setTimestamp(counter++, timestamp);

            statement.setInt(counter++, booking.getRoomCount());
            statement.setInt(counter++, booking.getGuestID());
            statement.setInt(counter++, booking.getAgentID());
            statement.setInt(counter++, 1);
            statement.setInt(counter++, booking.getHotelID());

            statement.executeUpdate();
            connection.commit();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException | ParseException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
        }
        return 0;
    }

    public static int saveRoom(int roomID, int bookingId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = HotelfxAccess.getInstance().getConn();
            connection.setAutoCommit(false);
            String query = "INSERT INTO roomsbooked(roomsBookedID, roomID, fk_bookingID) VALUES(DEFAULT, ?, ?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int counter = 1;
            statement.setInt(counter++, roomID);
            statement.setInt(counter++, bookingId);

            statement.executeUpdate();
            connection.commit();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
        }
        return 0;
    }

    public static int savePayment(int roomID, int price, int paymentTypeId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = HotelfxAccess.getInstance().getConn();
            connection.setAutoCommit(false);
            String query = "INSERT INTO payments(paymentID, date, payment, fk_roomID, fk_paymentTypeID, fk_paymentStatusID) VALUES(DEFAULT, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int counter = 1;
            statement.setTimestamp(counter++, new Timestamp(System.currentTimeMillis()));
            statement.setInt(counter++, price);
            statement.setInt(counter++, roomID);
            statement.setInt(counter++, paymentTypeId);
            statement.setInt(counter++, 1);

            statement.executeUpdate();
            connection.commit();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
        }
        return 0;
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

    public static int getPriceByRoomType(String roomType) throws SQLException {
        String sql = "SELECT roomType.roomTypeDescription FROM roomtype WHERE roomtype.roomType = '" + roomType +"'";
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();

        int roomTypePrice = 999;
        while (rs.next()) {
            roomTypePrice = rs.getInt("roomType.roomTypeDescription");
        }

        pstmnt.close();
        return roomTypePrice;
    }

    public static void updateRoomStatus(int roomID, int newRoomStatus) throws SQLException {
        String sql = "UPDATE rooms SET rooms.fk_roomStatusID = ? WHERE rooms.roomID = ?";
        pstmnt = conn.prepareStatement(sql);
        pstmnt.setInt(1, newRoomStatus);
        pstmnt.setInt(2, roomID);
        pstmnt.executeUpdate();

        pstmnt.close();
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

    public static String getAllRoomsByBookingIDINT (int bookingID) throws SQLException {
        // String sql = "Select roomID from roomsbooked where fk_bookingID = 1";
        String sql = "Select roomID from roomsbooked where fk_bookingID = " + bookingID;
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        String roomID = "";
        while (rs.next()) {
            roomID = String.valueOf(rs.getInt("roomID"));

        }
        pstmnt.close();
        return roomID;
    }



    public static boolean checkIfRoomIsInBooking(int roomID) throws SQLException {
        String sql = "Select bookings.bookingID from bookings " +
                "INNER JOIN roomsbooked ON roomsbooked.fk_bookingID = bookings.bookingID " +
                "INNER JOIN rooms ON rooms.roomID = roomsbooked.roomID " +
                "where bookings.fk_bookingStatusID = 1 " +
                "AND rooms.roomID = " + roomID;
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List <Integer> bList = new ArrayList<>();
        while (rs.next()) {
            bList.add(rs.getInt("bookingID"));
        }
        pstmnt.close();
        return bList.size() > 0;
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