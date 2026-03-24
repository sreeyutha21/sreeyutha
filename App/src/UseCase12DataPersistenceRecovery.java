import java.io.*;
import java.util.*;

class Reservation implements Serializable {
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

class RoomInventory implements Serializable {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public void display() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }
}

class PersistenceService {

    public void save(RoomInventory inventory, List<Reservation> bookings, String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(inventory);
            out.writeObject(bookings);
            out.close();
            System.out.println("Data Saved");
        } catch (Exception e) {
            System.out.println("Save Failed");
        }
    }

    public Object[] load(String fileName) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            RoomInventory inventory = (RoomInventory) in.readObject();
            List<Reservation> bookings = (List<Reservation>) in.readObject();
            in.close();
            return new Object[]{inventory, bookings};
        } catch (Exception e) {
            System.out.println("No Previous Data Found");
            return null;
        }
    }
}

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        PersistenceService service = new PersistenceService();
        String fileName = "data.ser";

        Object[] data = service.load(fileName);

        RoomInventory inventory;
        List<Reservation> bookings;

        if (data != null) {
            inventory = (RoomInventory) data[0];
            bookings = (List<Reservation>) data[1];
            System.out.println("Data Restored");
        } else {
            inventory = new RoomInventory();
            bookings = new ArrayList<>();
        }

        bookings.add(new Reservation("RES101", "Single Room"));
        bookings.add(new Reservation("RES102", "Double Room"));

        System.out.println("Current Inventory:");
        inventory.display();

        service.save(inventory, bookings, fileName);
    }
}