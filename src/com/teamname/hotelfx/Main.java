package com.teamname.hotelfx;

import com.teamname.hotelfx.dbAccess.Backup;
import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // improve rendering on larger screens
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("prism.text", "t2k");

        // change with FXMLDocument.fxml for logging try error

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        root.getStylesheets().add(getClass().getResource("ressources/style.css").toExternalForm());
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("ressources/icon.png")));
        primaryStage.setTitle("Hotel FX");
        primaryStage.setScene(new Scene(root));

        primaryStage.setMaximized(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        try {

        } catch (Exception e) {
//            displayException(e);
        }
    }

    @Override
    public void stop() {

        try {
            Backup.backUpTool();
            HotelfxAccess.closeDb();
        } catch (Exception e) {
//            displayException(e);
        }
    }
}


