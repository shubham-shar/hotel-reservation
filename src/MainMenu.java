import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.NoSuchElementException;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

/**
 * @author shubham sharma
 *         <p>
 *         04/10/20
 */
public class MainMenu {
    
    public static final HotelResource HOTEL_RESOURCE = HotelResource.getHotelResourceInstance();
    
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        startFlow(input);
    }
    
    public static void startFlow(BufferedReader input) throws IOException {
        boolean continueToEnterOptions = true;
        boolean changeToAdminMenu = false;
        do {
            printOptions();
            int a = Integer.parseInt(input.readLine());
            switch (a) {
            case 1 -> findAndReserveRoom(input);
            case 2 -> printAllReservations(input);
            case 3 -> addCustomerFlow(input);
            case 4 -> {
                System.out.println("\nChanging to Admin Menu");
                continueToEnterOptions = false;
                changeToAdminMenu = true;
            }
            case 5 -> {
                System.out.println("\nExiting the application");
                continueToEnterOptions = false;
            }
            default -> System.out.println("\nInvalid option entered, please try again");
            }
        } while (continueToEnterOptions);
        
        if(changeToAdminMenu) {
            AdminMenu.startFlow(input);
        }
        
        System.out.println("\n\n\t\tThanks for using our service.\n");
    }
    
    private static void printAllReservations(BufferedReader input) throws IOException {
        System.out.println("\nEnter your email");
        String email = input.readLine();
        
        try {
            Collection<Reservation> customersReservations = HOTEL_RESOURCE.getCustomersReservations(email);
            if(customersReservations.size() == 0) {
                System.out.println("Seems like you have not booked any room yet, Please book a room first and then "
                                           + "try again!!!");
                return;
            }
            System.out.println("\nYour reservations:");
            customersReservations.forEach(System.out::println);
        } catch (NoSuchElementException ex) {
            System.out.println("No customer with email " + email + " found, please try again!!");
        }
    }
    
    private static void findAndReserveRoom(BufferedReader input) throws IOException {
        System.out.println("\nEnter the check-in Date in format dd-MM-yyyy ex. 06-10-2020");
        String date = input.readLine();
        System.out.println("\nEnter the check-out Date in format dd-MM-yyyy ex. 06-10-2020");
        String inputDate = input.readLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date checkInDate;
        Date checkOutDate;
        try {
            checkInDate = dateFormat.parse(date);
            checkOutDate = dateFormat.parse(inputDate);
            if(checkInDate.after(checkInDate)) {
                System.out.println("Check-In date can't be ahead of Checkout Date, try again!!");
                return;
            }
            Collection<IRoom> aRoom = HOTEL_RESOURCE.findARoom(checkInDate, checkOutDate);
            if(aRoom.size() == 0) {
                System.out.println("Seems like all rooms are booked, try after some other dates");
                return;
            }
            aRoom.forEach(System.out::println);
            reserveRoom(input, checkInDate, checkOutDate);
        } catch (ParseException e) {
            System.out.println("Format of entered date not correct");
        }
    }
    
    private static void reserveRoom(BufferedReader input, Date checkInDate, Date checkOutDate) throws IOException {
        System.out.println("\nEnter your email");
        String email = input.readLine();
    
        System.out.println("Enter the room number from above list that you want to book");
        String roomNumber = input.readLine();
        IRoom room = null;
        try {
            room = HOTEL_RESOURCE.getRoom(roomNumber);
        } catch (NoSuchElementException ex) {
            System.out.println("No such room found. please try again");
            return;
        }
        try {
            Reservation reservation = HOTEL_RESOURCE.bookARoom(email, room, checkInDate, checkOutDate);
            System.out.println("Reserved room " + reservation);
        } catch (NoSuchElementException ex) {
            System.out.println("No customer with email " + email + " found, please try again!!");
        }
    }
    
    public static void addCustomerFlow(BufferedReader scanner) throws IOException {
        System.out.println("\nEnter your email");
        String email = scanner.readLine();
        
        System.out.println("Enter your first name");
        String firstName = scanner.readLine();
        
        System.out.println("Enter your Last name");
        String lastName = scanner.readLine();
        
        HOTEL_RESOURCE.createACustomer(email, firstName, lastName);
        System.out.println("\nAccount Created.\n");
        
    }
    
    private static void printOptions() {
        System.out.println("Please choose a valid option:");
        System.out.println("1. Find and Reserve Room.");
        System.out.println("2. Check all of your reservations");
        System.out.println("3. Create an Account.");
        System.out.println("4. Go to Admin");
        System.out.println("5. Exit application.");
    }
}
