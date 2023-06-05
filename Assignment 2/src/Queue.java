public class Queue {
     Node head;
     Node tail;


    public Queue() {
        this.head = null;
        this.tail = null;
    }

    public void enqueue(Shape shape) {
        Node newNode = new Node(shape);

        if (tail == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }

        tail = newNode;
    }

    public Shape dequeue() {
        if (head == null) {
            return null;
        }

        Node dequeuedNode = head;
        head = head.getNext();

        if (head == null) {
            tail = null;
        }

        return dequeuedNode.getData();
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public Shape removeLast() {
        if (tail == null) {
            return null;
        }

        if (head == tail) {
            // there's only one element in the queue
            Shape removedShape = head.getData();
            head = null;
            tail = null;
            return removedShape;
        }

        Node currentNode = head;
        while (currentNode.getNext() != tail) {
            currentNode = currentNode.getNext();
        }

        Shape removedShape = tail.getData();
        tail = currentNode;
        tail.setNext(null);
        return removedShape;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
