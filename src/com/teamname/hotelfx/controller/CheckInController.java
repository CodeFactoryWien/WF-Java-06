package com.teamname.hotelfx.controller;

import com.teamname.hotelfx.Controller;
import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CheckInController {
    @FXML
    private TableView bookings_tableView;
    private List<String> bookingTableColumnNames = new ArrayList<>();

    public void initialize() throws SQLException, ParseException {
        bookingTableColumnNames.add("startDate");
        bookingTableColumnNames.add("endDate");
        bookingTableColumnNames.add("guestID");
        bookingTableColumnNames.add("agentID");
        bookingTableColumnNames.add("hotelID");

        HotelfxAccess.addColumnsToTable(bookingTableColumnNames, bookings_tableView);
    }
}
