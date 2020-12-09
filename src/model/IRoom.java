package model;

/**
 * @author shubham sharma
 *         <p>
 *         03/10/20
 */
public interface IRoom {
    public String getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
    
    default boolean isFree() {
        return false;
    }
}
