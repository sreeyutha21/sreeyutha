import java.util.*;

class Reservation {
    private String reservationId;
    private String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public synchronized boolean bookRoom(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);
        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        }
        return false;
    }

    public void display() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

class BookingQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public synchronized void addRequest(Reservation r) {
        queue.add(r);
    }

    public synchronized Reservation getRequest() {
        return queue.poll();
    }

    public synchronized boolean hasRequests() {
        return !queue.isEmpty();
    }
}

class BookingProcessor extends Thread {
    private BookingQueue queue;
    private RoomInventory inventory;

    public BookingProcessor(BookingQueue queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {
        while (true) {
            Reservation r;
            synchronized (queue) {
                if (!queue.hasRequests()) {
                    break;
                }
                r = queue.getRequest();
            }

            if (r != null) {
                boolean success = inventory.bookRoom(r.getRoomType());
                if (success) {
                    System.out.println(Thread.currentThread().getName() + " Booked " + r.getReservationId());
                } else {
                    System.out.println(Thread.currentThread().getName() + " Failed " + r.getReservationId());
                }
            }
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingQueue queue = new BookingQueue();

        queue.addRequest(new Reservation("RES101", "Single Room"));
        queue.addRequest(new Reservation("RES102", "Single Room"));
        queue.addRequest(new Reservation("RES103", "Single Room"));
        queue.addRequest(new Reservation("RES104", "Double Room"));
        queue.addRequest(new Reservation("RES105", "Suite Room"));

        BookingProcessor t1 = new BookingProcessor(queue, inventory);
        BookingProcessor t2 = new BookingProcessor(queue, inventory);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        System.out.println();
        System.out.println("Final Inventory:");
        inventory.display();
    }
}