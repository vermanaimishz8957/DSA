class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0, count = 0;

        int[][] id = new int[m][n];

        for (int[] row : id) {
            java.util.Arrays.fill(row, -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    id[i][j] = count++;
                }
            }
        }

        int fullMask = (1 << count) - 1;

        if (fullMask == 0) {
            return 0;
        }

        boolean[][][][] visited =
            new boolean[m][n][1 << count][energy + 1];

        java.util.Queue<int[]> q = new java.util.LinkedList<>();

        q.offer(new int[]{sr, sc, energy, 0});
        visited[sr][sc][0][energy] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] cur = q.poll();

                int r = cur[0];
                int c = cur[1];
                int e = cur[2];
                int mask = cur[3];

                if (mask == fullMask) {
                    return moves;
                }

                if (e == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    char ch = classroom[nr].charAt(nc);

                    if (ch == 'X') {
                        continue;
                    }

                    int ne = e - 1;
                    int nmask = mask;

                    if (ch == 'L') {
                        nmask |= (1 << id[nr][nc]);
                    }

                    if (ch == 'R') {
                        ne = energy;
                    }

                    if (!visited[nr][nc][nmask][ne]) {
                        visited[nr][nc][nmask][ne] = true;
                        q.offer(new int[]{nr, nc, ne, nmask});
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}