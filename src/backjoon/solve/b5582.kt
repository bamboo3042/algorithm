package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b5582 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val str1 = readLine()
        val str2 = readLine()
        val dp = Array(str1.length + 1) { IntArray(str2.length + 1) {0} }
        var max = 0
        for (i in 1 .. str1.length) {
            for (j in 1 .. str2.length) {
                if (str1[i-1] == str2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1
                    max = maxOf(max, dp[i][j])
                }
            }
        }

        println(max)
    }
}