public class A3_ReverseTheQueue {
 static class Node {int data;Node next;Node(int d){data=d;}}
 static Node reverse(Node head){Node prev=null,current=head;while(current!=null){Node next=current.next;current.next=prev;prev=current;current=next;}return prev;}
 static void print(Node h){while(h!=null){System.out.print(h.data+" -> ");h=h.next;}System.out.println("null");}
 public static void main(String[] args){Node h=new Node(1);h.next=new Node(2);h.next.next=new Node(3);h.next.next.next=new Node(4);print(reverse(h));Node one=new Node(9);print(reverse(one));System.out.println("Time O(n), extra space O(1).");}
}