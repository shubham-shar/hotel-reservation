package model;

/**
 * @author shubham sharma
 *         <p>
 *         03/10/20
 */
public class FreeRoom extends Room{
    
    public FreeRoom(String roomNumber, Double price, RoomType roomType) {
        super(roomNumber, price, roomType);
        this.setPrice(Double.valueOf("0"));
    }
    
    @Override
    public boolean isFree() {
        return true;
    }
    
    @Override
    public String toString() {
        return "FreeRoom{" + "roomNumber='" + getRoomNumber() + '\'' + ", price=" + getRoomPrice()
                + ", roomType=" + getRoomType() + '}';
    }
}
