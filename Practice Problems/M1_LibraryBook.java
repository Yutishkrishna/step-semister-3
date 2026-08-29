public class M1_LibraryBook {

    static class LibraryBook {
        String title;
        String isbn;
        boolean catalogued;

        LibraryBook(String title, String isbn) {
            this.title = title;
            this.isbn = (isbn == null || isbn.trim().isEmpty()) ? "PENDING" : isbn;
            this.catalogued = true;
        }

        LibraryBook(String title) {
            this(title, null); // chains to the two-arg constructor, which applies the PENDING default
        }

        void printStatus() {
            System.out.println(title + " | " + isbn + " | Catalogued: " + catalogued);
        }
    }

    public static void main(String[] args) {
        String[] titles = {"Clean Code", "Untitled Draft", "1984", "Notes"};
        String[] isbns = {"978-0132350884", "", "9780451524935", ""};

        for (int i = 0; i < titles.length; i++) {
            LibraryBook book = isbns[i].isEmpty()
                    ? new LibraryBook(titles[i])
                    : new LibraryBook(titles[i], isbns[i]);
            book.printStatus();
        }
    }
}
