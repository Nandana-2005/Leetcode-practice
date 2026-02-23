import java.util.*;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
};


public class Main{
    static Node insertAtEnd(Node head, int data) {
        Node newNode = new Node(data);

    
        if (head == null) {
            return newNode;
        }

        Node temp = head;

        
        while (temp.next != null) {
            temp = temp.next;
        }

        
        temp.next = newNode;

        return head;
    }

    
    static void printList(Node head) {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    static Node head = null;

    public static void main(String[] args) {

        head = insertAtEnd(head, 10);
        head = insertAtEnd(head, 20);
        head = insertAtEnd(head, 30);

        printList(head);
    }
}