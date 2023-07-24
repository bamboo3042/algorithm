package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b1256 {
    lateinit var dp: Array<IntArray>

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (N, M, K) = readLine().split(" ").map { it.toInt() }
        dp = Array(N+1) { IntArray(M+1) {0} }

        for (n in 0 .. N) dp[n][0] = 1
        for (m in 0 .. M) dp[0][m] = 1

        for (n in 1 .. N) {
            for (m in 1 .. M) {
                dp[n][m] = minOf(1000000000, dp[n-1][m] + dp[n][m-1])
            }
        }

        if (K > dp[N][M]) println(-1)
        else {
            var n = N-1
            var m = M
            var k = K
            val sb = StringBuilder()
            while (k > 0) {
                if (n < 0) {
                    repeat(m) { sb.append('z') }
                    break
                }
                else if (m < 0) {
                    repeat(n) { sb.append('a') }
                    break
                }

                if (k > dp[n][m]) {
                    sb.append('z')
                    k -= dp[n][m]
                    m--
                }
                else {
                    sb.append('a')
                    n--
                }
            }
            println(sb)
        }
    }
}