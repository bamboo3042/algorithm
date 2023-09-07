package programmers

class 카운트_다운 {
    class Solution {
        fun solution(target: Int): IntArray {
            val answer: IntArray = intArrayOf(0, 0)
            val dp = Array(311) {0 to 0}
            for (i in 1 .. 20) dp[i] = 1 to 1
            for (i in 21 .. 60) {
                dp[i] = if (i % 2 == 0 && i / 2 <= 20) 1 to 0
                else if (i % 3 == 0) 1 to 0
                else 2 to 1
            }
            dp[50] = 1 to 1
            for (i in 61 .. 310) {
                var first = Int.MAX_VALUE
                var second = Int.MIN_VALUE
                for (j in 1 .. 60) {
                    val k = i - j
                    val f = dp[k].first + dp[j].first
                    val s = dp[k].second + dp[k].second
                    if (f <= first && s > second ) {
                        first = f
                        second = s
                    }
                }
                dp[i] = first to second
            }

            return if (target <= 310) intArrayOf(dp[target].first, dp[target].second)
            else {
                val x = (target - 250) / 60
                val y = (target - 250) % 60

                intArrayOf(dp[250 + y].first + x, dp[250 + y].second)
            }
        }
    }
}