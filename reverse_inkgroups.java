public class reverse_inkgroups {
    class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}
    class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {

        if(head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevGroup = dummy;

        while(true){

            // find kth node
            ListNode kth = prevGroup;
            for(int i = 0; i < k && kth != null; i++){
                kth = kth.next;
            }

            if(kth == null) break; // not enough nodes

            ListNode groupStart = prevGroup.next;
            ListNode nextGroup = kth.next;

            // reverse inside the group (NO cutting)
            ListNode prev = nextGroup;
            ListNode curr = groupStart;

            while(curr != nextGroup){
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // reconnect
            prevGroup.next = kth;
            prevGroup = groupStart;
        }

        return dummy.next;
    }
}
}
