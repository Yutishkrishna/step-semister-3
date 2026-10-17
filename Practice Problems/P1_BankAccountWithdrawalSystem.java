import java.util.*;
public class P1_BankAccountWithdrawalSystem {
 static abstract class Account {String id;double balance;Account(String i,double b){id=i;balance=b;}abstract boolean allowed(double amount);String reason(){return "withdrawal rejected";}boolean withdraw(double amount){if(!allowed(amount)){System.out.println(id+" rejected: "+reason());return false;}balance-=amount;System.out.println(id+" balance "+(long)balance);return true;}}
 static class Savings extends Account {Savings(String i,double b){super(i,b);}boolean allowed(double a){return balance-a>=1000;}String reason(){return "minimum balance 1000";}}
 static class Current extends Account {Current(String i,double b){super(i,b);}boolean allowed(double a){return balance-a>=-5000;}String reason(){return "overdraft limit 5000";}}
 public static void main(String[] args){Map<String,Account> accounts=new LinkedHashMap<>();accounts.put("S1",new Savings("S1",5000));accounts.get("S1").withdraw(4500);accounts.put("C1",new Current("C1",2000));accounts.get("C1").withdraw(6000);accounts.get("S1").withdraw(3000);accounts.get("C1").withdraw(2000);}
}