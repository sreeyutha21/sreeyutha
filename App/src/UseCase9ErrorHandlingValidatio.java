import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Standard", 2);
        rooms.put("Deluxe", 2);
        rooms.put("Suite", 1);
    }

    public void validateRoom(String roomType) throws InvalidBookingException {
        if (!rooms.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (rooms.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }
    }

    public void bookRoom(String roomType) throws InvalidBookingException {
        validateRoom(roomType);
        rooms.put(roomType, rooms.get(roomType) - 1);
        System.out.println("Room booked successfully: " + roomType);
    }
}

public class UseCase9ErrorHandlingValidatio{

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        String[] bookingRequests = {"Deluxe", "Suite", "Suite", "Premium"};

        for (String roomType : bookingRequests) {
            try {
                inventory.bookRoom(roomType);
            } catch (InvalidBookingException e) {
                System.out.println("Booking Failed: " + e.getMessage());
            }
        }
    }
}