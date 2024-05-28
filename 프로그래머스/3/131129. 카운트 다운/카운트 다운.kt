class Solution {
    fun solution(target: Int): IntArray {
        val answer: IntArray = intArrayOf(0, 0)
        val dp = Array(311) {0 to 0}
        for (i in 1 .. 20) dp[i] = 1 to 1
        for (i in 21 .. 40) {
            dp[i] = if (i % 2 == 0 && i <= 40) 1 to 0
            else if (i % 3 == 0) 1 to 0
            else 2 to 2
        }
        for (i in 41 .. 49) {
            dp[i] = if (i % 3 == 0) 1 to 0
            else 2 to 1
        }
        dp[50] = 1 to 1
        for (i in 51 .. 60) dp[i] = if(i % 3 == 0) 1 to 0 else 2 to 2
        for (i in 61 .. 310) {
            dp[i] = if (dp[i-60].first < dp[i-50].first) dp[i-60].first + 1 to dp[i-60].second
            else dp[i-50].first + 1 to dp[i-50].second + 1
        }
        
        return if (target <= 310) intArrayOf(dp[target].first, dp[target].second)
        else {
            val x = (target - 250) / 60
            val y = (target - 250) % 60
            
            intArrayOf(dp[250 + y].first + x, dp[250 + y].second)
        }
    }
}