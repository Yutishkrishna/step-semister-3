public class A4_MonthlyAttendanceAnnouncer {

    // Base GymMember
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
            return "Standard | Sessions: " + getSessionsAttended();
        }
    }

    // Subclass PremiumMember
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
            return "Premium | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
        }
    }

    // Batch announcement generator using StringBuilder and safe instanceof downcast
    public static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();

        if (members != null) {
            for (GymMember member : members) {
                if (member == null) {
                    continue;
                }
                // Polymorphic displayInfo() invocation - no instanceof chain deciding what to print
                sb.append(member.displayInfo());

                // Guarded downcast using instanceof to safely access subclass-specific property
                if (member instanceof PremiumMember) {
                    PremiumMember pm = (PremiumMember) member;
                    sb.append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("]");
                }

                sb.append(" | ");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 4: The Monthly Attendance Announcer ---");

        GymMember[] members = {
            new GymMember("MEM6", 1000),
            new PremiumMember("MEM7", 2000, "Coach Riya")
        };

        String announcement = batchPrint(members);
        System.out.println("Batch Print Output:");
        System.out.println(announcement);

        // Demonstrate runtime ClassCastException when downcast is unguarded
        GymMember plain = new GymMember("MEM8", 1000);
        try {
            PremiumMember bad = (PremiumMember) plain;
            System.out.println("Cast unexpectedly succeeded: " + bad);
        } catch (ClassCastException e) {
            System.out.println("\nUnguarded downcast threw ClassCastException as expected: " + e.getMessage());
        }
    }
}
