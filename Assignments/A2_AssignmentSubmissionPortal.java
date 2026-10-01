import java.time.*;
import java.time.temporal.ChronoUnit;

public class A2_AssignmentSubmissionPortal {
    static class Student {
        String name;
        Student(String name) { this.name = name; }
    }

    static abstract class Assignment {
        String title;
        int maxMarks;
        LocalDate dueDate;

        Assignment(String title, int maxMarks, LocalDate dueDate) {
            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDate = dueDate;
        }

        abstract double applyPenalty(double marks, long lateDays);
    }

    static class CodingAssignment extends Assignment {
        CodingAssignment(String t, int m, LocalDate d) { super(t, m, d); }
        double applyPenalty(double marks, long days) {
            return Math.max(0, marks * (1 - 0.10 * days));
        }
    }

    static class WrittenAssignment extends Assignment {
        WrittenAssignment(String t, int m, LocalDate d) { super(t, m, d); }
        double applyPenalty(double marks, long days) {
            return Math.max(0, marks * (1 - 0.20 * days));
        }
    }

    static class Submission {
        enum Status { SUBMITTED, GRADED }
        Student student;
        Assignment assignment;
        LocalDate date;
        private Status status = Status.SUBMITTED;

        Submission(Student s, Assignment a, LocalDate d) {
            student = s; assignment = a; date = d;
            long late = lateDays();
            System.out.println(s.name + "'s submission for '" + a.title + "' received (" +
                    (late == 0 ? "on time" : late + " days late") + "). Status: Submitted.");
        }

        long lateDays() {
            return Math.max(0, ChronoUnit.DAYS.between(assignment.dueDate, date));
        }

        void grade(double awarded) {
            if (status == Status.GRADED) return;
            double finalMarks = assignment.applyPenalty(awarded, lateDays());
            status = Status.GRADED;
            if (lateDays() > 0)
                System.out.printf("%s graded: %.0f/%d after %.0f%% late penalty. Status: Graded.%n",
                        student.name, finalMarks, assignment.maxMarks,
                        (1 - finalMarks / awarded) * 100);
            else
                System.out.printf("%s graded: %.0f/%d. Status: Graded.%n",
                        student.name, finalMarks, assignment.maxMarks);
        }

        void resubmit() {
            if (status == Status.GRADED)
                System.out.println("Cannot resubmit: '" + assignment.title + "' has already been graded.");
        }
    }

    public static void main(String[] args) {
        Student asha = new Student("Asha"), ravi = new Student("Ravi");
        Assignment coding = new CodingAssignment("Linked List Lab", 50, LocalDate.of(2026, 3, 10));
        Assignment written = new WrittenAssignment("Design Essay", 50, LocalDate.of(2026, 3, 12));

        Submission s1 = new Submission(asha, coding, LocalDate.of(2026, 3, 10));
        Submission s2 = new Submission(ravi, written, LocalDate.of(2026, 3, 14));
        s1.grade(45);
        s2.grade(40);
        s1.resubmit();
    }
}