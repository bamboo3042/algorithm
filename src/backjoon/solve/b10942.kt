package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b10942 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val N = readLine().toInt()
        val dp = Array(N + 1) { BooleanArray(N + 1) { false } }
        val arr = listOf(0) + readLine().split(" ").map { it.toInt() }
        for (i in 0..N) dp[i][i] = true
        for (i in 1 until N) dp[i][i + 1] = arr[i] == arr[i + 1]
        for (i in 2 until N) {
            for (j in 1..N - i) {
                dp[j][j + i] = (arr[j] == arr[j + i] && dp[j + 1][j + i - 1])
            }
        }
        val sb = StringBuilder()
        repeat(readLine().toInt()) {
            val (s, e) = readLine().split(" ").map { it.toInt() }
            sb.appendLine(if (dp[s][e]) 1 else 0)
        }
        println(sb)
    }
}