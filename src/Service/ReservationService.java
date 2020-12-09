package Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

/**
 * @author shubham sharma
 *         <p>
 *         03/10/20
 */
public class ReservationService {
    
    private static List<IRoom> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static List<String> existingRooms = new ArrayList<>();
    
    public static final ReservationService reservationService = new ReservationService();
    
    public void addRoom(IRoom room) {
        if (existingRooms.contains(room.getRoomNumber())) {
            System.out.println("Room " + room.getRoomNumber() + " already exists");
            return;
        }
        rooms.add(room);
    }
    
    public IRoom getARoom(String roomId) {
        return rooms.stream().filter(iRoom -> roomId.equalsIgnoreCase(iRoom.getRoomNumber())).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Room " + roomId + " not found"));
    }
    
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        return new Reservation(customer, room, checkInDate, checkOutDate);
    }
    
    public List<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return reservations.stream().filter(reservation -> {
            if (checkInDate.after(reservation.getCheckInDate()) && checkInDate.before(reservation.getCheckOutDate())) {
                return false;
            } else if (checkOutDate.after(reservation.getCheckInDate()) && checkOutDate
                    .before(reservation.getCheckOutDate())) {
                return false;
            }
            return true;
        }).map(Reservation::getiRoom).collect(Collectors.toList());
    }
    
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return reservations.stream().filter(reservation -> customer.getEmail().equalsIgnoreCase(
                reservation.getCustomer().getEmail())).collect(Collectors.toList());
    }
    
    public void printAllReservations() {
        if(reservations.size() == 0) {
            System.out.println("No reservations done yet, try again after sometime!!");
        }
        reservations.forEach(System.out::println);
    }
    
    public List<IRoom> getAllRooms() {
        return rooms;
    }
    
    public static ReservationService getInstance() {
        return reservationService;
    }
}
