public class A4_MonthlyAttendanceAnnouncer {

    static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        protected int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
                throw new IllegalArgumentException("Invalid memberId");
            }
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        String displayInfo() {
            return "Standard | Sessions: " + sessionsAttended;
        }
    }

    static class PremiumMember extends GymMember {
        String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        String displayInfo() {
            return "Premium | Trainer: " + trainerName + " | Sessions: " + sessionsAttended;
        }
    }

    // loops polymorphically over displayInfo() - no instanceof/if-else chain deciding WHAT to print;
    // instanceof is only used to guard the one downcast needed to reach the trainer's name
    static String batchPrint(GymMember[] members) {
        StringBuilder announcement = new StringBuilder();
        for (GymMember member : members) {
            announcement.append(member.displayInfo());
            if (member instanceof PremiumMember) {
                PremiumMember premium = (PremiumMember) member;
                announcement.append(" [Trainer via downcast: ").append(premium.trainerName).append("]");
            }
            announcement.append(" | ");
        }
        return announcement.toString();
    }

    public static void main(String[] args) {
        GymMember[] members = {
                new GymMember("MEM6", 1000),
                new PremiumMember("MEM7", 2000, "Coach Riya")
        };
        System.out.println(batchPrint(members));

        GymMember plain = new GymMember("MEM8", 1000);
        try {
            PremiumMember bad = (PremiumMember) plain;
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}
