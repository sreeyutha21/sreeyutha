/**
 * Book My Stay - Booking Request Queue
 * @author Student
 * @version 5.0
 */
import java.util.*;

class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Request added: " + reservation.getReservationId());
    }

    public void showQueue() {
        for (Reservation r : queue) {
            System.out.println(r.getReservationId() + " | " + r.getGuestName() + " | " + r.getRoomType());
        }
    }
}

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("RES101", "Rahul", "Single Room");
        Reservation r2 = new Reservation("RES102", "Anita", "Double Room");
        Reservation r3 = new Reservation("RES103", "Karan", "Suite Room");

        requestQueue.addRequest(r1);
        requestQueue.addRequest(r2);
        requestQueue.addRequest(r3);

        System.out.println();

        System.out.println("Booking Requests in Queue:");
        requestQueue.showQueue();
    }
}