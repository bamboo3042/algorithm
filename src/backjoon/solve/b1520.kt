package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b1520 {
    var M = 0
    var N = 0
    val dx = arrayOf(1, -1, 0, 0)
    val dy = arrayOf(0, 0, 1, -1)
    lateinit var board: Array<IntArray>
    lateinit var dp: Array<IntArray>

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (m, n) = readLine().split(" ").map { it.toInt() }
        M = m
        N = n
        board = Array(M) {readLine().split(" ").map { it.toInt() }.toIntArray()}
        dp = Array(M) { IntArray(N) {-1} }
        println(dfs(0, 0))
    }

    fun dfs(m: Int, n: Int): Int {
        if (m == M-1 && n == N-1) return 1
        if (dp[m][n] != -1) return dp[m][n]

        dp[m][n] = 0
        repeat(4) {
            val nm = m + dy[it]
            val nn = n + dx[it]
            if (nm in 0 until M && nn in 0 until N && board[m][n] > board[nm][nn]) {
                dp[m][n] += dfs(nm, nn)
            }
        }

        return dp[m][n]
    }
}