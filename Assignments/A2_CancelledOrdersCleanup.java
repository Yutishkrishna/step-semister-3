public class A2_CancelledOrdersCleanup {
 static class Node {int data;Node next;Node(int d){data=d;}}
 static Node removeAll(Node head,int code){while(head!=null&&head.data==code)head=head.next;Node c=head;while(c!=null&&c.next!=null){if(c.next.data==code)c.next=c.next.next;else c=c.next;}return head;}
 static void print(Node h){while(h!=null){System.out.print(h.data+" -> ");h=h.next;}System.out.println("null");}
 public static void main(String[] args){Node h=new Node(5);h.next=new Node(3);h.next.next=new Node(5);h.next.next.next=new Node(8);h.next.next.next.next=new Node(5);print(removeAll(h,5));Node all=new Node(5);all.next=new Node(5);print(removeAll(all,5));System.out.println("Time O(n), extra space O(1).");}
}