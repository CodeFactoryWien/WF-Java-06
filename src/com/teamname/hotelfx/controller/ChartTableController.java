package com.teamname.hotelfx.controller;


import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
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
    @FXML
    public StackedBarChart<Number, String> areaChart;
    @FXML
    public NumberAxis xAxis;
    @FXML
    public CategoryAxis yAxis;


    final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String DB_DATABASE = "hotelfx";
    final String DB_CONNECTION = "jdbc:mysql://localhost/" + DB_DATABASE + "?useLegacyDatetimeCode=false&serverTimezone=UTC";
    final String DB_USER = "root";
    final String DB_PASSWORD = "";
    String myDate = "2020-10-29 00:00:00";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = sdf.parse(myDate);
    long millis = date.getTime();

    public ChartTableController() throws ParseException {
    }

    private void overviewTable() {

        chart_tableView.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = HotelfxAccess.getDBConnection();

            String SQLQ = "SELECT rooms.roomNumber, bookings.dateFrom, bookings.dateTo, guests.firstName, guests.lastName " +
                    "FROM rooms " +
                    "LEFT JOIN roomsbooked ON rooms.roomID = roomsbooked.roomID " +
                    "LEFT JOIN bookings ON roomsbooked.fk_bookingID = bookings.bookingID " +
                    "LEFT JOIN guests ON guests.guestID       = bookings.fk_guestID";

            ResultSet rs = c.createStatement().executeQuery(SQLQ);


            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {

                int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));


                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(Objects.toString(param.getValue().get(j), "----"));
                    }
                });
                chart_tableView.getColumns().addAll(col);
            }

            //ObservableList
            while (rs.next()) {
//                ResultSet columns = c.getMetaData().getColumns(null, null, null, null);
                ObservableList<String> row = FXCollections.observableArrayList();

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);

            }

            chart_tableView.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAreaChart() throws SQLException, ClassNotFoundException, ParseException {

        final String austria = "001";
        final String brazil = "002";
        final String france = "009";
        final String italy = "014";
        final String usa = "020";

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final StackedBarChart<Number, String> sbc = new StackedBarChart<Number, String>(yAxis, xAxis);
        final XYChart.Series<Number, String> series1 = new XYChart.Series<Number, String>();
        final XYChart.Series<Number, String> series2 = new XYChart.Series<Number, String>();
        final XYChart.Series<Number, String> series3 = new XYChart.Series<Number, String>();
        StackedBarChart series = new StackedBarChart<Number, String>(yAxis, xAxis);
        series.getData().add(new XYChart.Data<Number, String>(25, austria));
        areaChart.setCategoryGap(10);


        xAxis.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList(austria, brazil, france, italy, usa)));
        yAxis.setLabel("Value");
        series1.setName("");
        series1.getData().add(new XYChart.Data<Number, String>(251.34, austria));
        series1.getData().add(new XYChart.Data<Number, String>(20.82, austria));
        series1.getData().add(new XYChart.Data<Number, String>(10, austria));
        series1.getData().add(new XYChart.Data<Number, String>(357.15, austria));
        series2.setName("");
        series2.getData().add(new XYChart.Data<Number, String>(57401.85, usa));
        series2.getData().add(new XYChart.Data<Number, String>(41941.19, italy));
        series2.getData().add(new XYChart.Data<Number, String>(45263.37, brazil));
        series2.getData().add(new XYChart.Data<Number, String>(117320.16, usa, austria));
        series2.getData().add(new XYChart.Data<Number, String>(14845.27, france));
        series3.setName("");
        series3.getData().add(new XYChart.Data<Number, String>(45000.65, austria));
        series3.getData().add(new XYChart.Data<Number, String>(44835.76, austria));
        series3.getData().add(new XYChart.Data<Number, String>(18722.18, italy));
        series3.getData().add(new XYChart.Data<Number, String>(17557.31, france));
        series3.getData().add(new XYChart.Data<Number, String>(92633.68, austria));
        areaChart.getData().addAll(series1, series2, series3);

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chart_tableView.getColumns().clear();
        overviewTable();
        try {
            setAreaChart();
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }


    }
}

