import java.util.*;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int mod = 1000000007;

        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];

        for (int[] row : score)
            Arrays.fill(row, -1);

        score[0][0] = 0;
        ways[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board.get(i).charAt(j) == 'X' || score[i][j] == -1)
                    continue;

                int[] dr = {1, 0, 1};
                int[] dc = {0, 1, 1};

                for (int d = 0; d < 3; d++) {
                    int r = i + dr[d];
                    int c = j + dc[d];

                    if (r < n && c < n && board.get(r).charAt(c) != 'X') {
                        int val = board.get(r).charAt(c) - '0';

                        if (board.get(r).charAt(c) == 'S')
                            val = 0;

                        int newScore = score[i][j] + val;

                        if (newScore > score[r][c]) {
                            score[r][c] = newScore;
                            ways[r][c] = ways[i][j];
                        } else if (newScore == score[r][c]) {
                            ways[r][c] = (ways[r][c] + ways[i][j]) % mod;
                        }
                    }
                }
            }
        }

        if (score[n - 1][n - 1] == -1)
            return new int[]{0, 0};

        return new int[]{score[n - 1][n - 1], ways[n - 1][n - 1]};
    }
}