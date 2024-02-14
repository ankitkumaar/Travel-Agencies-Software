# Travel-Agencies-Software
Design, implement and test a software system that allows travel agencies to maintain their travel packages' itinerary and passengers.

Higher Level Diagram (HLD) :
A high-level diagram (HLD) for the travel agency software system. The HLD provides an overview of the system’s architecture and interactions between different components. 

Here’s a simple representation:
1. TravelPackage:
      i. Attributes: a. name: Name of the travel package.
                     b. passengerCapacity: Maximum number of passengers allowed.
      ii. Relationships:
                     a. Contains multiple Destinations.
                     b. Contains multiple Passengers.
   
3. Destination:
      i. Attributes: a. name: Name of the destination.
      ii. Relationships: a. Contains multiple Activities.
   
4. Activity:
      i. Attributes: a. name: Name of the activity.
                     b. description: Description of the activity.
                     c. cost: Cost of the activity.
                     d. capacity: Maximum number of participants.
      ii. Relationships: a. Belongs to a single Destination.
   
5. Passenger:
      i. Attributes: a. name: Name of the passenger.
                     b. passengerNumber: Unique identifier for the passenger.
                     c. balance: Current balance (for standard and gold passengers).
                     d. type: Passenger type (standard, gold, premium).
     ii. Relationships: a. Enrolls in multiple Activities.

Here’s a visual representation:
+---------------------+                  +---------------------+                 +---------------------+                  +---------------------+
|   TravelPackage     |                  |    Destination      |                 |      Activity       |                  |     Passenger       |
|---------------------|                  |---------------------|                 |---------------------|                  |---------------------|
| - name              |                  | - name              |                 | - name              |                  | - name              |
| - passengerCapacity |      --->        | - activities        |      --->       | - description       |       --->       | - passengerNumber   |
| - itinerary         |                  |---------------------|                 | - cost              |                  | - balance           |
| - passengers        |                  | + addActivity()     |                 | - capacity          |                  | - type              |
|---------------------|                  +---------------------+                 |---------------------|                  |---------------------|
| + addDestination()  |                                                          +---------------------+                  | + signUpForActivity()|
| + addPassenger()    |                                                                                                   +---------------------+
+---------------------+


Lower Level Diagram :
A low-level design (LLD) for the travel agency software system. The LLD provides detailed specifications for each class, including attributes, methods, and relationships. 

Here’s a UML class diagram representing the LLD:
@startuml
class TravelPackage {
  - name: String
  - passengerCapacity: int
  - itinerary: List<Destination>
  - passengers: List<Passenger>
  + addDestination(destination: Destination): void
  + addPassenger(passenger: Passenger): void
}

class Destination {
  - name: String
  - activities: List<Activity>
  + addActivity(activity: Activity): void
}

class Activity {
  - name: String
  - description: String
  - cost: double
  - capacity: int
}

class Passenger {
  - name: String
  - passengerNumber: int
  - balance: double
  - type: PassengerType
  + signUpForActivity(activity: Activity): void
}

enum PassengerType {
  STANDARD
  GOLD
  PREMIUM
}

TravelPackage --> Destination
Destination --> Activity
TravelPackage --> Passenger
@enduml

TravelPackage contains destinations (Destination) and passengers (Passenger). Each Destination has a list of activities (Activity). Passengers can sign up for activities using the signUpForActivity method. The PassengerType enum represents standard, gold, or premium passengers.


Implementation : Java programming language is used for implementation.
