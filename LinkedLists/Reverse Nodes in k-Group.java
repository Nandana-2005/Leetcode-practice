import java.util.Scanner;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class ReverseKGroup {
    public static ListNode reverseLinkedList(ListNode head, int k) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;
        int count = 0;
        while (curr != null && count < k) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }
        return prev;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;
        ListNode current = head;
        while (current != null) {
            ListNode groupStart = current;
            int count = 0;
            while (current != null && count < k) {
                current = current.next;
                count++;
            }
            if (count == k) {
                ListNode groupEnd = groupStart;
                for (int i = 1; i < k; i++) {
                    groupEnd = groupEnd.next;
                }
                ListNode nextGroupStart = groupEnd.next;
                groupEnd.next = null;
                ListNode newGroupStart = reverseLinkedList(groupStart, k);
                prevGroupEnd.next = newGroupStart;
                groupStart.next = nextGroupStart;
                prevGroupEnd = groupStart;
                current = nextGroupStart;
            } else {
                break;
            }
        }
        return dummy.next;
    }

    public static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(head.val);
            head = head.next;
        }
        System.out.println(sb.toString());
    }

    public static ListNode createListFromInput(int n, Scanner scanner) {
        if (n == 0) return null;
        int val = scanner.nextInt();
        ListNode head = new ListNode(val);
        ListNode current = head;
        for (int i = 1; i < n; i++) {
            val = scanner.nextInt();
            current.next = new ListNode(val);
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ListNode head = createListFromInput(n, scanner);
        int k = scanner.nextInt();
        ListNode newHead = reverseKGroup(head, k);
        printList(newHead);
        scanner.close();
    }
}
