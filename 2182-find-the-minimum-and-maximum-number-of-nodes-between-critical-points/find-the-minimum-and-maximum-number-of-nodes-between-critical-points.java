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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 1;

        int firstCriticalIndex = -1;
        int prevCriticalIndex = -1;
        int minDistance = Integer.MAX_VALUE;

        while (curr.next != null) {
            boolean isLocalMaxima = (curr.val > prev.val) && (curr.val > curr.next.val);
            boolean isLocalMinima = (curr.val < prev.val) && (curr.val < curr.next.val);

            if (isLocalMaxima || isLocalMinima) {
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = currentIndex;
                } else {
                    minDistance = Math.min(minDistance, currentIndex - prevCriticalIndex);
                }
                prevCriticalIndex = currentIndex;
            }

            prev = curr;
            curr = curr.next;
            currentIndex++;
        }

        if (firstCriticalIndex == -1 || firstCriticalIndex == prevCriticalIndex) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevCriticalIndex - firstCriticalIndex;
        return new int[]{minDistance, maxDistance};
    }
}