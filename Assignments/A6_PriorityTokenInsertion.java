public class A6_PriorityTokenInsertion {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }
    static class TokenList {
        Node head;
        void addFirst(int token) {
            Node n = new Node(token);
            n.next = head;
            head = n;
        }
        void addLast(int token) {
            Node n = new Node(token);
            if (head == null) { head = n; return; }
            Node current = head;
            while (current.next != null) current = current.next;
            current.next = n;
        }
        void insertAt(int index, int token) {
            if (index < 0) throw new IndexOutOfBoundsException("Negative index");
            if (index == 0) { addFirst(token); return; }
            Node current = head;
            for (int i = 0; current != null && i < index - 1; i++) current = current.next;
            if (current == null) throw new IndexOutOfBoundsException("Index exceeds list size");
            Node n = new Node(token);
            n.next = current.next;
            current.next = n;
        }
        void print() {
            Node current = head;
            while (current != null) { System.out.print(current.data + " -> "); current = current.next; }
            System.out.println("null");
        }
    }
    public static void main(String[] args) {
        TokenList list = new TokenList();
        list.addLast(101); list.addLast(102); list.addLast(103);
        list.addFirst(100); list.addLast(104); list.insertAt(2, 150);
        list.print();
        System.out.println("addFirst: O(1), addLast: O(n), insertAt: O(n) (O(1) at index 0).");
    }
}