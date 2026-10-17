import java.util.*;
public class P3_StudentClubRegistrationManagement {
 static class Student {String roll,name;Student(String r,String n){roll=r;name=n;}public boolean equals(Object o){return o instanceof Student s&&roll.equals(s.roll);}public int hashCode(){return roll.hashCode();}}
 public static void main(String[] args){Set<Student> members=new LinkedHashSet<>();add(members,new Student("21CS01","Asha"));add(members,new Student("21CS01","Asha"));add(members,new Student("21CS02","Ravi"));System.out.println("member count "+members.size());System.out.println("contains: "+members.contains(new Student("21CS02","Ravi")));System.out.println("contains: "+members.contains(new Student("21CS03","Priya")));}
 static void add(Set<Student>s,Student x){System.out.println(s.add(x)?"Added":"duplicate rejected");}
}