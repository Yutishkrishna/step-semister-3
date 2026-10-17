public class A4_TrainCoachesBothWays {
 static class Node {String name;Node prev,next;Node(String n){name=n;}}
 static class Train {Node head,tail;
  void addLast(String name){Node n=new Node(name);if(head==null)head=tail=n;else{tail.next=n;n.prev=tail;tail=n;}}
  boolean remove(String name){Node c=head;while(c!=null&&!c.name.equals(name))c=c.next;if(c==null)return false;if(c.prev!=null)c.prev.next=c.next;else head=c.next;if(c.next!=null)c.next.prev=c.prev;else tail=c.prev;return true;}
  void printForward(){System.out.print("Forward: ");for(Node c=head;c!=null;c=c.next){System.out.print(c.name);if(c.next!=null)System.out.print(" <-> ");}System.out.println();}
  void printBackward(){System.out.print("Backward: ");for(Node c=tail;c!=null;c=c.prev){System.out.print(c.name);if(c.prev!=null)System.out.print(" <-> ");}System.out.println();}
 }
 public static void main(String[] args){Train t=new Train();for(String s:new String[]{"Engine","A1","B1","B2","Guard"})t.addLast(s);t.remove("B1");t.printForward();t.printBackward();System.out.println("Finding coach O(n); unlinking once found O(1).");}
}