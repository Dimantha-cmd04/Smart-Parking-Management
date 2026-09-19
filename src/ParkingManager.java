import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ParkingManager {
    private final ParkingSlot[] parkingSlots;
    private final Queue<Vehicle> waitingQueue;
    private final HashMap<String, Vehicle> parkedVehicles;

    public ParkingManager(int numberOfSlots) {
        parkingSlots = new ParkingSlot[numberOfSlots];
        for (int i = 0; i < numberOfSlots; i++) {
            parkingSlots[i] = new ParkingSlot("A-" + (i + 1));
        }
        waitingQueue = new LinkedList<>();
        parkedVehicles = new HashMap<>();
    }

    // Add a vehicle to the parking system
    public void addVehicle(Vehicle vehicle) {
        String vehicleNumber = vehicle.getVehicleNumber();

        // Check if the vehicle is already parked
        if (parkedVehicles.containsKey(vehicleNumber)) {
            System.out.println("\nVehicle is already parked.");
            return;
        }

        // Check whether the vehicle is already in the waiting queue
        for (Vehicle waitingVehicle : waitingQueue) {
            if (waitingVehicle.getVehicleNumber()
                    .equalsIgnoreCase(vehicleNumber)) {
                System.out.println(
                        "\nVehicle is already in the waiting queue."
                );
                return;
            }
        }

        // Find an available parking slot
        for (ParkingSlot slot : parkingSlots) {
            if (slot.isAvailable()) {
                slot.parkVehicle(vehicle);
                parkedVehicles.put(vehicleNumber, vehicle);

                System.out.println("\nVehicle parked successfully.");
                System.out.println("Vehicle Number : " + vehicleNumber);
                System.out.println("Parking Slot   : " + slot.getSlotNumber());
                return;
            }
        }
        // If no slot is available
        waitingQueue.offer(vehicle);
        System.out.println("\nParking is full.");
        System.out.println("Vehicle added to the waiting queue.");
    }

    // Remove a vehicle
    public void removeVehicle(String vehicleNumber){
        Vehicle vehicle = parkedVehicles.get(vehicleNumber);

        if (vehicle == null) {
            System.out.println("\nVehicle not found.");
            return;
        }
        // Find the vehicle's parking slot
        for (ParkingSlot slot : parkingSlots) {
            if (!slot.isAvailable()
                    && slot.getVehicle()
                    .getVehicleNumber()
                    .equalsIgnoreCase(vehicleNumber)) {

                slot.removeVehicle();
                parkedVehicles.remove(vehicleNumber);
                System.out.println("\nVehicle " + vehicleNumber + " has left the parking area.");

                // Assign the free slot to the next waiting vehicle
                assignWaitingVehicle(slot);
                return;
            }
        }
    }

    // Assign a free slot to the first vehicle in the queue
    private void assignWaitingVehicle(ParkingSlot slot) {
        if (waitingQueue.isEmpty()) {
            return;
        }
        Vehicle nextVehicle = waitingQueue.poll();
        slot.parkVehicle(nextVehicle);

        parkedVehicles.put(nextVehicle.getVehicleNumber(), nextVehicle);

        System.out.println(
                "Waiting vehicle "
                        + nextVehicle.getVehicleNumber()
                        + " assigned to "
                        + slot.getSlotNumber()
        );
    }

    // Display all parking slots
    public void displayParkingSlots() {
        System.out.println("\n========== PARKING SLOTS ==========");

        for (ParkingSlot slot : parkingSlots) {
            slot.displaySlot();
        }
        System.out.println("===================================");
    }

    // Search for a vehicle
    public void searchVehicle(String vehicleNumber) {
        Vehicle vehicle = parkedVehicles.get(vehicleNumber);

        if (vehicle == null) {
            System.out.println("\nVehicle not found.");
            return;
        }

        System.out.println("\n========== VEHICLE FOUND ==========");
        System.out.println("Vehicle Number : " + vehicle.getVehicleNumber());
        System.out.println("Owner Name     : " + vehicle.getOwnerName());
        System.out.println("Vehicle Type   : " + vehicle.getVehicleType());

        // Find the parking slot
        for (ParkingSlot slot : parkingSlots) {
            if (!slot.isAvailable()
                    && slot.getVehicle()
                    .getVehicleNumber()
                    .equalsIgnoreCase(vehicleNumber)) {

                System.out.println(
                        "Parking Slot   : "
                                + slot.getSlotNumber()
                );
                break;
            }
        }
        System.out.println("===================================");
    }

    // Display waiting vehicles
    public void displayWaitingQueue() {
        System.out.println("\n========= WAITING QUEUE =========");

        if (waitingQueue.isEmpty()) {
            System.out.println("No vehicles are waiting.");
        } else {
            int position = 1;
            for (Vehicle vehicle : waitingQueue) {
                System.out.println(
                        position + ". "
                                + vehicle.getVehicleNumber()
                                + " - "
                                + vehicle.getOwnerName()
                );
                position++;
            }
        }
        System.out.println("=================================");
    }

    // Display parking statistics
    public void displayStatistics() {
        int totalSlots = parkingSlots.length;
        int occupiedSlots = parkedVehicles.size();
        int availableSlots = totalSlots - occupiedSlots;

        System.out.println("\n======= PARKING STATISTICS =======");
        System.out.println("Total Slots      : " + totalSlots);
        System.out.println("Occupied Slots   : " + occupiedSlots);
        System.out.println("Available Slots  : " + availableSlots);
        System.out.println("Waiting Vehicles : " + waitingQueue.size());
        System.out.println("==================================");
    }
}