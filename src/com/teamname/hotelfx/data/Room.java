package com.teamname.hotelfx.data;

public class Room {
    private int roomID;
    private int floor;
    private String roomNumber;
    private String description;

    public Room(int roomID,int floor, String roomNumber, String description) {
        this.roomID = roomID;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.description = description;
    }
}
