package com.teamname.hotelfx.data;

import com.teamname.hotelfx.dbAccess.HotelfxAccess;

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
                Guest guest = new Guest();
                guest.setGuestID(resultSet.getInt(1));
                guest.setGuest_firstName(resultSet.getString(2));
                guest.setGuest_lastName(resultSet.getString(3));
                guest.setAddress(resultSet.getString(4));
                guest.setCity(resultSet.getString(5));
                guest.setState(resultSet.getString(6));
                guest.setZipCode(resultSet.getString(7));
                guest.setCountry(resultSet.getString(8));
                guest.setPhoneNumber(resultSet.getString(9));
                guest.setEmail(resultSet.getString(10));
                guest.setGender(resultSet.getString(11));

                guests.add(guest);
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
            statement.setString(counter++, guest.getGuest_firstName());
            statement.setString(counter++, guest.getGuest_lastName());
            statement.setString(counter++, guest.getAddress());
            statement.setString(counter++, guest.getCity());
            statement.setString(counter++, guest.getState());
            statement.setString(counter++, guest.getZipCode());
            statement.setString(counter++, guest.getCountry());
            statement.setString(counter++, guest.getPhoneNumber());
            statement.setString(counter++, guest.getEmail());
            statement.setString(counter++, guest.getGender());

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