import java.util.*;

public class A1_HostelLaundryQueue {
    interface WashType {
        int getDuration();
        double getCharge();
        String getName();
    }

    static class QuickWash implements WashType {
        public int getDuration() { return 30; }
        public double getCharge() { return 20; }
        public String getName() { return "Quick"; }
    }
    static class NormalWash implements WashType {
        public int getDuration() { return 45; }
        public double getCharge() { return 30; }
        public String getName() { return "Normal"; }
    }
    static class HeavyWash implements WashType {
        public int getDuration() { return 60; }
        public double getCharge() { return 45; }
        public String getName() { return "Heavy"; }
    }

    static class Student {
        String name;
        Student(String name) { this.name = name; }
    }

    static class WashingMachine {
        private String id;
        private boolean busy;

        WashingMachine(String id) { this.id = id; }

        WashCycle startWash(Student student, WashType type) {
            if (busy) {
                System.out.println("Machine " + id + " is currently busy.");
                return null;
            }
            busy = true;
            System.out.printf("%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
                    type.getName(), id, student.name, type.getDuration(), type.getCharge());
            return new WashCycle(student, this, type);
        }

        void makeFree() { busy = false; }
        String getId() { return id; }
    }

    static class WashCycle {
        Student student;
        WashingMachine machine;
        WashType type;

        WashCycle(Student student, WashingMachine machine, WashType type) {
            this.student = student;
            this.machine = machine;
            this.type = type;
        }

        void complete() {
            machine.makeFree();
            System.out.println(machine.getId() + " cycle completed. " + machine.getId() + " is now free.");
        }
    }

    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashCycle c1 = m1.startWash(asha, new QuickWash());
        m1.startWash(ravi, new HeavyWash());
        m2.startWash(ravi, new HeavyWash());
        c1.complete();
        m1.startWash(neha, new NormalWash());
    }
}