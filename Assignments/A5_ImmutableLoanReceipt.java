import java.util.Arrays;

public class A5_ImmutableLoanReceipt {

    // Base immutable receipt class
    static class LoanReceipt {
        private final String memberId;
        private final String[] bookIds;

        public LoanReceipt(String memberId, String[] bookIds) {
            this.memberId = memberId;
            // Defensive copy on the way in
            this.bookIds = (bookIds != null) ? bookIds.clone() : new String[0];
        }

        public String getMemberId() {
            return memberId;
        }

        // Defensive copy on the way out to protect internal array
        public String[] getBookIds() {
            return bookIds.clone();
        }

        // Wither pattern: returns a brand new object, leaving original unchanged
        public LoanReceipt withCorrectedBookId(int index, String newId) {
            String[] updated = this.bookIds.clone();
            if (index >= 0 && index < updated.length) {
                updated[index] = newId;
            }
            return new LoanReceipt(this.memberId, updated);
        }
    }

    // Reference-only subclass for books restricted to designated rooms
    static class ReferenceOnlyLoanReceipt extends LoanReceipt {
        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        @Override
        public ReferenceOnlyLoanReceipt withCorrectedBookId(int index, String newId) {
            String[] updated = getBookIds();
            if (index >= 0 && index < updated.length) {
                updated[index] = newId;
            }
            return new ReferenceOnlyLoanReceipt(getMemberId(), updated, this.roomNumber);
        }
    }

    // CirculationLedger demonstrating static initializer block and polymorphic batch processing
    static class CirculationLedger {
        private static final String DEFAULT_BRANCH_CODE;

        static {
            // One-time class-level state initialization
            DEFAULT_BRANCH_CODE = "SRMIST-CENTRAL-LIB";
        }

        public static String getDefaultBranchCode() {
            return DEFAULT_BRANCH_CODE;
        }

        public static String processNightlyCirculation(LoanReceipt[] receipts) {
            int processed = 0;
            int nullSkipped = 0;
            int referenceOnly = 0;
            int regular = 0;

            if (receipts != null) {
                for (LoanReceipt r : receipts) {
                    if (r == null) {
                        nullSkipped++;
                        continue;
                    }

                    processed++;
                    if (r instanceof ReferenceOnlyLoanReceipt) {
                        referenceOnly++;
                    } else {
                        regular++;
                    }
                }
            }

            return processed + " processed | " + nullSkipped + " null skipped | " + referenceOnly + " reference-only | " + regular + " regular";
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Assignment 5: Immutable Loan Receipt & Nightly Circulation Ledger ---");
        System.out.println("Branch Code: " + CirculationLedger.getDefaultBranchCode());

        // Example 1: Defensive copying test
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println("Defensive copy check (should be BK-100): " + r.getBookIds()[0]);

        // Example 2: Wither pattern test
        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println("Original book IDs:  " + Arrays.toString(r.getBookIds()));
        System.out.println("Corrected book IDs: " + Arrays.toString(corrected.getBookIds()));

        // Example 3: Nightly circulation reconciliation
        LoanReceipt[] batch = new LoanReceipt[]{
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };

        String report = CirculationLedger.processNightlyCirculation(batch);
        System.out.println("Circulation Report: " + report);
    }
}