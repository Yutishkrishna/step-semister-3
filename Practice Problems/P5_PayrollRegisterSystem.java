import java.util.*;
public class P5_PayrollRegisterSystem {
 static class Payslip {String name,type;long pay;Payslip(String n,String t,long p){name=n;type=t;pay=p;}public String toString(){return "Payslip[name="+name+", type="+type+", pay="+pay+"]";}}
 public static void main(String[] args){Payslip[] p={new Payslip("Asha","FullTime",50000),new Payslip("Ravi","PartTime",80L*300),new Payslip("Neha","Intern",15000)};long total=0;Payslip top=p[0];for(Payslip x:p){System.out.println(x);total+=x.pay;if(x.pay>top.pay)top=x;}System.out.println("Total "+total);System.out.println("top earner "+top.name);}
}