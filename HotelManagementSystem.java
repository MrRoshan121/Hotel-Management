import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private boolean isBooked;
    private String customerName;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isBooked = false;
        this.customerName = "";
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookRoom(String customerName) {
        this.isBooked = true;
        this.customerName = customerName;
    }

    public void vacateRoom() {
        this.isBooked = false;
        this.customerName = "";
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " is " + (isBooked ? "booked by " + customerName : "available");
    }
}

public class HotelManagementSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeRooms(10); // Initialize with 10 rooms

        while (true) {
            System.out.println("\nHotel Management System");
            System.out.println("1. Check Room Availability");
            System.out.println("2. Book a Room");
            System.out.println("3. Vacate a Room");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    checkAvailability();
                    break;
                case 2:
                    bookRoom(scanner);
                    break;
                case 3:
                    vacateRoom(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please choose again.");
            }
        }
    }

    private static void initializeRooms(int numberOfRooms) {
        for (int i = 1; i <= numberOfRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    private static void checkAvailability() {
        System.out.println("\nRoom Availability:");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    private static void bookRoom(Scanner scanner) {
        System.out.print("\nEnter room number to book: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room room = getRoomByNumber(roomNumber);
        if (room != null && !room.isBooked()) {
            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine();
            room.bookRoom(customerName);
            System.out.println("Room " + roomNumber + " booked successfully by " + customerName);
        } else {
            System.out.println("Room " + roomNumber + " is not available.");
        }
    }

    private static void vacateRoom(Scanner scanner) {
        System.out.print("\nEnter room number to vacate: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room room = getRoomByNumber(roomNumber);
        if (room != null && room.isBooked()) {
            room.vacateRoom();
            System.out.println("Room " + roomNumber + " vacated successfully.");
        } else {
            System.out.println("Room " + roomNumber + " is not currently booked.");
        }
    }

    private static Room getRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}
