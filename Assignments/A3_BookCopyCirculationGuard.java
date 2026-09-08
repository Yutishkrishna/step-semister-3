public class A3_BookCopyCirculationGuard {

    static class BookInventory {
        private final int copiesTotal;
        private int copiesAvailable;

        public BookInventory(int copiesTotal) {
            if (copiesTotal <= 0) {
                throw new IllegalArgumentException("Invalid inventory size: copiesTotal must be positive, received " + copiesTotal);
            }
            this.copiesTotal = copiesTotal;
            this.copiesAvailable = copiesTotal; // Starts fully stocked
        }

        public void checkOut() {
            // Silently reject checkout if no copies are available to prevent negative inventory
            if (copiesAvailable > 0) {
                copiesAvailable--;
            }
        }

        public void checkIn() {
            // Silently reject checkin if inventory is already at total capacity
            if (copiesAvailable < copiesTotal) {
                copiesAvailable++;
            }
        }

        public int getCopiesAvailable() {
            return copiesAvailable;
        }

        public int getCopiesTotal() {
            return copiesTotal;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Assignment 3: Book Copy Circulation Guard ---");

        BookInventory b = new BookInventory(3);

        // Test checkout boundary
        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut(); // 4th attempt rejected silently
        System.out.println("Copies available after 4 checkouts on stock of 3: " + b.getCopiesAvailable()); // Output: 0

        // Test checkin boundary
        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn(); // 4th attempt rejected silently
        System.out.println("Copies available after 4 checkins on stock of 3: " + b.getCopiesAvailable()); // Output: 3
    }
}