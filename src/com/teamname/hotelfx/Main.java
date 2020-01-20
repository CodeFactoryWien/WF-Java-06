package com.teamname.hotelfx;

import com.teamname.hotelfx.dbAccess.Backup;
import com.teamname.hotelfx.dbAccess.HotelfxAccess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("bookingWindow.fxml"));
        Parent root = loader.load();
        root.getStylesheets().add(getClass().getResource("ressources/style.css").toExternalForm());
        primaryStage.setTitle("Hotel FX");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {

        try {
            HotelfxAccess.getDBConnection();
        } catch (Exception e) {
//           displayException(e);
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


