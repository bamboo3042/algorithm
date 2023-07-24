package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b17404 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val N = readLine().toInt()
        val home = Array(N) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        var answer = Int.MAX_VALUE
        val dp = Array(N) { IntArray(3) { 0 } }

        for (s in 0 .. 2) {

            for (i in 0 .. 2) dp[0][i] = if (i == s) home[0][i] else 10000000
            for (i in 1 until N) {
                dp[i][0] = minOf(dp[i-1][1], dp[i-1][2]) + home[i][0]
                dp[i][1] = minOf(dp[i-1][0], dp[i-1][2]) + home[i][1]
                dp[i][2] = minOf(dp[i-1][0], dp[i-1][1]) + home[i][2]
            }
            for (i in 0 .. 2) if (i != s) answer = minOf(answer, dp.last()[i])
        }

        println(answer)
    }
}