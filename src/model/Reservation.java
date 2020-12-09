package model;

import java.util.Date;
import java.util.Objects;

/**
 * @author shubham sharma
 *         <p>
 *         03/10/20
 */
public class Reservation {
    private Customer customer;
    private IRoom iRoom;
    private Date checkInDate;
    private Date checkOutDate;
    
    public Reservation(Customer customer, IRoom iRoom, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.iRoom = iRoom;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    
    @Override
    public String toString() {
        return "Reservation{" + "customer=" + customer + ", iRoom=" + iRoom + ", checkInDate=" + checkInDate
                + ", checkOutDate=" + checkOutDate + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reservation that = (Reservation) o;
        return customer.equals(that.customer) && iRoom.equals(that.iRoom) && checkInDate.equals(that.checkInDate)
                && checkOutDate.equals(that.checkOutDate);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(customer, iRoom, checkInDate, checkOutDate);
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public IRoom getiRoom() {
        return iRoom;
    }
    
    public void setiRoom(IRoom iRoom) {
        this.iRoom = iRoom;
    }
    
    public Date getCheckInDate() {
        return checkInDate;
    }
    
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }
    
    public Date getCheckOutDate() {
        return checkOutDate;
    }
    
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
