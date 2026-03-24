import java.util.*;

class AddOnService {
    private String serviceName;
    private double price;

    public AddOnService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }
}

class AddOnServiceManager {

    private Map<String, List<AddOnService>> reservationServices = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);
        System.out.println(service.getServiceName() + " added to reservation " + reservationId);
    }

    public void showServices(String reservationId) {

        List<AddOnService> services = reservationServices.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected for reservation " + reservationId);
            return;
        }

        System.out.println("Services for Reservation " + reservationId + ":");

        for (AddOnService s : services) {
            System.out.println(s.getServiceName() + " : " + s.getPrice());
        }
    }

    public double calculateTotalCost(String reservationId) {

        double total = 0;

        List<AddOnService> services = reservationServices.get(reservationId);

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getPrice();
            }
        }

        return total;
    }
}

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "RES101";

        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 1200);
        AddOnService spa = new AddOnService("Spa Access", 2000);

        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, airportPickup);
        manager.addService(reservationId, spa);

        System.out.println();

        manager.showServices(reservationId);

        double total = manager.calculateTotalCost(reservationId);

        System.out.println("Total Add-On Cost: " + total);
    }
}