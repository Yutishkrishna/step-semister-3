public class A1_PriorityTokenInsertion {
 static class Node { int data; Node next; Node(int d){data=d;} }
 static class List { Node head;
  void addFirst(int x){Node n=new Node(x);n.next=head;head=n;}
  void addLast(int x){Node n=new Node(x);if(head==null){head=n;return;}Node c=head;while(c.next!=null)c=c.next;c.next=n;}
  void insertAt(int i,int x){if(i<0)throw new IndexOutOfBoundsException();if(i==0){addFirst(x);return;}Node c=head;for(int k=0;c!=null&&k<i-1;k++)c=c.next;if(c==null)throw new IndexOutOfBoundsException();Node n=new Node(x);n.next=c.next;c.next=n;}
  void print(){Node c=head;while(c!=null){System.out.print(c.data+" -> ");c=c.next;}System.out.println("null");}
 }
 public static void main(String[] args){List l=new List();l.addLast(101);l.addLast(102);l.addLast(103);l.addFirst(100);l.addLast(104);l.insertAt(2,150);l.print();System.out.println("addFirst O(1), addLast O(n), insertAt O(n).");}
}