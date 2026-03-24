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

class BookingHistory {
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}

class BookingReportService {

    public void showAllBookings(List<Reservation> reservations) {
        if (reservations.isEmpty()) {
            System.out.println("No bookings available");
            return;
        }

        for (Reservation r : reservations) {
            System.out.println("Reservation ID: " + r.getReservationId());
            System.out.println("Guest Name: " + r.getGuestName());
            System.out.println("Room Type: " + r.getRoomType());
            System.out.println();
        }
    }

    public void generateSummary(List<Reservation> reservations) {
        System.out.println("Total Bookings: " + reservations.size());
    }
}

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("RES101", "Rahul", "Deluxe");
        Reservation r2 = new Reservation("RES102", "Anita", "Suite");
        Reservation r3 = new Reservation("RES103", "Karan", "Standard");

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        BookingReportService reportService = new BookingReportService();

        reportService.showAllBookings(history.getReservations());
        reportService.generateSummary(history.getReservations());
    }
}