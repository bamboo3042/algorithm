package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2056 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val N = readLine().toInt()
        val work = Array(N) { readLine().split(" ").map { it.toInt() } }
        val dp = IntArray(N) {0}
        work.forEachIndexed { index, list ->
            dp[index] = if (list[1] == 0) list[0]
            else {
                var max = 0
                repeat(list[1]) { max = maxOf(max, dp[list[2+it]-1]) }
                max+list[0]
            }
        }

        println(dp.max())
    }
}