package programmers

class 거스름돈 {

    class Solution {
        fun solution(n: Int, money: IntArray): Int {
            val dp = IntArray(n+1) { 0 }
            dp[0] = 1
            money.forEach { m -> for (i in m .. n) dp[i] = (dp[i] + dp[i-m]) / 1000000007 }
            return dp[n]
        }
    }

}