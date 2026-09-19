public class Vehicle {
    private final String vehicleNumber;
    private final String ownerName;
    private final String vehicleType;

    public Vehicle(String vehicleNumber, String ownerName, String vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle Number: " + vehicleNumber
                + ", Owner: " + ownerName
                + ", Type: " + vehicleType;
    }
}
