package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b11049 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val N = readLine().toInt()
        val matrix = arrayOf( listOf(0) ) + Array(N) { readLine().split(" ").map { it.toInt() } }
        val dp = Array(N+1) { IntArray(N+1) {0} }
        for (size in 1 .. N) {
            for (left in 1 .. N-size) {
                val right = left + size
                dp[left][right] = Int.MAX_VALUE
                for (middle in left until  right) dp[left][right] = minOf(dp[left][right], dp[left][middle] + dp[middle+1][right] + (matrix[left][0] * matrix[middle][1] * matrix[right][1]))
            }
        }

        println(dp[1][N])
    }
}