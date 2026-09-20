import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create parking area with 5 slots
        ParkingManager parkingManager = new ParkingManager(5);

        int choice = 0;
        while (choice != 8) {
            displayMenu();
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addVehicle(scanner, parkingManager);
                        break;
                    case 2:
                        removeVehicle(scanner, parkingManager);
                        break;
                    case 3:
                        vehiclePayment(scanner, parkingManager);
                        break;
                    case 4:
                        parkingManager.displayParkingSlots();
                        break;
                    case 5:
                        searchVehicle(scanner, parkingManager);
                        break;
                    case 6:
                        parkingManager.displayWaitingQueue();
                        break;
                    case 7:
                        parkingManager.displayStatistics();
                        break;
                    case 8:
                        System.out.println("\nThank you for using Smart Parking Management System.");
                        break;
                    default:
                        System.out.println("\nInvalid choice. " + "Please select 1-8.");
                }

            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. " + "Please enter a number."
                );
            }
        }
        scanner.close();
    }

    // Display main menu
    private static void displayMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("     SMART PARKING MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Vehicle Entry");
        System.out.println("2. Vehicle Exit");
        System.out.println("3. View Parking Slots");
        System.out.println("4. Search Vehicle");
        System.out.println("5. View Waiting Queue");
        System.out.println("6. Parking Statistics");
        System.out.println("7. Exit");
        System.out.println("========================================");
    }

    // Add vehicle
    private static void addVehicle(
            Scanner scanner,
            ParkingManager parkingManager) {
        System.out.println("\n--------- VEHICLE ENTRY ---------");
        System.out.print("Enter vehicle number: ");
        String vehicleNumber = scanner.nextLine().trim();

        if (vehicleNumber.isEmpty()) {
            System.out.println("Vehicle number cannot be empty.");
            return;
        }

        System.out.print("Enter owner name: ");
        String ownerName = scanner.nextLine().trim();

        if (ownerName.isEmpty()) {
            System.out.println("Owner name cannot be empty.");
            return;
        }

        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine().trim();

        if (vehicleType.isEmpty()) {
            System.out.println("Vehicle type cannot be empty.");
            return;
        }
        Vehicle vehicle = new Vehicle(vehicleNumber, ownerName, vehicleType);
        parkingManager.addVehicle(vehicle);
    }

    // Remove vehicle
    private static void removeVehicle(
            Scanner scanner,
            ParkingManager parkingManager) {

        System.out.println("\n--------- VEHICLE EXIT ---------");
        System.out.print("Enter vehicle number: ");

        String vehicleNumber = scanner.nextLine().trim();

        if (vehicleNumber.isEmpty()) {
            System.out.println("Vehicle number cannot be empty.");
            return;
        }
        parkingManager.removeVehicle(vehicleNumber);
    }

    // Search vehicle
    private static void searchVehicle(
            Scanner scanner,
            ParkingManager parkingManager) {

        System.out.println("\n--------- SEARCH VEHICLE ---------");
        System.out.print("Enter vehicle number: ");

        String vehicleNumber = scanner.nextLine().trim();

        if (vehicleNumber.isEmpty()) {
            System.out.println("Vehicle number cannot be empty.");
            return;
        }
        parkingManager.searchVehicle(vehicleNumber);
    }

    // Calculate vehicle payment
    private static void vehiclePayment(Scanner scanner, ParkingManager parkingManager) {
        System.out.println("\n--------- VEHICLE PAYMENT ---------");
        System.out.print("Enter parking hours: ");

        try {
            int hours = Integer.parseInt(scanner.nextLine());
            if (hours <= 0) {
                System.out.println("Parking hours must be greater than 0.");
                return;
            }
            double payment = parkingManager.vehiclePayment(hours);

            System.out.println("\n===== PAYMENT DETAILS =====");
            System.out.println("Parking Hours : " + hours);
            System.out.printf("Total Payment : Rs. %.2f%n", payment);
            System.out.println("===========================");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
}