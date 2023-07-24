package programmers

class 에어컨 {
    class Solution {
        fun solution(temperature: Int, t1: Int, t2: Int, a: Int, b: Int, onboard: IntArray): Int {
            if (temperature in t1 .. t2) return 0
            var answer: Int = 0
            val dp = Array(onboard.size) { IntArray(51) {1000000} }
            val temp = temperature + 10
            val left = t1 + 10
            val right = t2 + 10
            var lastIndex = 0

            dp[0][temp] = 0
            if (temp > 0) dp[0][temp-1] = a
            if (temp < 50) dp[0][temp+1] = a

            for (i in 1 until  onboard.size) {
                for (j in 0 .. 50) {
                    val temp1 = if (j-1 < 0) 1000000 else if (j-1 < temp) dp[i-1][j-1] else dp[i-1][j-1] + a
                    val temp2 = if (j == temp) dp[i-1][j] else dp[i-1][j] + b
                    val temp3 = if (j+1 > 50) 1000000 else if (j+1 > temp) dp[i-1][j+1] else dp[i-1][j+1] + a
                    if (i in 1 .. 2 && j in 37 .. 39) println("j: $j $temp1 $temp2 $temp3")
                    dp[i][j] = minOf(temp1, temp2 ,temp3)
                }
                if (onboard[i] == 1) {
                    lastIndex = i
                    for (j in 0 .. 50) if (j !in left .. right) dp[i][j] = 1000000
                }
            }
            answer = dp[lastIndex][right]
            for (j in left until right) answer = minOf(answer, dp[lastIndex][j])

            return answer
        }
    }
}