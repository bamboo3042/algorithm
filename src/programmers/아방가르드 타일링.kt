package programmers

class `아방가르드 타일링` {
    class Solution {
        fun solution(n: Int): Int {
            if(n == 1) return 1
            if(n == 2) return 3
            if(n == 3) return 10
            if(n == 4) return 23
            if(n == 5) return 50
            if(n == 6) return 150

            val dp = LongArray(n) { 0 }
            val DIV = 1_000_000_007

            dp[0] = 1
            dp[1] = 3
            dp[2] = 10
            dp[3] = 23
            dp[4] = 50
            dp[5] = 150

            for(i in 6 until n) {
                dp[i] = ((dp[i-3] * 5) + (dp[i-2] * 2) + dp[i-1]) % DIV
            }

            return dp.last().toInt()
        }
    }
}