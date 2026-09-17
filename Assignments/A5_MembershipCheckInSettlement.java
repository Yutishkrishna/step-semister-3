public class A5_MembershipCheckInSettlement {

    // Base GymMember with static counter, final membershipNumber, and overloaded payment methods
    public static class GymMember {
        private static int counter = 2000;
        private static int membersEnrolled = 0;

        public final String membershipNumber;
        private final int monthlyFee;
        private int feesPaid;
        private String lastPaymentMode;

        public GymMember(int monthlyFee) {
            // Increment static counter before building final membershipNumber string
            counter++;
            membersEnrolled++;
            this.membershipNumber = "GYM-" + counter;
            this.monthlyFee = monthlyFee;
            this.feesPaid = 0;
            this.lastPaymentMode = "Cash";
        }

        public void payFee(int amount) {
            this.feesPaid += amount;
        }

        public void payFee(int amount, String mode) {
            this.lastPaymentMode = mode;
            // Delegate to flat-amount overload to avoid duplicating logic
            payFee(amount);
        }

        public int getFeesPaid() {
            return feesPaid;
        }

        public String getLastPaymentMode() {
            return lastPaymentMode;
        }

        public int getMonthlyFee() {
            return monthlyFee;
        }

        public static int getMembersEnrolled() {
            return membersEnrolled;
        }

        public static void resetCounter() {
            counter = 2000;
            membersEnrolled = 0;
        }
    }

    // Subclass representing a member enrolled in group classes
    public static class GroupClassMember extends GymMember {
        private final String className;

        public GroupClassMember(int monthlyFee, String className) {
            super(monthlyFee);
            this.className = className;
        }

        public String getClassName() {
            return className;
        }
    }

    // Referral code validator using charAt and Character methods without regex
    public static boolean isValidReferralCode(String code) {
        // Check length first - wrong length fails immediately before calling charAt
        if (code == null || code.length() != 4) {
            return false;
        }

        return code.charAt(0) == 'G'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    // Batch processor separating GroupClassMember from regular GymMember with null tolerance
    public static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        if (members != null) {
            for (GymMember m : members) {
                if (m == null) {
                    nullSkipped++;
                    continue;
                }

                processed++;
                if (m instanceof GroupClassMember) {
                    group++;
                } else {
                    individual++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + group + " group | " + individual + " individual";
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 5: Membership Numbers, Referral Codes & Weekly Check-in Settlement ---");

        // Example 1: Static counter and membershipNumber
        GymMember m1 = new GymMember(1000);
        System.out.println("Membership Number: " + m1.membershipNumber); // Expected: GYM-2001
        System.out.println("Members Enrolled: " + GymMember.getMembersEnrolled()); // Expected: 1

        // Example 2: Referral code validation
        System.out.println("\nReferral Code Validations:");
        System.out.println("isValidReferralCode(\"G45B\"): " + isValidReferralCode("G45B")); // Expected: true
        System.out.println("isValidReferralCode(\"G4B\"):  " + isValidReferralCode("G4B"));  // Expected: false
        System.out.println("isValidReferralCode(\"X45B\"): " + isValidReferralCode("X45B")); // Expected: false

        // Example 3: Overloaded payFee
        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println("\nTotal Fees Paid: " + m1.getFeesPaid()); // Expected: 1000

        // Example 4: Weekly check-in processor with null resilience
        GymMember[] checkIns = {
            new GroupClassMember(1500, "Zumba"),
            null,
            new GymMember(1000)
        };

        String checkInSummary = processWeeklyCheckIn(checkIns);
        System.out.println("\nWeekly Check-in Summary:");
        System.out.println(checkInSummary); // Expected: 2 processed | 1 null skipped | 1 group | 1 individual
    }
}
