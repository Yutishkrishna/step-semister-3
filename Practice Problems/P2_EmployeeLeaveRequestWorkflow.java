import java.time.*;

public class P2_EmployeeLeaveRequestWorkflow {
    static abstract class Employee {
        String name;
        Employee(String n){name=n;}
        abstract boolean canRequest(int days);
    }
    static class FullTimeEmployee extends Employee {
        FullTimeEmployee(String n){super(n);}
        boolean canRequest(int days){return days<=30;}
    }
    static class PartTimeEmployee extends Employee {
        PartTimeEmployee(String n){super(n);}
        boolean canRequest(int days){return days<=10;}
    }
    static class Contractor extends Employee {
        Contractor(String n){super(n);}
        boolean canRequest(int days){return days<=5;}
    }
    static class LeaveRequest {
        enum Status { PENDING, APPROVED, REJECTED }
        Employee employee; LocalDate start,end;
        private Status status=Status.PENDING;

        LeaveRequest(Employee e,LocalDate s,LocalDate end){
            employee=e;start=s;this.end=end;
            long days=java.time.temporal.ChronoUnit.DAYS.between(s,end)+1;
            if(!e.canRequest((int)days)) throw new IllegalArgumentException("Leave not allowed.");
            System.out.println("Leave request submitted for "+e.name+" ("+s+" to "+end+"). Status: Pending.");
        }
        void approve(){
            if(status!=Status.PENDING) return;
            status=Status.APPROVED;
            System.out.println(employee.name+"'s leave request approved. Status: Approved.");
        }
        void reject(){
            if(status!=Status.PENDING) return;
            status=Status.REJECTED;
            System.out.println(employee.name+"'s leave request rejected. Status: Rejected.");
        }
        void changeToPending(){
            if(status!=Status.PENDING)
                System.out.println("Cannot change leave request status from "+status+" to Pending.");
        }
    }
    public static void main(String[] args){
        LeaveRequest john=new LeaveRequest(new FullTimeEmployee("John"),LocalDate.of(2026,1,1),LocalDate.of(2026,1,5));
        john.approve();
        LeaveRequest jane=new LeaveRequest(new PartTimeEmployee("Jane"),LocalDate.of(2026,2,10),LocalDate.of(2026,2,11));
        jane.reject();
        john.changeToPending();
    }
}