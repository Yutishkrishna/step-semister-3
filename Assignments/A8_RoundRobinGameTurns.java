public class A8_RoundRobinGameTurns {
    static class Node {
        String name;
        Node next;
        Node(String name) { this.name = name; }
    }
    static class CircularPlayerList {
        private Node tail;
        void addLast(String name) {
            Node node = new Node(name);
            if (tail == null) {
                tail = node;
                tail.next = tail;
            } else {
                node.next = tail.next;
                tail.next = node;
                tail = node;
            }
        }
        void printTurns(int turns) {
            if (tail == null) { System.out.println("No players."); return; }
            Node current = tail.next;
            for (int i = 0; i < turns; i++) {
                System.out.print(current.name + (i == turns - 1 ? "\n" : " "));
                current = current.next;
            }
        }
    }
    public static void main(String[] args) {
        CircularPlayerList players = new CircularPlayerList();
        players.addLast("Asha"); players.addLast("Ravi"); players.addLast("Neha");
        players.printTurns(7);
        System.out.println("addLast: O(1), printTurns: O(turns).");
    }
}