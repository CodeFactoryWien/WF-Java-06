package com.teamname.hotelfx.controller;


import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class ChartTableController implements Initializable {
    public TableColumn Room_number;
    @FXML
    public TableColumn startDate;
    @FXML
    public TableColumn endDate;
    @FXML
    public TableColumn guest_firstName;
    @FXML
    public TableColumn guest_lastName;
    @FXML
    private ObservableList<ObservableList> data;

    @FXML
    private TableView chart_tableView;


    private void overviewTable() {
        chart_tableView.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = HotelfxAccess.getDBConnection();

//            String SQLC = "create table update";
//              String SQLQ = "SELECT R.roomID,\n" +
//                      "B.dateFrom, B.dateTo, G.firstName, G.lastName\n" +
//                      "FROM rooms  IS FALSE       R\n" +
//                      "LEFT JOIN roomsbooked RB ON R.roomID       = RB.roomID IS NOT null\n" +
//                      "LEFT JOIN bookings    B ON RB.fk_bookingID = B.bookingID IS NOT null\n" +
//                      "LEFT JOIN guests      G ON G.guestID       = B.fk_guestID IS NOT null\n" +
//                      "ORDER BY R.roomID";
            String SQLQ = "SELECT rooms.roomID, bookings.dateFrom, bookings.dateTo, guests.firstName, guests.lastName FROM rooms LEFT JOIN roomsbooked ON rooms.roomID = roomsbooked.roomID LEFT JOIN bookings ON roomsbooked.fk_bookingID = bookings.bookingID LEFT JOIN guests ON guests.guestID       = bookings.fk_guestID";

            //st.executeUpdate(SQLC);


            ResultSet rs = c.createStatement().executeQuery(SQLQ);
            System.out.println(rs.getMetaData().getColumnCount());

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {

                System.out.println("Hello");
                int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
//                System.out.println(rs.getMetaData().getColumnName(i + 1));

                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                chart_tableView.getColumns().addAll(col);
            }

            //ObservableList
            while (rs.next()) {
                System.out.println("Hwllo2");
                ObservableList<String> row = FXCollections.observableArrayList();

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    if (rs.getString(i) == null) {
                        rs.getString(i).isBlank();
                    }
                    ;
                    row.add(rs.getString(i));
                }
                data.add(row);
            }

            chart_tableView.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chart_tableView.getColumns().clear();
        overviewTable();


    }

}






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