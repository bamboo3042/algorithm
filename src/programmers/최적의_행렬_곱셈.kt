package programmers

class 최적의_행렬_곱셈 {
    class Solution {
        fun solution(matrix_sizes: Array<IntArray>): Int {
            val N = matrix_sizes.size
            val dp = Array(N+1) { IntArray(N+1) {0} }
            for (size in 0 until N) {
                for (left in 0 until N-size-1) {
                    val right = left + size + 1
                    dp[left][right] = Int.MAX_VALUE
                    for (middle in left until  right) dp[left][right] = minOf(dp[left][right], dp[left][middle] + dp[middle+1][right] + (matrix_sizes[left][0] * matrix_sizes[middle][1] * matrix_sizes[right][1]))
                }
            }
            return dp[0][N-1]
        }
    }
}