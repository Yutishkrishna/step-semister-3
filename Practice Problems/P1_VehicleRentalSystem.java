public class P1_VehicleRentalSystem {
    static abstract class Vehicle {
        String name;
        private boolean available = true;
        Vehicle(String name){this.name=name;}
        abstract double calculateCharge(int days);
        boolean isAvailable(){return available;}
        void rent(){available=false;}
        void returnVehicle(){available=true;}
    }
    static class Sedan extends Vehicle {
        Sedan(String n){super(n);}
        double calculateCharge(int days){return 50*days;}
    }
    static class SUV extends Vehicle {
        SUV(String n){super(n);}
        double calculateCharge(int days){return 80*days;}
    }
    static class Truck extends Vehicle {
        Truck(String n){super(n);}
        double calculateCharge(int days){return 100*days;}
    }
    static class Customer {
        String name; Customer(String n){name=n;}
    }
    static class Rental {
        Customer customer; Vehicle vehicle; int days; boolean active;
        Rental(Customer c,Vehicle v,int d){customer=c;vehicle=v;days=d;}
        boolean start(){
            if(!vehicle.isAvailable()){
                System.out.println(vehicle.name+" is currently unavailable.");
                return false;
            }
            vehicle.rent(); active=true;
            System.out.println(vehicle.name+" rented successfully by "+customer.name+". Rental charge: $"+vehicle.calculateCharge(days)+".");
            return true;
        }
        void returnRental(){
            if(active){
                vehicle.returnVehicle(); active=false;
                System.out.println(vehicle.name+" returned by "+customer.name+".");
            }
        }
    }
    public static void main(String[] args){
        Vehicle sedan=new Sedan("Sedan A"), suv=new SUV("SUV B");
        Rental r1=new Rental(new Customer("Customer 1"),sedan,3);
        r1.start();
        new Rental(new Customer("Customer 2"),sedan,2).start();
        r1.returnRental();
        new Rental(new Customer("Customer 3"),suv,5).start();
    }
}