import java.util.*;

class Solution {
    public int scoreOfParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int v = stack.pop();
                int score = (v == 0) ? 1 : 2 * v;
                stack.push(stack.pop() + score);
            }
        }
        return stack.pop();
    }
}