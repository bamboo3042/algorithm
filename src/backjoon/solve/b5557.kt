package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b5557 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val N = readLine().toInt()
        val arr = readLine().split(" ").map { it.toInt() }
        val dp = Array(N-1) { LongArray(21) {0} }
        dp[0][arr[0]] = 1

        for (i in 1 until N-1) {
            for (j in 0 .. 20) {
                val plus = j + arr[i]
                val minus = j - arr[i]
                if (plus in 0 .. 20) dp[i][plus] += dp[i-1][j]
                if (minus in 0 .. 20) dp[i][minus] += dp[i-1][j]
            }
        }

        println(dp.last()[arr.last()])
    }
}