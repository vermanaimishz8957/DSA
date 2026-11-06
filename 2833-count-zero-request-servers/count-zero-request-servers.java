import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[] res = new int[m];
        
        // Sort logs by time (logs[i][1])
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        // Pair queries with their original indices
        int[][] indexedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            indexedQueries[i][0] = queries[i];
            indexedQueries[i][1] = i;
        }
        
        // Sort queries by time
        Arrays.sort(indexedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] serverCounts = new int[n + 1];
        int activeServers = 0;
        int left = 0, right = 0;
        int numLogs = logs.length;
        
        for (int i = 0; i < m; i++) {
            int qTime = indexedQueries[i][0];
            int qIdx = indexedQueries[i][1];
            int windowStart = qTime - x;
            int windowEnd = qTime;
            
            // Expand window rightwards to include logs up to windowEnd
            while (right < numLogs && logs[right][1] <= windowEnd) {
                int serverId = logs[right][0];
                if (serverCounts[serverId] == 0) {
                    activeServers++;
                }
                serverCounts[serverId]++;
                right++;
            }
            
            // Shrink window leftwards to exclude logs before windowStart
            while (left < numLogs && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                serverCounts[serverId]--;
                if (serverCounts[serverId] == 0) {
                    activeServers--;
                }
                left++;
            }
            
            // Servers with zero requests in the current window
            res[qIdx] = n - activeServers;
        }
        
        return res;
    }
}