import java.util.*;

public class A3_CampusPremiereTicketCounter {
    interface Seat {
        String getNumber();
        double getPrice();
    }
    static class RegularSeat implements Seat {
        String n; RegularSeat(String n) { this.n=n; }
        public String getNumber(){ return n; }
        public double getPrice(){ return 150; }
    }
    static class PremiumSeat implements Seat {
        String n; PremiumSeat(String n) { this.n=n; }
        public String getNumber(){ return n; }
        public double getPrice(){ return 250; }
    }
    static class ReclinerSeat implements Seat {
        String n; ReclinerSeat(String n) { this.n=n; }
        public String getNumber(){ return n; }
        public double getPrice(){ return 400; }
    }
    static class Customer {
        String name; Customer(String n){ name=n; }
    }
    static class Show {
        String time;
        boolean started;
        Set<String> booked = new HashSet<>();
        Show(String t){ time=t; }

        Booking book(Customer c, Seat... seats) {
            if (seats.length > 6) {
                System.out.println("Maximum 6 seats allowed per booking.");
                return null;
            }
            for (Seat s : seats)
                if (booked.contains(s.getNumber())) {
                    System.out.println("Seat " + s.getNumber() + " is already booked for this show.");
                    return null;
                }
            for (Seat s : seats) booked.add(s.getNumber());
            Booking b = new Booking(c, this, Arrays.asList(seats));
            b.printConfirmation();
            return b;
        }
    }
    static class Booking {
        Customer customer;
        Show show;
        List<Seat> seats;
        boolean cancelled;

        Booking(Customer c, Show s, List<Seat> seats) {
            customer=c; show=s; this.seats=seats;
        }
        double total() {
            return seats.stream().mapToDouble(Seat::getPrice).sum();
        }
        void printConfirmation() {
            System.out.print("Booking confirmed for " + customer.name + ": ");
            System.out.print(String.join(", ", seats.stream().map(Seat::getNumber).toList()));
            System.out.printf(". Total: ₹%.2f.%n", total());
        }
        void cancel() {
            if (show.started) {
                System.out.println("Cannot cancel after the show starts.");
                return;
            }
            if (cancelled) return;
            for (Seat s : seats) show.booked.remove(s.getNumber());
            cancelled = true;
            System.out.print(customer.name + "'s booking cancelled. Seats ");
            System.out.print(String.join(", ", seats.stream().map(Seat::getNumber).toList()));
            System.out.println(" released.");
        }
    }

    public static void main(String[] args) {
        Show show = new Show("7 PM");
        Customer asha=new Customer("Asha"), ravi=new Customer("Ravi"), neha=new Customer("Neha");
        Booking a = show.book(asha, new RegularSeat("A1"), new RegularSeat("A2"), new PremiumSeat("F5"));
        show.book(ravi, new RegularSeat("A2"));
        show.book(ravi, new ReclinerSeat("R1"));
        a.cancel();
        show.book(neha, new RegularSeat("A2"));
    }
}