package programmers

class 연속_펄스_부분_수열의_합 {
    class Solution {
        fun solution(sequence: IntArray): Long {
            var answer: Long = 0
            val dp = Array(sequence.size) { LongArray(2) { 0 } }
            dp[0][0] = maxOf(sequence[0].toLong(), 0)
            dp[0][1] = maxOf(-sequence[0].toLong(), 0)
            answer = dp[0].max()

            for (i in 1 until sequence.size) {
                dp[i][0] = maxOf(dp[i - 1][1] + sequence[i], 0)
                dp[i][1] = maxOf(dp[i - 1][0] - sequence[i], 0)

                answer = maxOf(answer, dp[i][0], dp[i][1])
            }
            return answer
        }
    }
}