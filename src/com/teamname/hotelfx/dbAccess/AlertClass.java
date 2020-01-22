package com.teamname.hotelfx.dbAccess;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;


class AlertClass extends Application {

    // launch the application
    public void start(Stage s) {
        // set title for the stage
        s.setTitle("creating alerts");

        // create a button
        Button b = new Button("Confirmation alert");
        Button b1 = new Button("error alert");
        Button b2 = new Button("Information alert");
        Button b3 = new Button("Warning alert");

        // create a tile pane
        TilePane r = new TilePane();

        // create a alert
        Alert a = new Alert(AlertType.NONE);

        // action event
        EventHandler<ActionEvent> event = new
                EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        // set alert type
                        a.setAlertType(AlertType.CONFIRMATION);

                        // show the dialog
                        a.show();
                    }
                };

        // action event
        EventHandler<ActionEvent> event1 = new
                EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        // set alert type
                        a.setAlertType(AlertType.ERROR);

                        // show the dialog
                        a.show();
                    }
                };

        // action event
        EventHandler<ActionEvent> event2 = new
                EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        // set alert type
                        a.setAlertType(AlertType.INFORMATION);

                        // show the dialog
                        a.show();
                    }
                };

        // action event
        EventHandler<ActionEvent> event3 = new
                EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        // set alert type
                        a.setAlertType(AlertType.WARNING);

                        // show the dialog
                        a.show();
                    }
                };
    }
}