import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;
        int[] requestCount = new int[k];
        
        // Min-heap to store busy servers: elements are {free_time, server_id}
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        // TreeSet to store indices of available servers
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
        
        for (int i = 0; i < n; i++) {
            int currTime = arrival[i];
            int duration = load[i];
            
            // Free up servers whose tasks have finished by currTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currTime) {
                availableServers.add(busyServers.poll()[1]);
            }
            
            // If all servers are busy, drop the request
            if (availableServers.isEmpty()) {
                continue;
            }
            
            // Find the preferred server using round-robin starting at (i % k)
            int preferred = i % k;
            Integer serverId = availableServers.ceiling(preferred);
            
            // If no available server >= preferred, wrap around to the smallest available index
            if (serverId == null) {
                serverId = availableServers.first();
            }
            
            // Assign the request to this server
            availableServers.remove(serverId);
            requestCount[serverId]++;
            busyServers.offer(new int[] { currTime + duration, serverId });
        }
        
        // Find the maximum number of requests handled by any server
        int maxRequests = 0;
        for (int count : requestCount) {
            maxRequests = Math.max(maxRequests, count);
        }
        
        // Collect all servers that handled the maximum number of requests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}