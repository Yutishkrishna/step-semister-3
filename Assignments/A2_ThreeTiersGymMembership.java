public class A2_ThreeTiersGymMembership {

    // Base class: Standard Gym Member
    public static class GymMember {
        private final String memberId;
        private final int monthlyFee;
        private int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
                throw new IllegalArgumentException("Invalid memberId: cannot be blank, whitespace-only, or shorter than 4 characters");
            }
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
            this.sessionsAttended = 0;
        }

        public void attendSession() {
            sessionsAttended++;
        }

        public int getSessionsAttended() {
            return sessionsAttended;
        }

        public String getMemberId() {
            return memberId;
        }

        public int getMonthlyFee() {
            return monthlyFee;
        }

        public String displayInfo() {
            return "Standard Member | Sessions: " + getSessionsAttended();
        }
    }

    // Subclass 1: Premium Member (Single Inheritance)
    public static class PremiumMember extends GymMember {
        private final String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        public String getTrainerName() {
            return trainerName;
        }

        @Override
        public String displayInfo() {
            return "Premium Member | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
        }
    }

    // Subclass 2: Elite Member extends PremiumMember (Multilevel Inheritance, 3 classes deep)
    public static class EliteMember extends PremiumMember {
        private final String lockerNumber;

        public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
            super(memberId, monthlyFee, trainerName);
            this.lockerNumber = lockerNumber;
        }

        public String getLockerNumber() {
            return lockerNumber;
        }

        @Override
        public String displayInfo() {
            return "Elite Member | Trainer: " + getTrainerName() + " | Locker: " + lockerNumber + " | Sessions: " + getSessionsAttended();
        }
    }

    // Subclass 3: Group Class Member extends GymMember directly (Hierarchical Inheritance, independent branch)
    public static class GroupClassMember extends GymMember {
        private final String className;

        public GroupClassMember(String memberId, int monthlyFee, String className) {
            super(memberId, monthlyFee);
            this.className = className;
        }

        public String getClassName() {
            return className;
        }

        @Override
        public String displayInfo() {
            return "Group Class Member | Class: " + className + " | Sessions: " + getSessionsAttended();
        }
    }

    // Uses instanceof to classify inheritance structure
    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMember) {
            return "Single inheritance descendant";
        } else if (member != null) {
            return "Base hierarchy root";
        }
        return "Unknown";
    }

    // Sums sessions polymorphically without type checking
    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        if (members != null) {
            for (GymMember m : members) {
                if (m != null) {
                    total += m.getSessionsAttended();
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 2: Three Tiers of Gym Membership ---");

        GymMember m1 = new GymMember("MEM1", 1000);
        PremiumMember m2 = new PremiumMember("MEM2", 2000, "Coach Riya");
        EliteMember m3 = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember m4 = new GroupClassMember("MEM4", 1500, "Zumba");

        System.out.println(m1.displayInfo());
        System.out.println(m2.displayInfo());
        System.out.println(m3.displayInfo());
        System.out.println(m4.displayInfo());

        System.out.println("\nClassification:");
        System.out.println("EliteMember: " + classifyGeneration(m3));
        System.out.println("GroupClassMember: " + classifyGeneration(m4));

        // Simulate session attendances
        m2.attendSession(); m2.attendSession(); m2.attendSession(); // 3
        m3.attendSession(); m3.attendSession();                     // 2
        m4.attendSession(); m4.attendSession(); m4.attendSession(); m4.attendSession(); // 4

        GymMember[] list = {m2, m3, m4};
        System.out.println("\nTotal Sessions Attended (Polymorphic): " + getTotalSessionsAttended(list)); // Expected: 9
    }
}
