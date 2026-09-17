public class P2_ThreeBranchesOfMembershipTree {

    static class LibraryMember {
        protected String memberId;
        protected int borrowLimit;
        protected int booksBorrowed;

        public LibraryMember(String memberId, int borrowLimit) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
                throw new IllegalArgumentException("Invalid memberId");
            }
            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
        }

        void borrowBook() {
            booksBorrowed++;
        }

        int getBooksBorrowed() {
            return booksBorrowed;
        }

        String displayInfo() {
            return "General Member | Books Borrowed: " + booksBorrowed;
        }
    }

    static class StudentMember extends LibraryMember {
        String course;

        public StudentMember(String memberId, int borrowLimit, String course) {
            super(memberId, borrowLimit);
            this.course = course;
        }

        @Override
        String displayInfo() {
            return "Student Member | Course: " + course + " | Books Borrowed: " + booksBorrowed;
        }
    }

    static class HonorsStudentMember extends StudentMember {
        int bonusLimit;

        public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
            super(memberId, borrowLimit, course);
            this.bonusLimit = bonusLimit;
        }

        @Override
        String displayInfo() {
            return "Honors Student Member | Course: " + course + " | Bonus Limit: " + bonusLimit + " | Books Borrowed: " + booksBorrowed;
        }
    }

    static class FacultyMember extends LibraryMember {
        String department;

        public FacultyMember(String memberId, int borrowLimit, String department) {
            super(memberId, borrowLimit);
            this.department = department;
        }

        @Override
        String displayInfo() {
            return "Faculty Member | Department: " + department + " | Books Borrowed: " + booksBorrowed;
        }
    }

    static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        }
        if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }
        if (member instanceof StudentMember) {
            return "Direct subclass";
        }
        return "Base class";
    }

    static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        for (LibraryMember member : members) {
            total += member.getBooksBorrowed();
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(new LibraryMember("STU1", 3).displayInfo());
        System.out.println(new StudentMember("STU2", 3, "CSE").displayInfo());
        System.out.println(new HonorsStudentMember("STU3", 3, "ECE", 2).displayInfo());
        System.out.println(new FacultyMember("STU4", 5, "Physics").displayInfo());

        StudentMember studentMember = new StudentMember("STU5", 3, "CSE");
        HonorsStudentMember honorsMember = new HonorsStudentMember("STU6", 3, "ECE", 2);
        FacultyMember facultyMember = new FacultyMember("STU7", 5, "Physics");

        System.out.println(classifyGeneration(honorsMember));
        System.out.println(classifyGeneration(facultyMember));

        studentMember.borrowBook();
        studentMember.borrowBook();
        honorsMember.borrowBook();
        facultyMember.borrowBook();
        facultyMember.borrowBook();
        facultyMember.borrowBook();

        System.out.println(getTotalBooksBorrowed(new LibraryMember[]{studentMember, honorsMember, facultyMember}));
    }
}
