package com.teamname.hotelfx;

import com.teamname.hotelfx.data.Guest;
import com.teamname.hotelfx.data.Room;
import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class Controller {

    @FXML
    private TableView<Guest> guest_tableView;

    @FXML
    private TableView<Room> room_tableView;


    public void initialize() throws SQLException {

//        guest_listView.getSelectionModel().selectedIndexProperty().addListener(
//                new ListSelectChangeListener()
//        );


        addColumnsToTable(HotelfxAccess.getInstance().getColumnNames("guests"), guest_tableView);
        addColumnsToTable(HotelfxAccess.getInstance().getColumnNames("rooms"), room_tableView);

        room_tableView.getItems().setAll(HotelfxAccess.getInstance().getAllRooms());
    }


    public void addColumnsToTable(List<String> columnNames, TableView tableView){
        for (int i = 0; i < columnNames.size(); i++) {
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(
                    columnNames.get(i)
            );
            column.setCellValueFactory(new PropertyValueFactory<>("floor"));

            if(tableView == guest_tableView){
                column.prefWidthProperty().bind(tableView.widthProperty().divide(9));
            }else{
                column.prefWidthProperty().bind(tableView.widthProperty().divide(5));
            }
            tableView.getColumns().add(column);
        }

    }


}