/*
package com.teamname.hotelfx.controller;


import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import com.teamname.hotelfx.diagramm.GanttChart;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
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
    @FXML
    public StackedBarChart<Number, String> areaChart;
    @FXML
    public NumberAxis xAxis;
    @FXML
    public CategoryAxis yAxis;


    final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String DB_DATABASE = "hotelfx";
    final String DB_CONNECTION = "jdbc:mysql://localhost/" + DB_DATABASE + "?useLegacyDatetimeCode=false&serverTimezone=UTC";
    final String DB_USER = "root";
    final String DB_PASSWORD = "";
    String myDate = "2020-10-29 00:00:00";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = sdf.parse(myDate);
    long millis = date.getTime();

    public ChartTableController() throws ParseException {
    }

    private void overviewTable() {

        chart_tableView.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = HotelfxAccess.getDBConnection();

            String SQLQ = "SELECT rooms.roomNumber, bookings.dateFrom, bookings.dateTo, guests.firstName, guests.lastName " +
                    "FROM rooms " +
                    "LEFT JOIN roomsbooked ON rooms.roomID = roomsbooked.roomID " +
                    "LEFT JOIN bookings ON roomsbooked.fk_bookingID = bookings.bookingID " +
                    "LEFT JOIN guests ON guests.guestID       = bookings.fk_guestID";

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
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(Objects.toString(param.getValue().get(j), "----"));
                    }
                });
                chart_tableView.getColumns().addAll(col);
            }

            //ObservableList
            while (rs.next()) {
//                ResultSet columns = c.getMetaData().getColumns(null, null, null, null);
                System.out.println("-");
                ObservableList<String> row = FXCollections.observableArrayList();

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);

            }

            chart_tableView.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAreaChart() throws SQLException, ClassNotFoundException, ParseException {

        final String austria = "Austria";
        final String brazil = "Brazil";
        final String france = "France";
        final String italy = "Italy";
        final String usa = "USA";

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final StackedBarChart<Number, String> sbc = new StackedBarChart<Number, String>(yAxis, xAxis);
        final XYChart.Series<Number, String> series1 = new XYChart.Series<Number, String>();
        final XYChart.Series<Number, String> series2 = new XYChart.Series<Number, String>();
        final XYChart.Series<Number, String> series3 = new XYChart.Series<Number, String>();
        StackedBarChart series = new StackedBarChart<Number, String>(yAxis, xAxis);
        series.getData().add(new XYChart.Data<Number, String>(25, austria));
        areaChart.setCategoryGap(10);


        xAxis.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList(austria, brazil, france, italy, usa)));
        yAxis.setLabel("Value");
        series1.setName("2003");
        series1.getData().add(new XYChart.Data<Number, String>(251.34, austria));
        series1.getData().add(new XYChart.Data<Number, String>(20.82, austria));
        series1.getData().add(new XYChart.Data<Number, String>(10, austria));
        series1.getData().add(new XYChart.Data<Number, String>(357.15, austria));
        series2.setName("2004");
        series2.getData().add(new XYChart.Data<Number, String>(57401.85, usa));
        series2.getData().add(new XYChart.Data<Number, String>(41941.19,italy));
        series2.getData().add(new XYChart.Data<Number, String>(45263.37,brazil));
        series2.getData().add(new XYChart.Data<Number, String>(117320.16,usa, austria));
        series2.getData().add(new XYChart.Data<Number, String>(14845.27, france));
        series3.setName("2005");
        series3.getData().add(new XYChart.Data<Number, String>(45000.65, austria));
        series3.getData().add(new XYChart.Data<Number, String>(44835.76, austria));
        series3.getData().add(new XYChart.Data<Number, String>(18722.18, italy));
        series3.getData().add(new XYChart.Data<Number, String>(17557.31, france));
        series3.getData().add(new XYChart.Data<Number, String>(92633.68, austria));
        areaChart.getData().addAll(series1, series2, series3);

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chart_tableView.getColumns().clear();
        overviewTable();
        try {
            setAreaChart();
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }


    }
    }



 */

