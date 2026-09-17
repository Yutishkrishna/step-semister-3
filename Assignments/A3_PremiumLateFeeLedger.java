import java.util.Arrays;

public class A3_PremiumLateFeeLedger {

    // Base GymMember with private late fee ledger and defensive copying
    public static class GymMember {
        private final String memberId;
        private final int monthlyFee;
        private final int[] lateFeeHistory;
        private int feeCount;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
                throw new IllegalArgumentException("Invalid memberId: cannot be blank, whitespace-only, or shorter than 4 characters");
            }
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
            this.lateFeeHistory = new int[10]; // Up to 10 late fees per membership
            this.feeCount = 0;
        }

        protected void chargeLateFee(int amount) {
            if (feeCount < lateFeeHistory.length) {
                lateFeeHistory[feeCount++] = amount;
            }
        }

        // Returns a defensive copy so external tampering does not mutate the internal array
        public int[] getLateFeeHistory() {
            return Arrays.copyOf(lateFeeHistory, feeCount);
        }

        public int getTotalLateFees() {
            int total = 0;
            for (int i = 0; i < feeCount; i++) {
                total += lateFeeHistory[i];
            }
            return total;
        }

        public String getMemberId() {
            return memberId;
        }

        public int getMonthlyFee() {
            return monthlyFee;
        }
    }

    // PremiumMember halves late fees and delegates directly to super.chargeLateFee()
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
        protected void chargeLateFee(int amount) {
            // Halves every late fee and reuses parent's deduction and recording logic
            super.chargeLateFee(amount / 2);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 3: The Premium Loyalty Discount & Late-Fee Ledger ---");

        PremiumMember p = new PremiumMember("MEM5", 2000, "Coach Riya");
        p.chargeLateFee(200);
        System.out.println("Total Late Fees: " + p.getTotalLateFees()); // Expected: 100

        // Demonstrate defensive copying
        int[] history = p.getLateFeeHistory();
        System.out.println("Original History: " + Arrays.toString(history)); // Expected: [100]

        // Tamper with caller's array copy
        history[0] = 999;
        System.out.println("After attempting mutation on external copy: " + Arrays.toString(p.getLateFeeHistory())); // Expected: [100]
    }
}
