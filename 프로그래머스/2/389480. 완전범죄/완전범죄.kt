class Solution {
    fun solution(info: Array<IntArray>, n: Int, m: Int): Int {
        val totalA = info.sumOf { it[0] }
        val dp = IntArray(m) { -1 }
        dp[0] = 0

        for ((a, b) in info) {
            for (curB in m - 1 downTo 0) {
                if (dp[curB] == -1) continue

                val nextB = curB + b
                if (nextB >= m) continue

                val nextA = dp[curB] + a
                if (nextA > dp[nextB]) dp[nextB] = nextA
            }
        }

        val maxAForB = dp.maxOrNull() ?: 0
        val minTrace = totalA - maxAForB

        return if (minTrace >= n) -1 else minTrace
    }
}