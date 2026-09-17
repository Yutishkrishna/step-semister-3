public class A3_PremiumLoyaltyDiscountLedger {

    static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        private int[] lateFeeHistory = new int[10];
        private int lateFeeCount = 0;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
                throw new IllegalArgumentException("Invalid memberId");
            }
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        protected void chargeLateFee(int amount) {
            lateFeeHistory[lateFeeCount] = amount;
            lateFeeCount++;
        }

        int[] getLateFeeHistory() {
            int[] copy = new int[lateFeeCount];
            System.arraycopy(lateFeeHistory, 0, copy, 0, lateFeeCount);
            return copy; // defensive copy - real internal array is never handed out
        }

        int getTotalLateFees() {
            int total = 0;
            for (int i = 0; i < lateFeeCount; i++) {
                total += lateFeeHistory[i];
            }
            return total;
        }
    }

    static class PremiumMember extends GymMember {
        String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        protected void chargeLateFee(int amount) {
            // reuses the parent's deduction AND recording logic in one call - halved amount only
            super.chargeLateFee(amount / 2);
        }
    }

    public static void main(String[] args) {
        PremiumMember p = new PremiumMember("MEM5", 2000, "Coach Riya");
        p.chargeLateFee(200);
        System.out.println(p.getTotalLateFees());

        int[] history = p.getLateFeeHistory();
        history[0] = 999; // mutating the returned copy must not touch the real history
        System.out.println(java.util.Arrays.toString(p.getLateFeeHistory()));
    }
}
