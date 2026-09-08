public class M3_SeatBookingEncapsulation {

    static class CineScreen {
        private final int seatsTotal;
        private int seatsAvailable;

        public CineScreen(int seatsTotal) {
            if (seatsTotal <= 0) {
                throw new IllegalArgumentException("Construction rejected: seatsTotal must be positive, received " + seatsTotal);
            }
            this.seatsTotal = seatsTotal;
            this.seatsAvailable = seatsTotal;
        }

        public void bookSeat() {
            // Silently reject if no seats are available to prevent negative counts
            if (seatsAvailable > 0) {
                seatsAvailable--;
            }
        }

        public void cancelBooking() {
            // Silently reject if all seats are already available to prevent exceeding total capacity
            if (seatsAvailable < seatsTotal) {
                seatsAvailable++;
            }
        }

        public int getSeatsAvailable() {
            return seatsAvailable;
        }

        public int getSeatsTotal() {
            return seatsTotal;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 3: Seat Booking Encapsulation Guard ---");

        // Test 1: Constructor-time validation
        try {
            new CineScreen(0);
            System.out.println("new CineScreen(0): Accepted (unexpected)");
        } catch (IllegalArgumentException e) {
            System.out.println("new CineScreen(0): construction rejected (" + e.getMessage() + ")");
        }

        // Test 2: Booking boundary (never goes below zero)
        CineScreen c = new CineScreen(2);
        c.bookSeat();
        c.bookSeat();
        c.bookSeat(); // 3rd booking rejected silently
        System.out.println("Seats available after 3 bookings on screen of 2: " + c.getSeatsAvailable()); // Output: 0

        // Test 3: Cancellation boundary (never exceeds total)
        c.cancelBooking();
        c.cancelBooking();
        c.cancelBooking(); // 3rd cancellation rejected silently
        System.out.println("Seats available after 3 cancellations on screen of 2: " + c.getSeatsAvailable()); // Output: 2
    }
}