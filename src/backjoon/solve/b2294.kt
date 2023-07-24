package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

class b2294 {
    lateinit var dp: IntArray
    lateinit var coin: IntArray

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (N, K) = readLine().split(" ").map { it.toInt() }
        coin = List(N) { readLine().toInt() }.toHashSet().toIntArray()
        dp = IntArray(110001) {100001}
        dp[0] = 0
        repeat(K+1) { k ->
            coin.forEach { c -> if (k >= c) dp[k] = min(dp[k], dp[k-c]+1) }
        }
        println(if (dp[K] == 100001) -1 else dp[K])
    }
}