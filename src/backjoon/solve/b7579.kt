package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b7579 {
    var N = 0
    var M = 0
    lateinit var memory: List<Int>
    lateinit var cost: List<Int>
    lateinit var dp: Array<IntArray>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()
        memory = listOf(0) + readLine().split(" ").map { it.toInt() }
        cost = listOf(0) + readLine().split(" ").map { it.toInt() }
        val sum = cost.sum()
        dp = Array(N + 1) { IntArray(sum + 1) {0} }

        for (i in 1 .. N) {
            for (j in 1 .. sum) {
                if (j - cost[i] >= 0) dp[i][j] = dp[i][j].coerceAtLeast(dp[i-1][j-cost[i]] + memory[i])
                dp[i][j] = dp[i][j].coerceAtLeast(dp[i-1][j])
            }
        }

        println(dp[N].indexOfFirst { it >= M })
    }
}