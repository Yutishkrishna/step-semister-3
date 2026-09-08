public class M2_SubclassTicketAccess {

    // Base MovieTicket class with protected field
    static class MovieTicket {
        protected double ticketPrice;

        public MovieTicket(double ticketPrice) {
            this.ticketPrice = ticketPrice;
        }
    }

    // Subclass representing a premium ticket in a loyalty module
    static class PremiumMovieTicket extends MovieTicket {
        private double discountRate;

        public PremiumMovieTicket(double ticketPrice, double discountRate) {
            super(ticketPrice);
            this.discountRate = discountRate;
        }

        public double getDiscountedPrice() {
            // In Java, a subclass in another package can access protected members through its OWN type (this.ticketPrice),
            // but NOT through an instance of the PARENT type (e.g. parentRef.ticketPrice).
            return this.ticketPrice * (1 - discountRate);
        }
    }

    // AccessChecker handling the 5 access contexts
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
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 2: Subclass Ticket Access ---");

        // Example 1: Subclass accessing protected via own type
        String ownTypeResult = AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE");
        System.out.println("protected via SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE: " + ownTypeResult);

        // Example 2: Subclass accessing protected via parent type
        String parentTypeResult = AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE");
        System.out.println("protected via SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE: " + parentTypeResult);

        // Other modifier checks
        System.out.println("public via SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE: " +
                AccessChecker.classifyAccess("public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
        System.out.println("default via SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE: " +
                AccessChecker.classifyAccess("default", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println("private via SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE: " +
                AccessChecker.classifyAccess("private", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));

        PremiumMovieTicket premium = new PremiumMovieTicket(300.0, 0.10);
        System.out.println("Premium ticket discounted price: " + premium.getDiscountedPrice());
    }
}