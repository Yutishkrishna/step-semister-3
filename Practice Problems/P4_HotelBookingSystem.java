import java.time.*;
import java.util.*;

public class P4_HotelBookingSystem {
    static abstract class Room {
        int number;
        List<Reservation> reservations=new ArrayList<>();
        Room(int n){number=n;}
        abstract double pricePerNight();
        double calculatePrice(long nights){return pricePerNight()*nights;}
        boolean available(LocalDate start,LocalDate end){
            for(Reservation r:reservations)
                if(r.active && start.isBefore(r.end) && end.isAfter(r.start)) return false;
            return true;
        }
    }
    static class StandardRoom extends Room {
        StandardRoom(int n){super(n);} double pricePerNight(){return 100;}
    }
    static class DeluxeRoom extends Room {
        DeluxeRoom(int n){super(n);} double pricePerNight(){return 180;}
    }
    static class Suite extends Room {
        Suite(int n){super(n);} double pricePerNight(){return 300;}
    }
    static class Customer {String name;Customer(String n){name=n;}}
    static class Reservation {
        Customer customer; Room room; LocalDate start,end; boolean active=true;
        Reservation(Customer c,Room r,LocalDate s,LocalDate e){
            customer=c;room=r;start=s;end=e;
        }
        void cancel(LocalDate today,LocalDate deadline){
            if(today.isAfter(deadline)){
                System.out.println("Cancellation deadline has passed.");
                return;
            }
            active=false;
            System.out.println("Reservation for "+customer.name+", Room "+room.number+" cancelled successfully.");
        }
    }
    static Reservation reserve(Customer c,Room r,LocalDate s,LocalDate e){
        if(!r.available(s,e)){
            System.out.println("Room "+r.number+" is not available from "+s+" to "+e+".");
            return null;
        }
        Reservation x=new Reservation(c,r,s,e); r.reservations.add(x);
        long nights=java.time.temporal.ChronoUnit.DAYS.between(s,e);
        System.out.println("Reservation confirmed for "+c.name+", Room "+r.number+". Price: $"+r.calculatePrice(nights)+".");
        return x;
    }
    public static void main(String[] args){
        Room standard=new StandardRoom(101), deluxe=new DeluxeRoom(201);
        Customer a=new Customer("Customer A"),b=new Customer("Customer B"),c=new Customer("Customer C");
        LocalDate s=LocalDate.of(2026,1,1),e=LocalDate.of(2026,1,5);
        System.out.println("Standard Room 101 is "+(standard.available(s,e)?"available":"not available")+" from Jan 1 to Jan 5.");
        Reservation r=reserve(a,standard,s,e);
        reserve(b,standard,LocalDate.of(2026,1,3),LocalDate.of(2026,1,7));
        r.cancel(LocalDate.of(2025,12,30),LocalDate.of(2025,12,31));
        reserve(c,deluxe,LocalDate.of(2026,2,10),LocalDate.of(2026,2,12));
    }
}