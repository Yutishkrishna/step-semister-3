public class P3_StudentDiscountFineLedger {

    static class LibraryMember {
        protected String memberId;
        protected int borrowLimit;
        private int[] fineHistory = new int[10];
        private int fineCount = 0;

        public LibraryMember(String memberId, int borrowLimit) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
                throw new IllegalArgumentException("Invalid memberId");
            }
            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
        }

        protected void chargeFine(int amount) {
            fineHistory[fineCount] = amount;
            fineCount++;
        }

        int[] getFineHistory() {
            int[] copy = new int[fineCount];
            System.arraycopy(fineHistory, 0, copy, 0, fineCount);
            return copy;
        }

        int getTotalFine() {
            int total = 0;
            for (int i = 0; i < fineCount; i++) {
                total += fineHistory[i];
            }
            return total;
        }
    }

    static class StudentMember extends LibraryMember {
        String course;

        public StudentMember(String memberId, int borrowLimit, String course) {
            super(memberId, borrowLimit);
            this.course = course;
        }

        @Override
        protected void chargeFine(int amount) {
            super.chargeFine(amount / 2);
        }
    }

    public static void main(String[] args) {
        StudentMember s = new StudentMember("STU5", 3, "CSE");
        s.chargeFine(100);
        System.out.println(s.getTotalFine());

        int[] history = s.getFineHistory();
        history[0] = 999;
        System.out.println(java.util.Arrays.toString(s.getFineHistory()));
    }
}
