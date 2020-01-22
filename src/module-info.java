module WF.Java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;

    opens com.teamname.hotelfx;
    opens com.teamname.hotelfx.data;
    opens com.teamname.hotelfx.controller;
}