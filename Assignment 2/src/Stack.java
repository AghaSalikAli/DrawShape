
public class Stack {
     Node head;
     int n;

    public Stack() {
        this.head = null;
        n = 0;
    }

    public void push(Shape shape) {
        Node newNode = new Node(shape);
        newNode.setNext(head);
        head = newNode;
        n++;
    }

    public Shape pop() {
        if (head == null) {
            return null;
        }

        Node poppedNode = head;
        head = head.getNext();
        n--;
        return poppedNode.getData();

    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return n;
    }

    public Shape get(int index) {
        if (index < 0 || index >= n) throw new IndexOutOfBoundsException();
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
}