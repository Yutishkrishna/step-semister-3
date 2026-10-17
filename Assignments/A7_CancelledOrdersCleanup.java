public class A7_CancelledOrdersCleanup {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }
    static Node removeAll(Node head, int code) {
        while (head != null && head.data == code) head = head.next;
        Node current = head;
        while (current != null && current.next != null) {
            if (current.next.data == code) current.next = current.next.next;
            else current = current.next;
        }
        return head;
    }
    static void print(Node head) {
        if (head == null) { System.out.println("null"); return; }
        while (head != null) { System.out.print(head.data + " -> "); head = head.next; }
        System.out.println("null");
    }
    public static void main(String[] args) {
        Node head = new Node(5);
        head.next = new Node(3); head.next.next = new Node(5);
        head.next.next.next = new Node(8); head.next.next.next.next = new Node(5);
        print(removeAll(head, 5));
        Node all = new Node(5); all.next = new Node(5); all.next.next = new Node(5);
        print(removeAll(all, 5));
        System.out.println("Time: O(n), additional space: O(1).");
    }
}