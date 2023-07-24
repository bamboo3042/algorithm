package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b1915 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (N, M) = readLine().split(" ").map { it.toInt() }
        val board = Array(N) { readLine().map { it == '1' }.toBooleanArray() }
        var max = 0
        val dp = Array(N) { i ->
            IntArray(M) { j ->
                if (board[i][j]) {
                    max = 1
                    1
                }
                else 0
            }
        }
        for (size in 2 until N*M) {
            val prevSize = size-1
            var check = false
            for (i in 0 .. N-size) {
                for (j in 0 .. M-size) {
                    if (dp[i][j] >= prevSize && dp[i][j+1] >= prevSize && dp[i+1][j] >= prevSize && dp[i+1][j+1] >= prevSize) {
                        dp[i][j] = size
                        check = true
                    }
                }
            }
            if (!check) break
            max = size * size
        }


        println(max)
    }
}