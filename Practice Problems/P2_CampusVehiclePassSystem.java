import java.util.*;
public class P2_CampusVehiclePassSystem {
 interface Chargeable {boolean bookChargingBay();}
 static abstract class Vehicle {String pass,owner;Vehicle(String p,String o){pass=p;owner=o;}abstract int fee();abstract String type();void printPass(){System.out.println(pass+" ("+type()+") pass fee "+fee());}}
 static class Bike extends Vehicle {Bike(String p,String o){super(p,o);}int fee(){return 300;}String type(){return "Bike";}}
 static class Car extends Vehicle {Car(String p,String o){super(p,o);}int fee(){return 1000;}String type(){return "Car";}}
 static class EBike extends Vehicle implements Chargeable {EBike(String p,String o){super(p,o);}int fee(){return 300;}String type(){return "EBike";}public boolean bookChargingBay(){return true;}}
 static class ECar extends Vehicle implements Chargeable {ECar(String p,String o){super(p,o);}int fee(){return 1000;}String type(){return "ECar";}public boolean bookChargingBay(){return true;}}
 public static void main(String[] args){Map<String,Vehicle> m=new HashMap<>();Vehicle b=new Bike("KA01","Asha"),e=new ECar("KA02","Ravi");m.put(b.pass,b);m.put(e.pass,e);b.printPass();e.printPass();for(String id:new String[]{"KA02","KA01"}){Vehicle v=m.get(id);if(v instanceof Chargeable c&&c.bookChargingBay())System.out.println(id+" charging bay allotted");else System.out.println(id+" rejected: charging unsupported");}}
}