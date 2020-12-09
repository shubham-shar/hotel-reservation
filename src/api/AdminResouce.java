package api;

import java.util.Collection;
import java.util.List;

import Service.CustomerService;
import Service.ReservationService;
import model.Customer;
import model.IRoom;

/**
 * @author shubham sharma
 *         <p>
 *         04/10/20
 */
public class AdminResouce {
    
    public static final AdminResouce adminResouce = new AdminResouce();
    
    public static final CustomerService CUSTOMER_SERVICE = CustomerService.getInstance();
    
    public static final ReservationService RESERVATION_SERVICE = ReservationService.getInstance();
    
    public Customer getCustomer(String email){return CUSTOMER_SERVICE.getCustomer(email);}
    
    public void addRoom(List<IRoom> rooms){
        rooms.forEach(RESERVATION_SERVICE::addRoom);
    }
    
    public Collection<IRoom> getAllRooms(){return RESERVATION_SERVICE.getAllRooms();}
    
    public Collection<Customer> getAllCustomers(){return CUSTOMER_SERVICE.getAllCustomers();}
    
    public void displayAllReservations(){
        RESERVATION_SERVICE.printAllReservations();
    }
    
    public static AdminResouce getHotelResourceInstance() {
        return adminResouce;
    }
    
}
