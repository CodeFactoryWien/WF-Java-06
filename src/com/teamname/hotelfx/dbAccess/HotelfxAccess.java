package com.teamname.hotelfx.dbAccess;

import com.teamname.hotelfx.data.Guest;
import com.teamname.hotelfx.data.Room;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelfxAccess {

    private Connection conn;
    private static final String roomTable = "rooms";
    private static final String guestTable = "guests";
    PreparedStatement pstmnt;
    private static HotelfxAccess instance;

    static {
        try {
            instance = new HotelfxAccess();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private HotelfxAccess() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotelfx" +
                        "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",
                "");

        conn.setAutoCommit(true);
        conn.setReadOnly(false);
    }

    public void closeDb() throws SQLException {
        conn.close();
    }

    public List<Room> getAllRooms() throws SQLException {
        String sql = "SELECT * FROM " + roomTable + " ORDER BY roomID";
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<Room> list = FXCollections.observableArrayList();

        while (rs.next()) {
            int id = rs.getInt("roomID");
            int number = rs.getInt("roomNumber") ;
            String description = rs.getString("roomDescription");

            list.add(new Room(id, number, description));
        }

        pstmnt.close();
        return list;
    }

    public List<Guest> getAllGuests() throws SQLException {
        String sql = "SELECT * FROM " + guestTable + " ORDER BY guestID";
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<Guest> list = FXCollections.observableArrayList();

        while (rs.next()) {
            int id = rs.getInt("roomID");
            String guest_firstName = rs.getString("firstName");
            String guest_lastName = rs.getString("lastName");
            String address = rs.getString("address");
            String city = rs.getString("city");
            String zipCode = rs.getString("zipCode");
            String country = rs.getString("country");
            String phoneNumber = rs.getString("phoneNumber");
            String email = rs.getString("email");
            String gender = rs.getString("gender");

            list.add(new Guest(id,guest_firstName,guest_lastName,
                    address,city,zipCode,country,phoneNumber,email,gender));
        }

        pstmnt.close();
        return list;
    }

    public List<String> getColumnNames(String tableName) throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 2; i < rsmd.getColumnCount(); i++) {
            list.add(rsmd.getColumnName(i));
        }
        return list;
    }

    public static HotelfxAccess getInstance() {
        return instance;
    }
}
