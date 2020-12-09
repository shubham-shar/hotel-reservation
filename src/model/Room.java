package model;

import java.util.Objects;

/**
 * @author shubham sharma
 *         <p>
 *         03/10/20
 */
public class Room implements IRoom{
    
    private String roomNumber;
    private Double price;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return roomNumber.equals(room.roomNumber) && price.equals(room.price) && roomType == room.roomType;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, price, roomType);
    }
    
    private RoomType roomType;
    
    
    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }
    
    @Override
    public Double getRoomPrice() {
        return this.price;
    }
    
    @Override
    public RoomType getRoomType() {
        return this.roomType;
    }
    
    @Override
    public boolean isFree() {
        return price == 0;
    }
    
    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }
    
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
    
    @Override
    public String toString() {
        return "Room{" + "roomNumber='" + roomNumber + '\'' + ", price=" + price + ", roomType=" + roomType + '}';
    }
}
