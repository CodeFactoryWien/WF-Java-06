package com.teamname.hotelfx.data;

import com.teamname.hotelfx.dbAccess.HotelfxAccess;

import javax.print.attribute.standard.RequestingUserName;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GuestSave {

    private static final Logger logger = Logger.getLogger(GuestSave.class.getName());

    public boolean guestExists(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Guest> guests = new ArrayList<>();

        try {
            connection = HotelfxAccess.getInstance().getConn();
            connection.setAutoCommit(false);
            String query = "SELECT guestID, firstName, lastName, address, city, state, zipCode, country, phoneNumber, emailAddress, gender FROM guests WHERE guestID = ?";
            statement = connection.prepareStatement(query);
            int counter = 1;
            statement.setString(counter++, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int gID = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String address = resultSet.getString(4);
                String city = resultSet.getString(5);
                String state = resultSet.getString(6);
                String zip = resultSet.getString(7);
                String country = resultSet.getString(8);
                String phone = resultSet.getString(9);
                String email = resultSet.getString(10);
                String gender = resultSet.getString(11);

                guests.add(new Guest(gID,firstName,lastName,address,city,state,zip,country,phone,email,gender));
            }

            return guests.isEmpty() ? false : true;
        } catch (SQLException exception) {
            logger.log(Level.FINEST, exception.getMessage());
        } finally {
            if (null != statement) {
                //statement.close();
            }

            if (null != connection) {
                //connection.close();
            }
        }

        return guests.isEmpty() ? false : true;
    }

    public int saveGuest(Guest guest) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = HotelfxAccess.getInstance().getConn();
            connection.setAutoCommit(false);
            String query = "INSERT INTO guests(guestID, firstName, lastName, address, city, state, zipCode, country, phoneNumber, emailAddress, gender) VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            statement.setString(1, guest.getGuest_firstName());
            statement.setString(2, guest.getGuest_lastName());
            statement.setString(3, guest.getAddress());
            statement.setString(4, guest.getCity());
            statement.setString(5, guest.getState());
            statement.setString(6, guest.getZipCode());
            statement.setString(7, guest.getCountry());
            statement.setString(8, guest.getPhoneNumber());
            statement.setString(9, guest.getEmailAdress());
            statement.setString(10, guest.getGender());

            statement.executeUpdate();
            connection.commit();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
            if (null != connection) {
                //connection.rollback();
            }
        } finally {
            if (null != resultSet) {
                //resultSet.close();
            }

            if (null != statement) {
                //statement.close();
            }

            if (null != connection) {
                //connection.close();
            }
        }

        return 0;
    }

}