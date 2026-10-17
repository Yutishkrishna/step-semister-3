public class A5_RoundRobinGameTurns {
 static class Node {String name;Node next;Node(String n){name=n;}}
 static class Players {Node tail;
  void addLast(String name){Node n=new Node(name);if(tail==null){tail=n;tail.next=tail;}else{n.next=tail.next;tail.next=n;tail=n;}}
  void printTurns(int turns){if(tail==null){System.out.println("No players");return;}Node c=tail.next;for(int i=0;i<turns;i++){System.out.print(c.name+(i==turns-1?"\n":" "));c=c.next;}}
 }
 public static void main(String[] args){Players p=new Players();p.addLast("Asha");p.addLast("Ravi");p.addLast("Neha");p.printTurns(7);System.out.println("addLast O(1); printTurns O(turns).");}
}