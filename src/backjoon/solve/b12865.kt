package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b12865 {
    var N = 0
    var K = 0
    lateinit var arr: Array<Pair<Int, Int>>
    lateinit var dp: IntArray

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ").map { it.toInt() }
        N = input[0]
        K = input[1]

        arr = Array(N) {
            val (w, v) = readLine().split(" ").map { it.toInt() }
            w to v
        }

        dp = IntArray(K+1) {0}

        for (i in 0 until N) {
            for (j in K downTo  1) {
                if (arr[i].first <= j) {
                    dp[j] = dp[j].coerceAtLeast(dp[j - arr[i].first] + arr[i].second)
                }
            }
        }

        println(dp[K])
    }
}