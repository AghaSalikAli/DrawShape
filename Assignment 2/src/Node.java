public class Node {
     Shape data;
     Node next;

    public Node(Shape data) {
        this.data = data;
        this.next = null;
    }

    public Shape getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}