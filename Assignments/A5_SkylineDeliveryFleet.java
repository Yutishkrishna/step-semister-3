public class A5_SkylineDeliveryFleet {

    static abstract class Drone {
        String id;

        public Drone(String id) {
            this.id = id;
        }

        public abstract String fly();
    }

    interface Trackable {
        String getLocation();
    }

    static class DeliveryDrone extends Drone implements Trackable {
        public DeliveryDrone(String id) {
            super(id);
        }

        @Override
        public String fly() {
            return id + " flying delivery route";
        }

        @Override
        public String getLocation() {
            return id + " at Sector 4";
        }
    }

    // hierarchical sibling of DeliveryDrone - shares Drone, but deliberately does NOT implement Trackable
    static class ScoutDrone extends Drone {
        public ScoutDrone(String id) {
            super(id);
        }

        @Override
        public String fly() {
            return id + " scouting ahead";
        }
    }

    // no relationship to Drone at all - only implements Trackable
    static class GroundRobot implements Trackable {
        String id;

        public GroundRobot(String id) {
            this.id = id;
        }

        @Override
        public String getLocation() {
            return id + " at Sector 4";
        }
    }

    static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            Trackable trackable = (Trackable) o;
            return trackable.getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        DeliveryDrone d = new DeliveryDrone("DR-1");
        System.out.println(getLocationIfTrackable(d));

        ScoutDrone s = new ScoutDrone("SC-1");
        System.out.println(getLocationIfTrackable(s));

        GroundRobot g = new GroundRobot("GR-1");
        System.out.println(getLocationIfTrackable(g));
    }
}
