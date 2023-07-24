package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b13398 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val N = readLine().toInt()
        val arr = readLine().split(" ").map { it.toInt() }
        val dp = Array(N) { IntArray(2) {0} }
        var max = arr[0]

        dp[0][0] = arr[0]
        dp[0][1] = arr[0]

        for (i in 1 until N) {
            dp[i][0] = maxOf(dp[i-1][0] + arr[i], arr[i])
            dp[i][1] = maxOf(dp[i-1][0], dp[i-1][1] + arr[i])
            max = maxOf(max, dp[i].max())
        }

        println(max)
    }
}