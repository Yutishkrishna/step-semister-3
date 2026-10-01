import java.util.*;

public class A5_CampusNoticeBroadcaster {
    interface NotificationChannel {
        void send(Student student, String message);
    }
    static class EmailChannel implements NotificationChannel {
        public void send(Student s,String m){System.out.println("[Email → "+s.name+"] "+m);}
    }
    static class SmsChannel implements NotificationChannel {
        public void send(Student s,String m){System.out.println("[SMS → "+s.name+"] "+m);}
    }
    static class AppChannel implements NotificationChannel {
        public void send(Student s,String m){System.out.println("[App → "+s.name+"] "+m);}
    }
    static class Student {
        String name, department;
        List<NotificationChannel> channels;
        Student(String n,String d,NotificationChannel... c){
            name=n; department=d; channels=Arrays.asList(c);
        }
    }
    static class Notice {
        String title;
        Set<String> departments;
        Notice(String title,String... departments){
            this.title=title;
            this.departments=new LinkedHashSet<>(Arrays.asList(departments));
        }
        boolean valid(){
            return title!=null && !title.isBlank() && !departments.isEmpty();
        }
    }
    static class NoticeBoard {
        List<Student> students=new ArrayList<>();
        void addStudent(Student s){students.add(s);}
        void post(Notice n){
            if(n.departments.isEmpty()){
                System.out.println("Cannot post notice: At least one target department is required.");
                return;
            }
            if(n.title==null || n.title.isBlank()){
                System.out.println("Cannot post notice: Title is required.");
                return;
            }
            System.out.println("Notice '"+n.title+"' posted to "+String.join(", ",n.departments)+".");
            for(Student s:students)
                if(n.departments.contains(s.department))
                    for(NotificationChannel c:s.channels) c.send(s,n.title);
        }
    }
    public static void main(String[] args){
        NoticeBoard board=new NoticeBoard();
        board.addStudent(new Student("Asha","CSE",new EmailChannel(),new AppChannel()));
        board.addStudent(new Student("Ravi","ECE",new SmsChannel()));
        board.post(new Notice("Lab Closed Tomorrow","CSE"));
        board.post(new Notice("Fee Deadline Extended","CSE","ECE"));
        board.post(new Notice("Sports Day"));
    }
}