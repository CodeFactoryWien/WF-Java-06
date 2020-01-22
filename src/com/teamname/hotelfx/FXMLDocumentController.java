package com.teamname.hotelfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 * @author Bushan Sirgur
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField textUser;

    @FXML
    private PasswordField textPassword;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public FXMLDocumentController() {
        connection = ConnectionUtil.cdb();
    }


    public void loginAction(ActionEvent event) {
        String user = textUser.getText().toString();
        String password = textPassword.getText().toString();

        String sql = "SELECT * FROM employee WHERE user = ? and pass = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                infoBox("Please enter correct Username and Password", null, "Failed");
            } else {
                System.setProperty("prism.lcdtext", "false");
                System.setProperty("prism.text", "t2k");
                scene = new Scene(FXMLLoader.load(getClass().getResource("bookingWindow.fxml")));
                scene.getStylesheets().add(getClass().getResource("ressources/style.css").toExternalForm());
                infoBox("Login Successfull", null, "Success");
                Node node = (Node) event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                dialogStage.getIcons().add(new Image(Main.class.getResourceAsStream("ressources/icon.png")));
                dialogStage.setTitle("Hotel FX");
                dialogStage.setMaximized(true);
                dialogStage.setScene(scene);
                dialogStage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}