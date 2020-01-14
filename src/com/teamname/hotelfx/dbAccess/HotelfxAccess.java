package com.teamname.hotelfx.dbAccess;

import com.teamname.hotelfx.data.Room;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;

public class HotelfxAccess {

    private Connection conn;
    private static final String roomTable = "rooms";
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
        String sql = "SELECT * FROM " + roomTable + " ORDER BY room_id";
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<Room> list = FXCollections.observableArrayList();

        while (rs.next()) {
            int ID = rs.getInt("roomID");
            int number = rs.getInt("roomNumber") ;
            String description = rs.getString("roomDescription");

            list.add(new Room(ID, number, description));
        }

        pstmnt.close();
        return list;
    }

    public static HotelfxAccess getInstance() {
        return instance;
    }
}
