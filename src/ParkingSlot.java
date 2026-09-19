public class ParkingSlot {

    private final String slotNumber;
    private Vehicle vehicle;

    public ParkingSlot(String slotNumber) {
        this.slotNumber = slotNumber;
        this.vehicle = null;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle removeVehicle() {
        Vehicle removedVehicle = this.vehicle;
        this.vehicle = null;
        return removedVehicle;
    }

    public void displaySlot() {
        if (isAvailable()) {
            System.out.println(slotNumber + " -> AVAILABLE");
        } else {
            System.out.println(
                    slotNumber + " -> OCCUPIED by "
                            + vehicle.getVehicleNumber()
            );
        }
    }
}