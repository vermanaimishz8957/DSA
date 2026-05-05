/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        
        // Edge cases
        if (head == null || head.next == null || k == 0) return head;

        // Step 1: Find length of list
        ListNode temp = head;
        int length = 1;
        
        while (temp.next != null) {
            temp = temp.next;
            length++;
        }

        // Step 2: Make it circular
        temp.next = head;

        // Step 3: Optimize k
        k = k % length;

        // Step 4: Find new tail
        int stepsToNewTail = length - k - 1;
        ListNode newTail = head;

        for (int i = 0; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        // Step 5: Set new head
        ListNode newHead = newTail.next;

        // Step 6: Break the circle
        newTail.next = null;

        return newHead;
    }
}