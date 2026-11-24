
class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {

        int MOD = 1000000007;
        int n = s.length();

        long[] num = new long[n + 1];
        long[] sum = new long[n + 1];
        int[] count = new int[n + 1];
        long[] pow = new long[n + 1];

        pow[0] = 1;

        for (int i = 0; i < n; i++) {

            int d = s.charAt(i) - '0';

            pow[i + 1] = (pow[i] * 10) % MOD;

            num[i + 1] = num[i];
            sum[i + 1] = sum[i];
            count[i + 1] = count[i];

            if (d != 0) {
                num[i + 1] = (num[i] * 10 + d) % MOD;
                sum[i + 1] = (sum[i] + d) % MOD;
                count[i + 1]++;
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int l = queries[i][0];
            int r = queries[i][1];

            int k = count[r + 1] - count[l];

            long x = (num[r + 1] -
                     (num[l] * pow[k]) % MOD + MOD) % MOD;

            long digitSum = (sum[r + 1] -
                            sum[l] + MOD) % MOD;

            ans[i] = (int)((x * digitSum) % MOD);
        }

        return ans;
    }
}
