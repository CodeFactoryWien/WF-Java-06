package com.teamname.hotelfx.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {
    private final IntegerProperty roomID = new SimpleIntegerProperty(this, "roomID");
    public IntegerProperty roomIDProperty(){
        return roomID;
    }
    private final IntegerProperty floor = new SimpleIntegerProperty(this, "floor");
    public IntegerProperty floorProperty(){
        return floor;
    }

    private final StringProperty roomNumber = new SimpleStringProperty(this, "roomNumber");
    public StringProperty roomNumberProperty(){
        return roomNumber;
    }

    private final StringProperty description = new SimpleStringProperty(this, "description");
    public StringProperty descriptionProperty(){
        return description;
    }

    private final StringProperty roomStatus = new SimpleStringProperty(this, "roomStatus");
    public StringProperty roomStatusProperty(){
        return roomStatus;
    }

    private final StringProperty roomType = new SimpleStringProperty(this, "roomType");
    public StringProperty roomTypeProperty(){
        return roomType;
    }

    private final StringProperty hotelName = new SimpleStringProperty(this, "hotelName");
    public StringProperty hotelNameProperty(){
        return hotelName;
    }

    public Room(int roomId, int floor, String roomNumber, String desc, String status, String type, String hotelName) {
        setRoomID(roomId);
        setFloor(floor);
        setRoomNumber(roomNumber);
        setDescription(desc);
        setRoomStatus(status);
        setRoomType(type);
        setHotelName(hotelName);
    }

    public String getHotelName() {
        return hotelName.get();
    }

    public void setHotelName(String hotelName) {
        this.hotelName.set(hotelName);
    }

    public int getRoomID() {
        return roomID.get();
    }

    public void setRoomID(int roomID) {
        this.roomID.set(roomID);
    }

    public int getFloor() {
        return floor.get();
    }

    public void setFloor(int floor) {
        this.floor.set(floor);
    }

    public String getRoomNumber() {
        return roomNumber.get();
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber.set(roomNumber);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getRoomStatus() {
        return roomStatus.get();
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus.set(roomStatus);
    }

    public String getRoomType() {
        return roomType.get();
    }

    public void setRoomType(String roomType) {
        this.roomType.set(roomType);
    }
}
