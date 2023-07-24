package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b1562 {
    var N = 0
    var answer = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        val dp = Array(N+1) { Array(10) { IntArray(1.shl(10)) {0} } }
        for (i in 1 .. 9) dp[1][i][1.shl(i)] = 1

        repeat(N) { n ->
            for (bit in 0 until 1024) {
                dp[n+1][8][1.shl(8) or bit] = (dp[n+1][8][1.shl(8) or bit]+dp[n][9][bit])%1000000000
                dp[n+1][1][1.shl(1) or bit] = (dp[n+1][1][1.shl(1) or bit]+dp[n][0][bit])%1000000000

                for (i in 1 .. 8) {
                    dp[n+1][i+1][1.shl(i+1) or bit] = (dp[n+1][i+1][1.shl(i+1) or bit]+dp[n][i][bit])%1000000000
                    dp[n+1][i-1][1.shl(i-1) or bit] = (dp[n+1][i-1][1.shl(i-1) or bit]+dp[n][i][bit])%1000000000
                }
            }
        }

        var answer = 0
        for (i in 0 .. 9) answer = (answer+dp[N][i][1023])%1000000000
        println(answer)
    }
}