/*
package com.teamname.hotelfx.controller;


import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import com.teamname.hotelfx.diagramm.GanttChart;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
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
    @FXML
    private AreaChart<Number,String> areaChart;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private CategoryAxis yAxis;


    final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String DB_DATABASE = "hotelfx";
    final String DB_CONNECTION = "jdbc:mysql://localhost/" + DB_DATABASE + "?useLegacyDatetimeCode=false&serverTimezone=UTC";
    final String DB_USER = "root";
    final String DB_PASSWORD = "";
    String myDate = "2020-10-29 00:00:00";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = sdf.parse(myDate);
    long millis = date.getTime();

    public ChartTableController() throws ParseException {
    }

    private void overviewTable() {
        chart_tableView.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = HotelfxAccess.getDBConnection();

            String SQLQ = "SELECT rooms.roomNumber, bookings.dateFrom, bookings.dateTo, guests.firstName, guests.lastName " +
                    "FROM rooms " +
                    "LEFT JOIN roomsbooked ON rooms.roomID = roomsbooked.roomID " +
                    "LEFT JOIN bookings ON roomsbooked.fk_bookingID = bookings.bookingID " +
                    "LEFT JOIN guests ON guests.guestID       = bookings.fk_guestID";

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
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(Objects.toString(param.getValue().get(j), "----"));
                    }
                });
                chart_tableView.getColumns().addAll(col);
            }

            //ObservableList
            while (rs.next()) {
//                ResultSet columns = c.getMetaData().getColumns(null, null, null, null);
                System.out.println("-");
                ObservableList<String> row = FXCollections.observableArrayList();

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);

            }

            chart_tableView.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAreaChart() throws SQLException, ClassNotFoundException, ParseException {
        String[] rooms = new String[] { "Room 1", "Room 2", "Room 3", "Room 4", "Room 5", "Room 6", "Room 7", "Room 8", "Room 9", "Room 10",
                "Room 11", "Room 12", "Room 13", "Room 14", "Room 15", "Room 16", "Room 17", "Room 18", "Room 19", "Room 20",
                "Room 21", "Room 22", "Room 23", "Room 24", "Room 25", "Room 26", "Room 27", "Room 28", "Room 29", "Room 30",
                "Room 31", "Room 32", "Room 33", "Room 34", "Room 35", "Room 36", "Room 37", "Room 38", "Room 39", "Room 40"
        };

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();

        final GanttChart<Number,String> chart = new GanttChart<Number,String>(xAxis,yAxis);
        xAxis.setLabel("");
        xAxis.setTickLabelFill(Color.CHOCOLATE);
        xAxis.setMinorTickCount(4);

        yAxis.setLabel("");
        yAxis.setTickLabelFill(Color.CHOCOLATE);
        yAxis.setTickLabelGap(10);
        yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(rooms)));

        chart.setTitle("Room Monitoring");
        chart.setLegendVisible(false);
        chart.setBlockHeight( 25);
        String room;
        XYChart.Series[][] series = new XYChart.Series[40][10];
        Instant date2 = Instant.ofEpochMilli(millis);
        LocalDateTime utc = LocalDateTime.ofInstant(date2, ZoneOffset.UTC);
        System.out.println(date2);

        //------------------------

        PreparedStatement pstmnt;
        Class.forName(HotelfxAccess.DB_DRIVER);
        Connection connection = null;
        connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);



        String qcount = "SELECT bookings.dateFrom, bookings.dateTo FROM bookings";
        Connection DBConnect = DriverManager.getConnection(HotelfxAccess.DB_CONNECTION, HotelfxAccess.DB_USER, HotelfxAccess.DB_PASSWORD);
        pstmnt = DBConnect.prepareStatement(qcount);
        try
        {
            ResultSet rs = pstmnt.executeQuery(qcount);
            while(rs.next())
            {
                String dateData = (rs.getString("dateFrom"));
                //int count= rec.getInt("COUNT(date)");
                System.out.println("DateFrom: " + dateData);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        //-----------------------------------------------------
        sdf.parse(myDate);
        System.out.println(myDate);

        for (int i = 0; i < 40; i++) {

            //int randomNum = ThreadLocalRandom.current().nextInt(-9, 10);
            for(int j = 0; j < 2; j++) {

                room = rooms[i];
                series[i][j] = new XYChart.Series();

                series[i][j].getData().add(new XYChart.Data("1", 29));


                areaChart.getData().addAll(series[i][j]);

            }
        }                areaChart.getStylesheets().add(getClass().getResource("ressources/style.css").toExternalForm());
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chart_tableView.getColumns().clear();
        overviewTable();
        try {
            setAreaChart();
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }


    }
    }
 */