       import java.util.*;

// === Ticket class ===
class Ticket {
    String passengerName;
    String email;
    String transportType;
    String route;
    int cost;

    public Ticket(String passengerName, String email, String transportType, String route, int cost) {
        this.passengerName = passengerName;
        this.email = email;
        this.transportType = transportType;
        this.route = route;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Passenger: " + passengerName + "\n" 
              + "Email: " + email + "\n" 
              + "Type: " + transportType + "\n" 
              + "Route: " + route + "\n" 
              + "Cost: Rs." + cost;
    }
}

// === Abstract Transport class ===
abstract class Transport {
    protected String type;
    protected int cost;
    protected List<String> routes;

    public Transport(String type, int cost, String[] routes) {
        this.type = type;
        this.cost = cost;
        this.routes = Arrays.asList(routes);
    }

    public void bookTicket(Scanner scanner, List<Ticket> ticketList) {
        System.out.println("\n--- " + type + " Routes ---");
        for (int i = 0; i < routes.size(); i++) {
            System.out.println((i + 1) + ". " + routes.get(i));
        }
        System.out.println(" Ticket Cost: Rs." + cost);
        System.out.print("Choose route (1-" + routes.size() + "): ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice < 1 || choice > routes.size()) {
            System.out.println(" Invalid route selection.");
            return;
        }

        String selectedRoute = routes.get(choice - 1);
        System.out.print("Enter passenger name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Ticket ticket = new Ticket(name, email, type, selectedRoute, cost);
        ticketList.add(ticket);
        System.out.println(" Ticket Booked Successfully!");
        System.out.println(" " + ticket);
    }
}

// === Subclasses for Bus, Train, and Flight ===
class Bus extends Transport {
    public Bus() {
        super("Bus", 100, new String[]{ "Nagapattinam - chennai", "Nagapattinam - coimbatore", "kudavasal - Nagapattinam"});
    }
}

class Train extends Transport {
    public Train() {
        super("Train", 250, new String[]{ " Karaikal Station  - kumbakonam Station ", "kumbakonam Station  -  Chennai Station ", " Chennai Station -  Chennai station "});
    }
}

class Flight extends Transport {
    public Flight() {
        super("Flight", 1500, new String[]{ "Trichy - chennai", "chennai - coimbatore", "Madurai - chennai"});
    }
}

// === Main App ===
public class TicketBookingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Ticket> bookedTickets = new ArrayList<>();

        Bus bus = new Bus();
        Train train = new Train();
        Flight flight = new Flight();

        boolean running = true;
        while (running) {
            System.out.println("\n=== Ticket Booking System ===");
            System.out.println("1. Book Bus Ticket");
            System.out.println("2. Book Train Ticket");
            System.out.println("3. Book Flight Ticket");
            System.out.println("4. View All Tickets");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    bus.bookTicket(scanner, bookedTickets);
                    break;
                case "2":
                    train.bookTicket(scanner, bookedTickets);
                    break;
                case "3":
                    flight.bookTicket(scanner, bookedTickets);
                    break;
                case "4":
                    if (bookedTickets.isEmpty()) {
                        System.out.println("No tickets booked yet.");
                    } else {
                        System.out.println("\n All Booked Tickets:");
                        for (Ticket t : bookedTickets) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "5":
                    System.out.println(" Thanks for using the Ticket Booking App!");
                    running = false;
                    break;
                default:
                    System.out.println(" Invalid input. Please try again.");
            }
        }

        scanner.close();
    }
}
