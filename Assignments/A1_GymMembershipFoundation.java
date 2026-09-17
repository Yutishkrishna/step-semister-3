public class A1_GymMembershipFoundation {

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

        void attendSession() {
            sessionsAttended++;
        }

        int getSessionsAttended() {
            return sessionsAttended;
        }
    }

    static class PremiumMember extends GymMember {
        String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }
    }

    // constructs one GymMember per entry, using try/catch to count rejections - no pre-validation here
    static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0, rejected = 0;
        for (String id : memberIds) {
            try {
                new GymMember(id, monthlyFee);
                signedUp++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        try {
            new GymMember("GM1", 1000);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        PremiumMember p = new PremiumMember("MEM01", 2000, "Coach Riya");
        p.attendSession();
        p.attendSession();
        System.out.println(p.getSessionsAttended());

        System.out.println(signUpBatch(new String[]{"MEM1", "GM1", "MEM2", " ", "MEM3"}, 1000));
    }
}
