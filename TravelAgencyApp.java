import java.util.ArrayList;
import java.util.List;

// Class representing an activity
class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private Destination destination;

    // Getters and setters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public void setCapacity(int new_capacity) {
        capacity = new_capacity;
    }
    public int getCapacity() {
        return capacity;
    }
    public double getCost() {
        return cost;
    }
    public Destination getDestination() {
        return destination;
    }

    // Constructor
    public Activity(String name, String description, double cost, int capacity, Destination destination) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.destination = destination;
    }

    // Get the name of the destination associated with this activity
    public String getDestinationName() {
        return destination.getName();
    }
}

// Class representing a destination
class Destination {
    private String name;
    private List<Activity> activities;

    // Getters
    public String getName() {
        return name;
    }
    public List<Activity> getActivities() {
        return activities;
    }

    // Constructor
    public Destination(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    // Add an activity to the destination
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    // Print details of activities at this destination
    public void printActivities() {
        System.out.println("Activities at " + name + ":");

        for (Activity activity : activities) {
            System.out.println("Name: " + activity.getName());
            System.out.println("Description: " + activity.getDescription());
            System.out.println("Cost: $" + activity.getCost());
            System.out.println("Capacity: " + activity.getCapacity());
            System.out.println();
        }
    }
}


// Enum representing types of passengers
enum PassengerType {
    STANDARD, GOLD, PREMIUM
}

// Class representing a passenger
class Passenger {
    private String name;
    private int passengerNumber;
    private double balance;
    private PassengerType type;
    private List<Activity> signedUpActivities;

    // Getters
    public String getName() {
        return name;
    }
    public int getPassengerNumber() {
        return passengerNumber;
    }

    // Constructor
    public Passenger(String name, int passengerNumber, double balance, PassengerType type) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.balance = balance;
        this.type = type;
        this.signedUpActivities = new ArrayList<>();
    }

    // Sign up for an activity based on passenger type
    public void signUpForActivity(Activity activity) {
        if (activity.getCapacity() > 0) {
            if (type == PassengerType.STANDARD) {
                if (balance >= activity.getCost()) {
                    balance -= activity.getCost();
                    signedUpActivities.add(activity);
                    activity.setCapacity(activity.getCapacity() - 1);
                } else {
                    System.out.println("Insufficient balance for standard passenger.");
                }
            } else if (type == PassengerType.GOLD) {
                double discountedCost = 0.9 * activity.getCost();
                if (balance >= discountedCost) {
                    balance -= discountedCost;
                    signedUpActivities.add(activity);
                    activity.setCapacity(activity.getCapacity() - 1);
                } else {
                    System.out.println("Insufficient balance for gold passenger.");
                }
            } else if (type == PassengerType.PREMIUM) {
                signedUpActivities.add(activity);
                activity.setCapacity(activity.getCapacity() - 1);
            }
        } else {
            System.out.println("Activity is already full.");
        }
    }

    // Print details of the passenger
    public void printDetails() {
        System.out.println("Passenger Details ->");
        System.out.println("Name: " + name);
        System.out.println("Passenger Number: " + passengerNumber);
        System.out.println("Balance: $" + balance);
        System.out.println("Signed Up Activities:");

        for (Activity activity : signedUpActivities) {
            System.out.println("Activity: " + activity.getName());
            System.out.println("Destination: " + activity.getDestination().getName());
            System.out.println("Price Paid: $" + activity.getCost());
            System.out.println();
        }
    }
}

// Class representing a travel package
class TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Destination> itinerary;
    private List<Passenger> passengers;

    // Getters
    public List<Destination> getItinerary() {
        return itinerary;
    }

    // Constructor
    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.itinerary = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    // Add a destination to the travel package
    public void addDestination(Destination destination) {
        itinerary.add(destination);
    }

    // Add a passenger to the travel package
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    // Print itinerary details
    public void printItinerary() {
        System.out.println("Travel Package Itinerary ->");

        System.out.println("Package Name: " + name);
        System.out.println();

        for (Destination destination : itinerary) {
            System.out.println("Destination: " + destination.getName());
            destination.printActivities();
        }
    }

    // Print passenger list details
    public void printPassengerList() {
        System.out.println("Passenger List ->");

        System.out.println("Package Name: " + name);
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("Number of Passengers Enrolled: " + passengers.size());
        System.out.println();

        for (Passenger passenger : passengers) {
            System.out.println("Name: " + passenger.getName());
            System.out.println("Passenger Number: " + passenger.getPassengerNumber());
        }
    }

    // Get a passenger by name
    public Passenger getPassengerByName(String passengerName) {

        for (Passenger passenger : passengers) {
            if (passenger.getName().equalsIgnoreCase(passengerName)) {
                return passenger;
            }
        }
        // Passenger not found
        return null;
    }

    // Print details of available activities
    public void printAvailableActivities() {
        System.out.println("Available Activities ->");

        for (Destination destination : itinerary) {
            for (Activity activity : destination.getActivities()) {
                if (activity.getCapacity() > 0) {
                    System.out.println("Activity: " + activity.getName());
                    System.out.println("Spaces Available: " + activity.getCapacity());
                    System.out.println();
                }
            }
        }
    }
}

// Main class
public class TravelAgencyApp {

    // Driver code
    public static void main(String[] args) {

        // Create a travel package
        TravelPackage beachVacation = new TravelPackage("Beach Vacation", 50);

        // Create destinations
        Destination beachDestination = new Destination("Beach Paradise");
        Destination mountainDestination = new Destination("Mountain Retreat");

        // Add activities to destinations
        Activity swimming = new Activity("Swimming", "Explore underwater life", 50.0, 20, beachDestination);
        beachDestination.addActivity(swimming);

        Activity hiking = new Activity("Hiking", "Enjoy scenic mountain trails", 30.0, 15, beachDestination);
        mountainDestination.addActivity(hiking);

        // Add destinations to the travel package
        beachVacation.addDestination(beachDestination);
        beachVacation.addDestination(mountainDestination);

        // Create passengers
        Passenger alice = new Passenger("Alice", 101, 100.0, PassengerType.STANDARD);
        Passenger bob = new Passenger("Bob", 102, 200.0, PassengerType.GOLD);

        // Sign up passengers for activities
        alice.signUpForActivity(swimming);
        bob.signUpForActivity(hiking);

        // Print itinerary
        beachVacation.printItinerary();

        // Print passenger list
        beachVacation.printPassengerList();

        // Print individual passenger details
        alice.printDetails();
        bob.printDetails();

        // Print available activities
        System.out.println("Available Activities ->");

        for (Destination destination : beachVacation.getItinerary()) {
            for (Activity activity : destination.getActivities()) {

                if (activity.getCapacity() > 0) {
                    System.out.println("Activity: " + activity.getName());
                    System.out.println("Spaces Available: " + activity.getCapacity());
                    System.out.println();
                }
            }
        }
    }
}
        
