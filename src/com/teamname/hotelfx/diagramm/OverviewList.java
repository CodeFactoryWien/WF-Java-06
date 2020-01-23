package com.teamname.hotelfx.diagramm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OverviewList {
    private ObservableList<Overview> overviewList = FXCollections.observableArrayList();
    private static final OverviewList instance = new OverviewList();

    private OverviewList() {
    }

    public ObservableList<Overview> getOverviewList() {
        return overviewList;
    }

    public static OverviewList getInstance() {
        return instance;
    }
}
