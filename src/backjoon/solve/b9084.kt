package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b9084 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val sb = StringBuilder()
        repeat(readLine().toInt()) {
            val N = readLine().toInt()
            val coins = readLine().split(" ").map { it.toInt() }
            val M = readLine().toInt()
            val dp = IntArray(M+1) {0}
            dp[0] = 1
            for (c in coins) {
                for (i in c .. M) dp[i] += dp[i-c]
            }
            sb.appendLine(dp[M])
        }
        println(sb)
    }
}