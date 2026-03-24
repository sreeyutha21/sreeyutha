import java.util.*;

class Reservation {
    private String reservationId;
    private String roomType;
    private String roomId;

    public Reservation(String reservationId, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);
    }

    public void increaseAvailability(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    public void display() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

class CancellationService {

    private Map<String, Reservation> confirmedBookings = new HashMap<>();
    private Stack<String> releasedRoomIds = new Stack<>();

    public void addBooking(Reservation r) {
        confirmedBookings.put(r.getReservationId(), r);
    }

    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!confirmedBookings.containsKey(reservationId)) {
            System.out.println("Cancellation Failed: Invalid Reservation ID");
            return;
        }

        Reservation r = confirmedBookings.get(reservationId);

        releasedRoomIds.push(r.getRoomId());

        inventory.increaseAvailability(r.getRoomType());

        confirmedBookings.remove(reservationId);

        System.out.println("Booking Cancelled: " + reservationId + " Room Released: " + r.getRoomId());
    }
}

public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        Reservation r1 = new Reservation("RES101", "Single Room", "SI101");
        Reservation r2 = new Reservation("RES102", "Double Room", "DO102");

        service.addBooking(r1);
        service.addBooking(r2);

        System.out.println("Before Cancellation:");
        inventory.display();

        System.out.println();

        service.cancelBooking("RES101", inventory);

        System.out.println();

        System.out.println("After Cancellation:");
        inventory.display();
    }
}