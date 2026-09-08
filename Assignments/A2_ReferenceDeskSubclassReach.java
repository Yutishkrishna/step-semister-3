public class A2_ReferenceDeskSubclassReach {

    // Base LibraryMember class with protected field
    static class LibraryMember {
        protected double finesOwed;

        public LibraryMember(double finesOwed) {
            this.finesOwed = finesOwed;
        }
    }

    // Subclass in another package
    static class PremiumLibraryMember extends LibraryMember {
        private double waiverLimit;

        public PremiumLibraryMember(double finesOwed, double waiverLimit) {
            super(finesOwed);
            this.waiverLimit = waiverLimit;
        }

        public double getPayableFine() {
            // Accessible through 'this' (own type) in a subclass across packages
            return Math.max(0, this.finesOwed - waiverLimit);
        }
    }

    // AccessChecker with early-exit scanner for the first denied attempt
    static class AccessChecker {
        public static String classifyAccess(String fieldModifier, String accessorContext) {
            if (fieldModifier == null || accessorContext == null) {
                return "DENIED";
            }

            switch (accessorContext) {
                case "SAME_CLASS":
                    return "ALLOWED";

                case "SAME_PACKAGE":
                    if ("default".equals(fieldModifier) || "protected".equals(fieldModifier) || "public".equals(fieldModifier)) {
                        return "ALLOWED";
                    }
                    return "DENIED";

                case "DIFFERENT_PACKAGE":
                    if ("public".equals(fieldModifier)) {
                        return "ALLOWED";
                    }
                    return "DENIED";

                case "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE":
                    if ("public".equals(fieldModifier) || "protected".equals(fieldModifier)) {
                        return "ALLOWED";
                    }
                    return "DENIED";

                case "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE":
                    if ("public".equals(fieldModifier)) {
                        return "ALLOWED";
                    }
                    return "DENIED";

                default:
                    return "DENIED";
            }
        }

        public static String firstDeniedAttempt(String[][] attempts) {
            if (attempts != null) {
                for (int i = 0; i < attempts.length; i++) {
                    String[] attempt = attempts[i];
                    if (attempt != null && attempt.length >= 2) {
                        String mod = attempt[0];
                        String ctx = attempt[1];
                        String decision = classifyAccess(mod, ctx);
                        if ("DENIED".equals(decision)) {
                            // Early exit immediately without processing subsequent attempts
                            return mod + " via " + ctx + " (attempt #" + (i + 1) + ")";
                        }
                    }
                }
            }
            return "None Denied";
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Assignment 2: Reference Desk Subclass Reach ---");

        // Example 1: Finding first denied attempt
        String[][] attempts1 = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println("Batch 1 result: " + AccessChecker.firstDeniedAttempt(attempts1));

        // Example 2: All attempts allowed
        String[][] attempts2 = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println("Batch 2 result: " + AccessChecker.firstDeniedAttempt(attempts2));

        PremiumLibraryMember pm = new PremiumLibraryMember(50.0, 20.0);
        System.out.println("Net payable fine: " + pm.getPayableFine());
    }
}