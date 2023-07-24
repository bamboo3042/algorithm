package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b1516 {
    var N = 0
    lateinit var dp: IntArray
    lateinit var arr: Array<IntArray>

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        dp = IntArray(N) { Int.MAX_VALUE}
        arr = Array(N) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        for (i in 0 until N) dfs(i)

        println(dp.joinToString("\n"))
    }

    fun dfs(n: Int): Int {
        if (dp[n] != Int.MAX_VALUE) return dp[n]

        if (arr[n][1] == -1) {
            dp[n] = arr[n].first()
            return dp[n]
        }

        val time = arr[n].first()
        var max = 0
        for (i in 1 until arr[n].size - 1) max = maxOf(max, dfs(arr[n][i] - 1))

        dp[n] = time + max

        return dp[n]
    }
}