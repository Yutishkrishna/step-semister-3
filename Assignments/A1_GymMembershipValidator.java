public class A1_GymMembershipValidator {

    // Base class representing a standard gym membership
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
    }

    // Subclass representing a premium gym membership with an assigned personal coach
    public static class PremiumMember extends GymMember {
        private final String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            // Forward shared fields to superclass constructor - no duplicated fields
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        public String getTrainerName() {
            return trainerName;
        }
    }

    // Batch signup method using try/catch on constructor validation
    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;

        if (memberIds != null) {
            for (String id : memberIds) {
                try {
                    new GymMember(id, monthlyFee);
                    signedUp++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 1: Gym Membership Foundation & Batch Trial Sign-up Validator ---");

        // Example 1: Rejected short memberId
        try {
            new GymMember("GM1", 1000);
            System.out.println("GM1 construction succeeded unexpectedly");
        } catch (IllegalArgumentException e) {
            System.out.println("new GymMember(\"GM1\", 1000) -> construction rejected");
        }

        // Example 2: PremiumMember attending sessions
        PremiumMember p = new PremiumMember("MEM01", 2000, "Coach Riya");
        p.attendSession();
        p.attendSession();
        System.out.println("Sessions Attended: " + p.getSessionsAttended()); // Expected: 2
        System.out.println("Trainer Name: " + p.getTrainerName());

        // Example 3: Batch signup
        String[] batch = {"MEM1", "GM1", "MEM2", " ", "MEM3"};
        String batchResult = signUpBatch(batch, 1000);
        System.out.println("Batch Signup Result: " + batchResult); // Expected: Signed Up: 3 | Rejected: 2
    }
}
