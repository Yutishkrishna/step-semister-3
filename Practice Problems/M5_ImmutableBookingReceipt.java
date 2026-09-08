import java.util.Arrays;

public class M5_ImmutableBookingReceipt {

    // Base immutable receipt class with defensive copying
    static class BookingReceipt {
        private final String bookingId;
        private final String[] seatNumbers;

        public BookingReceipt(String bookingId, String[] seatNumbers) {
            this.bookingId = bookingId;
            // Defensive copy on the way in
            this.seatNumbers = (seatNumbers != null) ? seatNumbers.clone() : new String[0];
        }

        public String getBookingId() {
            return bookingId;
        }

        // Defensive copy on the way out to prevent caller from mutating internal state
        public String[] getSeatNumbers() {
            return seatNumbers.clone();
        }

        // "Wither" pattern: returns a brand new object, leaving original unchanged
        public BookingReceipt withUpdatedSeat(int index, String newSeat) {
            String[] updatedSeats = this.seatNumbers.clone();
            if (index >= 0 && index < updatedSeats.length) {
                updatedSeats[index] = newSeat;
            }
            return new BookingReceipt(this.bookingId, updatedSeats);
        }
    }

    // Subclass for group bookings
    static class GroupBookingReceipt extends BookingReceipt {
        private final int groupSize;

        public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
            super(bookingId, seatNumbers);
            this.groupSize = groupSize;
        }

        public int getGroupSize() {
            return groupSize;
        }

        @Override
        public GroupBookingReceipt withUpdatedSeat(int index, String newSeat) {
            String[] updatedSeats = getSeatNumbers();
            if (index >= 0 && index < updatedSeats.length) {
                updatedSeats[index] = newSeat;
            }
            return new GroupBookingReceipt(getBookingId(), updatedSeats, this.groupSize);
        }
    }

    // Nightly settlement reconciliation engine
    static class SettlementProcessor {
        public static String processNightlySettlement(BookingReceipt[] receipts) {
            int processed = 0;
            int nullSkipped = 0;
            int group = 0;
            int individual = 0;

            if (receipts != null) {
                for (BookingReceipt r : receipts) {
                    if (r == null) {
                        nullSkipped++;
                        continue;
                    }

                    processed++;
                    if (r instanceof GroupBookingReceipt) {
                        group++;
                    } else {
                        individual++;
                    }
                }
            }

            return processed + " processed | " + nullSkipped + " null skipped | " + group + " group | " + individual + " individual";
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 5: Immutable Booking Receipt & Nightly Settlement ---");

        // Example 1: Defensive copying test
        BookingReceipt b = new BookingReceipt("CH-1001", new String[]{"A1", "A2"});
        String[] seats = b.getSeatNumbers();
        seats[0] = "X"; // Mutating the returned array
        System.out.println("Defensive copy check (should be A1): " + b.getSeatNumbers()[0]);

        // Example 2: Wither pattern test
        BookingReceipt updated = b.withUpdatedSeat(1, "A3");
        System.out.println("Original seats: " + Arrays.toString(b.getSeatNumbers()));
        System.out.println("Updated seats:  " + Arrays.toString(updated.getSeatNumbers()));

        // Example 3: Nightly settlement with null tolerance and instanceof dispatch
        BookingReceipt[] batch = new BookingReceipt[]{
            new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
            null,
            new BookingReceipt("CH-3003", new String[]{"C1"})
        };

        String settlementReport = SettlementProcessor.processNightlySettlement(batch);
        System.out.println("Nightly settlement: " + settlementReport);
    }
}