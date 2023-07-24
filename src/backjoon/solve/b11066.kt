package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b11066 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val sb = StringBuilder()
        repeat(readLine().toInt()) {
            val K = readLine().toInt()
            val arr = listOf(0) + readLine().split(" ").map { it.toInt() }
            val dp = Array(K+1) { IntArray(K+1) {0} }
            val sum = IntArray(K+1) { 0 }
            for (i in 1 .. K) sum[i] = sum[i-1] + arr[i]
            for (size in 1 .. K) {
                for (left in 1 .. K - size) {
                    val right = left + size
                    dp[left][right] = Int.MAX_VALUE
                    for (middle in left until right) {
                        dp[left][right] = minOf(dp[left][right], dp[left][middle] + dp[middle+1][right] + sum[right] - sum[left-1])
                    }
                }
            }

            sb.appendLine(dp[1][K])
        }

        println(sb)
    }
}