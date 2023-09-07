package programmers

class 올바른_괄호의_갯수 {
    class Solution {
        fun solution(n: Int): Int {
            if (n == 1) return 1
            if (n == 2) return 2
            if (n == 3) return 5
            val dp = IntArray(n+1) {0}
            dp[0] = 1
            dp[1] = 1
            dp[2] = 2
            dp[3] = 5

            for (t in 1 .. n) {
                var sum = 0
                var l = 0
                var r = t-1
                while (r > 0) sum += dp[l++]*dp[r--]
                dp[t] = sum
            }
            return dp[n]
        }
    }
}