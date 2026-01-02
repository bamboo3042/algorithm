class Solution {
    fun solution(n: Int, tops: IntArray): Int {
        val dp = Array(n) { IntArray(2) { 0 } }
        val DIV = 10007
        
        if(tops[0] == 0) {
            dp[0][0] = 2
            dp[0][1] = 1
        }
        else {
            dp[0][0] = 3
            dp[0][1] = 1
        }
        
        for(i in 1 until n) {
            val prev = i - 1
            if(tops[i] == 0) {
                dp[i][0] = (dp[prev][0] * 2 + dp[prev][1])
                dp[i][1] = (dp[prev][0] + dp[prev][1])
            }
            else {
                dp[i][0] = (dp[prev][0] * 3 + dp[prev][1] * 2)
                dp[i][1] = (dp[prev][0] + dp[prev][1])
            }
            
            dp[i][0] %= DIV
            dp[i][1] %= DIV
        }
        
        return dp.last().sum() % DIV
    }
}