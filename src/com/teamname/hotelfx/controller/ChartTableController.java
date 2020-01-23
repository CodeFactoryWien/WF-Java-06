package com.teamname.hotelfx.controller;

import com.teamname.hotelfx.data.Booking;
import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import com.teamname.hotelfx.diagramm.Overview;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ChartTableController {
    @FXML
    private TableView<Booking> tableView = new TableView();
    @FXML
    private TableColumn Room_number = new TableColumn();
    @FXML
    private TableColumn startDate = new TableColumn();
    @FXML
    private TableColumn endDate = new TableColumn();
    @FXML
    private TableColumn guest_firstName = new TableColumn();
    @FXML
    private TableColumn guest_lastName = new TableColumn();
    @FXML
    private TableView<Overview> chart_tableView;
    @FXML
    private List<String> listOverview = FXCollections.observableArrayList();

//    private String overviewColumnsSQL = "SELECT * FROM bookings";
//    private String overviewColumnsSQL = "SELECT rooms.roomNumber, bookings.dateFrom, bookings.dateTo, guests.firstName, guests.lastName FROM rooms " +
//            "LEFT JOIN roomstatus ON roomstatus.roomStatusID = rooms.fk_roomStatusID " +
//            "LEFT JOIN roomtype ON roomtype.roomtypeID = rooms.fk_roomTypeID " +
//            "LEFT JOIN hotels ON hotels.hotelID = rooms.fk_hotelID";

    private String overviewColumnsSQL = "SELECT rooms.roomNumber FROM rooms" +
            "SELECT bookings.dateFrom, bookings.dateTo FROM bookings" +
            "SELECT guests.firstName, guests.lastName";

    public void initialize() throws SQLException, ParseException {
        HotelfxAccess.getDBConnection();
        HotelfxAccess.addColumnsToTable(HotelfxAccess.getColumnNames(overviewColumnsSQL), chart_tableView);



        /*connect in application booking list to check in booking tableView*/
//    chart_tableView.setItems(listOverview.getInstance().getOverviewList());

//    Controller ctrl = new Controller;
        chart_tableView.getSelectionModel().selectFirst();
    }
};







/*

    //TABLE VIEW AND DATA
    private ObservableList<ObservableList> data;
    private TableView tableview;
    private DatagramSocket DBConnect;


    @FXML
    public TableView chart_tableView;

    //CONNECTION DATABASE
    public void chart_tableView(){

        System.out.println("<<<<<<<<xxx>>>>>>>>");
        Connection c ;
        data = FXCollections.observableArrayList();
        try{
            c = HotelfxAccess.getDBConnection();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * FROM bookings";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);


            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                chart_tableView.getColumns().add(col);
                System.out.println("Column ["+i+"] ");
            }

            // Add Data
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row:" + row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
//            tableview.setItems(data);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
 */