package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2631 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val N = readLine().toInt()
        val arr = IntArray(N) {readLine().toInt()}
        val dp = IntArray(N) {0}
        var max = 1
        dp[N-1] = 1
        for (i in N-2 downTo 0) {
            var temp = 0
            for (j in N-1 downTo i+1) {
                if (arr[i] < arr[j]) temp = maxOf(temp, dp[j])
            }
            dp[i] = temp + 1
            max = maxOf(max, dp[i])
        }
        println(N-max)
    }
}