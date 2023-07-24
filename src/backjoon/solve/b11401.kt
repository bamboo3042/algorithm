package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b11401 {
    val M = 1000000007

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (N, K) = readLine().split(" ").map { it.toInt() }
        if (N == K || K == 0) println(1)
        else {
            val dp = LongArray(N + 1) { it.toLong() }
            for (i in 3 until N + 1) dp[i] = (dp[i] * dp[i-1]) % M
            println((power((dp[K] * dp[N - K]) % M, M - 2L) * dp[N]) % M)
        }
    }

    fun power(_x: Long, _p: Long): Long {
        var result = 1L
        var x = _x
        var p = _p
        while (p > 0) {
            if (p and 1 == 1.toLong()) result = (result * x) % M

            x = (x * x) % M
            p = p.shr(1)
        }
        return result
    }
}