import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import api.AdminResouce;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

/**
 * @author shubham sharma
 *         <p>
 *         03/10/20
 */
public class AdminMenu {
    
    public static final AdminMenu adminMenu = new AdminMenu();
    
    public static final HotelResource HOTEL_RESOURCE = HotelResource.getHotelResourceInstance();
    public static final AdminResouce ADMIN_RESOUCE = AdminResouce.getHotelResourceInstance();
    
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        startFlow(input);
    }
    
    public static void startFlow(BufferedReader input) throws IOException {
        boolean continueToEnterOptions = true;
        boolean returnToMainMenu = false;
        do {
            printOptions();
            int a = Integer.parseInt(input.readLine());
            switch (a) {
            case 1 -> {
                Collection<Customer> allCustomers = ADMIN_RESOUCE.getAllCustomers();
                if (allCustomers.size() == 0) {
                    System.out.println("\nNo customer present as of now\n");
                } else {
                    System.out.println("\n");
                    allCustomers.forEach(System.out::println);
                    System.out.println("\n");
                }
            }
            case 2 -> {
                Collection<IRoom> allRooms = ADMIN_RESOUCE.getAllRooms();
                if (allRooms.size() == 0) {
                    System.out.println("\nNo Room present as of now\n");
                } else {
                    allRooms.forEach(System.out::println);
                }
            }
            case 3 -> ADMIN_RESOUCE.displayAllReservations();
            case 4 -> {
                addHotelRooms(input);
            }
            case 5 -> {
                System.out.println("\nReturning to Main Menu.\n");
                continueToEnterOptions = false;
                returnToMainMenu = true;
            }
            default -> System.out.println("\nInvalid option entered, please try again");
            }
        } while (continueToEnterOptions);
        if (returnToMainMenu) {
            MainMenu.startFlow(input);
        }
    }
    
    private static void addHotelRooms(BufferedReader input) throws IOException {
        List<IRoom> rooms = new ArrayList<>();
        boolean continueAddingRoom = true;
        do {
            System.out.println("Enter the room number");
            String roomNumer = input.readLine();
            System.out.println("Enter the room price");
            double roomPrice = (double) 0;
            try {
                roomPrice = Double.parseDouble(input.readLine());
            } catch (NumberFormatException ex) {
                System.out.println("Format of price not correct, please try again");
                continue;
            }
            System.out.println("Enter Room type");
            RoomType roomType = RoomType.valueOf(input.readLine());
            Room room = new Room(roomNumer, roomPrice, roomType);
            rooms.add(room);
            System.out.println("To continue adding room, enter 'continue' or to stop enter 'no':");
            String continueCheck = input.readLine();
            continueAddingRoom = "continue".equals(continueCheck);
        } while (continueAddingRoom);
        ADMIN_RESOUCE.addRoom(rooms);
        System.out.println("adding rooms compeleted");
    }
    
    private static void printOptions() {
        System.out.println("Please choose a valid option:");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Back to Main Menu");
    }
}
