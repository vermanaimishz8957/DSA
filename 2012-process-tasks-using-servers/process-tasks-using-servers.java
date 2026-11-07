import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        // freeServers: stores [weight, index], ordered by weight then index
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // busyServers: stores [freeTime (long), weight, index], ordered by freeTime
        PriorityQueue<long[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            if (a[1] != b[1]) return Long.compare(a[1], b[1]);
            return Long.compare(a[2], b[2]);
        });

        // Initialize all servers as free
        for (int i = 0; i < servers.length; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }

        int m = tasks.length;
        int[] ans = new int[m];
        long currentTime = 0;

        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            // Release all servers that have finished their tasks by currentTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                long[] server = busyServers.poll();
                freeServers.offer(new int[]{(int) server[1], (int) server[2]});
            }

            // If no servers are free, fast-forward time to when the next server frees up
            if (freeServers.isEmpty()) {
                currentTime = busyServers.peek()[0];
                while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                    long[] server = busyServers.poll();
                    freeServers.offer(new int[]{(int) server[1], (int) server[2]});
                }
            }

            // Assign the task to the best available free server
            int[] bestServer = freeServers.poll();
            int weight = bestServer[0];
            int index = bestServer[1];

            ans[j] = index;
            busyServers.offer(new long[]{currentTime + tasks[j], weight, index});
        }

        return ans;
    }
}