package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2240 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (T, W) = readLine().split(" ").map { it.toInt() }
        val dp = Array(T) { Array(W+1) { IntArray(2) {0} } }
        if (readLine().toInt() == 1) dp[0][0][1] = 1
        else dp[0][1][0] = 1

        repeat(T-1) { t ->
            val p = readLine().toInt() % 2
            val np = (p+1) % 2

            dp[t+1][0][p] = dp[t][0][p] + 1
            dp[t+1][0][np] = dp[t][0][np]

            repeat(W) {
                dp[t+1][it+1][p] = maxOf(dp[t][it+1][p], dp[t][it][np]) + 1
                dp[t+1][it+1][np] = maxOf(dp[t][it+1][np], dp[t][it][p])
            }
        }

        println(dp.maxOf { it.maxOf { it.max() } })
    }
}