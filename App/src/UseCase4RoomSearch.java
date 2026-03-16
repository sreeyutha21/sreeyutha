import java.util.HashMap;
import java.util.Map;

class Room {
    private String type;
    private double price;
    private String amenities;

    public Room(String type, double price, String amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getAmenities() {
        return amenities;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Price: " + price);
        System.out.println("Amenities: " + amenities);
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void registerRoomType(String type, int count) {
        inventory.put(type, count);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }
}

class SearchService {
    private RoomInventory inventory;
    private HashMap<String, Room> rooms;

    public SearchService(RoomInventory inventory, HashMap<String, Room> rooms) {
        this.inventory = inventory;
        this.rooms = rooms;
    }

    public void searchRooms() {
        for (Map.Entry<String, Integer> entry : inventory.getInventory().entrySet()) {
            if (entry.getValue() > 0) {
                Room room = rooms.get(entry.getKey());
                room.displayDetails();
                System.out.println("Available: " + entry.getValue());
                System.out.println();
            }
        }
    }
}

public class UseCase4RoomSearch {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();

        inventory.registerRoomType("Single", 5);
        inventory.registerRoomType("Double", 0);
        inventory.registerRoomType("Suite", 2);

        HashMap<String, Room> rooms = new HashMap<>();

        rooms.put("Single", new Room("Single", 2000, "WiFi, TV"));
        rooms.put("Double", new Room("Double", 3500, "WiFi, TV, AC"));
        rooms.put("Suite", new Room("Suite", 6000, "WiFi, TV, AC, Mini Bar"));

        SearchService searchService = new SearchService(inventory, rooms);

        System.out.println("Available Rooms");
        searchService.searchRooms();
    }
}