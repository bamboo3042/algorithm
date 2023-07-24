package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2293 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val coin = IntArray(n) { readLine().toInt() }
        val dp = IntArray(k+1) {0}
        dp[0] = 1
        coin.forEach { c -> for (i in c .. k) dp[i] += dp[i - c] }
        println(dp[k])
    }
}