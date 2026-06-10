import java.util.*;

class ListNode{
    int val;
    ListNode next;
    
    ListNode(int val){
        this.val = val;
    }
}
public class Main{
    
    public static ListNode mergeKLists(ListNode[] lists){
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)-> a.val - b.val);
        
        //all the heads are put first in the pq
        for(ListNode node : lists){
            if(node != null){
                pq.offer(node);
            }
        }
        
        //dummy node to create a starting 
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        
        //minheap will return min element and that is added after dummy to cretae final LL
        while(!pq.isEmpty()){
            ListNode minNode = pq.poll();
            
            curr.next = minNode;
            curr = curr.next;
            
            //after min removed, it is replaced with next element in corresponding LL
            if(minNode.next != null){
                pq.offer(minNode.next);
            }
        }
        
        return dummy.next;
        
        
    }
    
    public static void printList(ListNode head){
        while(head != null){
            System.out.print(head.val+" ");
            head = head.next;
            
        }
        System.out.println();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        
        ListNode[] lists = new ListNode[k];
        
        for(int i = 0;i<k;i++){
            int n = sc.nextInt();
            
            ListNode dummy = new ListNode(-1);
            ListNode curr = dummy;
            
            for(int j = 0;j<n;j++){
                curr.next = new ListNode(sc.nextInt());
                curr = curr.next;
            }
            
            lists[i] = dummy.next;
        }
        
        ListNode result = mergeKLists(lists);
        
        printList(result);
    }
}
