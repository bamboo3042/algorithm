package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2225 {
    var N = 0
    var K = 0
    lateinit var dp: Array<IntArray>

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (N, K) = readLine().split(" ").map { it.toInt() }
        dp = Array(K+1) { IntArray(N+1) {0} }
        dp[0][0] = 1
        for (k in 1 .. K) {
            for (n in 1 .. N) {
                for (i in 0 .. n) {
                    dp[k][n] = (dp[k][n] + dp[k-1][n-i]) % 1000000000
                }
            }
        }
        var answer = 0
        dp.forEach { answer = (answer + it.last()) % 1000000000 }
        println(answer)
    }
}