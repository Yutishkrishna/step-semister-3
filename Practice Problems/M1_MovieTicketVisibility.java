public class M1_MovieTicketVisibility {

    // MovieTicket class demonstrating appropriate access modifiers
    static class MovieTicket {
        private String seatNumber;    // only MovieTicket internal logic needs direct access
        int screenId;                 // default (package-private): cinema hall internal ops in same package
        protected double ticketPrice; // protected: accessible to subclasses (e.g., PremiumMovieTicket)
        public String movieTitle;     // public: readable from any module or display board

        public MovieTicket(String seatNumber, int screenId, double ticketPrice, String movieTitle) {
            this.seatNumber = seatNumber;
            this.screenId = screenId;
            this.ticketPrice = ticketPrice;
            this.movieTitle = movieTitle;
        }

        public String getSeatNumber() {
            return seatNumber;
        }
    }

    // AccessChecker linter component
    static class AccessChecker {
        public static String classifyAccess(String fieldModifier, String accessorContext) {
            if (fieldModifier == null || accessorContext == null) {
                return "DENIED";
            }

            switch (accessorContext) {
                case "SAME_CLASS":
                    return "ALLOWED"; // All modifiers are accessible within the same class

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

                default:
                    return "DENIED";
            }
        }

        public static String summarizeBatch(String[][] attempts) {
            int allowed = 0;
            int denied = 0;

            if (attempts != null) {
                for (String[] attempt : attempts) {
                    if (attempt != null && attempt.length >= 2) {
                        String result = classifyAccess(attempt[0], attempt[1]);
                        if ("ALLOWED".equals(result)) {
                            allowed++;
                        } else {
                            denied++;
                        }
                    }
                }
            }

            return "Allowed: " + allowed + " | Denied: " + denied;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 1: Movie Ticket Field Visibility Checker ---");

        // Test individual classifications
        System.out.println("private in SAME_CLASS: " + AccessChecker.classifyAccess("private", "SAME_CLASS"));
        System.out.println("protected in DIFFERENT_PACKAGE: " + AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE"));

        // Test batch summarization
        String[][] sampleBatch = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println("Batch summary: " + AccessChecker.summarizeBatch(sampleBatch));

        // Demonstrate MovieTicket usage
        MovieTicket ticket = new MovieTicket("A12", 3, 250.0, "Inception");
        System.out.println("Movie: " + ticket.movieTitle + " | Screen: " + ticket.screenId);
    }
}
