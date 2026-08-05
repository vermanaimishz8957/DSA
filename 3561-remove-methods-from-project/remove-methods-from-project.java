import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        
        List<Integer>[] graph = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : invocations) {
            graph[edge[0]].add(edge[1]);
        }
        
        boolean[] suspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(k);
        suspicious[k] = true;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            for (int next : graph[curr]) {
                if (!suspicious[next]) {
                    suspicious[next] = true;
                    queue.offer(next);
                }
            }
        }
        
        // Check if any non-suspicious method invokes a suspicious method
        for (int[] edge : invocations) {
            if (!suspicious[edge[0]] && suspicious[edge[1]]) {
                List<Integer> result = new ArrayList<>();
                
                for (int i = 0; i < n; i++) {
                    result.add(i);
                }
                
                return result;
            }
        }
        
        // Remove suspicious methods
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                result.add(i);
            }
        }
        
        return result;
    }
}