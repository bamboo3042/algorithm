package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2533 {
    var N = 0
    lateinit var friend: List<MutableList<Int>>
    lateinit var visited: BooleanArray
    lateinit var dp: Array<IntArray>

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        friend = List(N) { mutableListOf() }
        visited = BooleanArray(N) {false}
        dp = Array(N) { IntArray(2) {0} }
        repeat(N-1) {
            val (u, v) = readLine().split(" ").map { it.toInt() - 1}
            friend[u].add(v)
            friend[v].add(u)
        }

        dfs(0)

        println(dp[0].min())
    }

    fun dfs(n: Int) {
        visited[n] = true
        dp[n][1] = 1

        for (c in friend[n]) {
            if (visited[c]) continue

            dfs(c)

            dp[n][0] += dp[c][1]
            dp[n][1] += dp[c].min()
        }
    }
}