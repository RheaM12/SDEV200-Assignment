import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise24_03 {

    // TwoWayLinkedList class
    static class TwoWayLinkedList<E> {
        private static class Node<E> {
            E element;
            Node<E> next;
            Node<E> previous;
            Node(E e) { element = e; }
        }

        private Node<E> head, tail;
        private int size;

        public void add(E e) {
            Node<E> newNode = new Node<>(e);
            if (tail == null) { head = tail = newNode; }
            else { tail.next = newNode; newNode.previous = tail; tail = newNode; }
            size++;
        }

        public E get(int index) {
            Node<E> node = head;
            for (int i = 0; i < index; i++) node = node.next;
            return node.element;
        }

        public void set(int index, E e) {
            Node<E> node = head;
            for (int i = 0; i < index; i++) node = node.next;
            node.element = e;
        }

        // Print numbers forward without labels
        public void printForward() {
            Node<E> current = head;
            while (current != null) {
                System.out.print(current.element + " ");
                current = current.next;
            }
            System.out.println();
        }

        // Print numbers backward without labels
        public void printBackward() {
            Node<E> current = tail;
            while (current != null) {
                System.out.print(current.element + " ");
                current = current.previous;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TwoWayLinkedList<Double> list = new TwoWayLinkedList<>();

        // Read five numbers
        for (int i = 0; i < 5; i++) {
            list.add(input.nextDouble());
        }

        // Multiply the third number by 10
        list.set(2, list.get(2) * 10);

        // Print numbers forward
        list.printForward();

        // Print numbers backward
        list.printBackward();

        input.close();
    }
}
