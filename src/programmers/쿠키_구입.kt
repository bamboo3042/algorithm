package programmers

import kotlin.math.max

class 쿠키_구입 {
    class Solution {
        fun solution(cookie: IntArray): Int {
            var answer = 0
            val dp = Array(cookie.size) { IntArray(cookie.size) { 0 } }

            for (i in cookie.indices) {
                dp[i][i] = cookie[i]
                for (j in i + 1 until cookie.size) {
                    dp[i][j] = dp[i][j - 1] + cookie[j]
                }
            }

            for (m in cookie.indices) {
                var l = m
                var r = m+1
                while (l >= 0 && r <= cookie.size-1) {
                    if (dp[l][m] < dp[m+1][r]) l--
                    else if (dp[l][m] > dp[m+1][r]) r++
                    else {
                        answer = max(dp[l][m], answer)
                        l--
                    }
                }
            }

            return answer
        }
    }
}