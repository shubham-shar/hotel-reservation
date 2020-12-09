package api;

import java.util.Collection;
import java.util.Date;
import java.util.NoSuchElementException;

import com.sun.jdi.request.DuplicateRequestException;
import Service.CustomerService;
import Service.ReservationService;
import model.Customer;
import model.IRoom;
import model.Reservation;

/**
 * @author shubham sharma
 *         <p>
 *         03/10/20
 */
public class HotelResource {
    
    public static final HotelResource hotelResource = new HotelResource();
    
    public static final CustomerService customerService = CustomerService.getInstance();
    public static final ReservationService reservationService = ReservationService.getInstance();
    
    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    
    public void createACustomer(String  email,  String firstName, String lastName){
        try {
            customerService.addCustomer(email, firstName, lastName);
        } catch (IllegalArgumentException ex) {
            System.out.println(
                    "Invalid email entered for customer, correct format is abc@abc.com, please try again!!!\n");
        } catch (DuplicateRequestException ex) {
            System.out.println("Customer with email " + email + "already present, Please try again!!!\n");
        }
    }
    
    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }
    
    public Reservation bookARoom(String email, IRoom room, Date checkInDate, Date CheckOutDate){
        Customer customer = customerService.getCustomer(email);
        return reservationService.reserveARoom(customer, room, checkInDate,checkInDate);
    }
    
    public Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);
    }
    
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn, checkOut);
    }
    
    public static HotelResource getHotelResourceInstance() {
        return hotelResource;
    }
}
