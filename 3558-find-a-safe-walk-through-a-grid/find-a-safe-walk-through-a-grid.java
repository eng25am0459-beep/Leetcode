import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] best = new int[m][n];
        for (int[] row : best)
            Arrays.fill(row, -1);

        Queue<int[]> q = new LinkedList<>();

        health -= grid.get(0).get(0);

        if (health <= 0)
            return false;

        best[0][0] = health;
        q.add(new int[]{0, 0, health});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];
            int h = curr[2];

            if (r == m - 1 && c == n - 1)
                return true;

            if (h < best[r][c])
                continue;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int newHealth = h - grid.get(nr).get(nc);

                    if (newHealth > 0 && newHealth > best[nr][nc]) {
                        best[nr][nc] = newHealth;
                        q.add(new int[]{nr, nc, newHealth});
                    }
                }
            }
        }

        return false;
    }
}