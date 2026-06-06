import java.util.*;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

class QueueLL {
    Node front, rear;

    void enqueue(int pages) {
        Node newNode = new Node(pages);

        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        System.out.println("Print job with " + pages + " pages is enqueued.");
    }

    void dequeue() {
        if (front == null) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("Processing print job: " + front.data + " pages.");
        front = front.next;

        if (front == null) {
            rear = null;
        }
    }

    void display() {
        if (front == null) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.print("Print jobs in the queue: ");

        Node temp = front;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null)
                System.out.print(" ");
            temp = temp.next;
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QueueLL queue = new QueueLL();

        while (sc.hasNextInt()) {
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    if (sc.hasNextInt()) {
                        int pages = sc.nextInt();
                        queue.enqueue(pages);
                    }
                    break;

                case 2:
                    queue.dequeue();
                    break;

                case 3:
                    queue.display();
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        sc.close();
    }
}
