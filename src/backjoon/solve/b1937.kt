package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b1937 {
    var N = 0
    lateinit var board: Array<IntArray>
    lateinit var dp: Array<IntArray>
    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        board = Array(N) {readLine().split(" ").map { it.toInt() }.toIntArray()}
        dp = Array(N) { IntArray(N) { Int.MIN_VALUE} }
        for (i in 0 until N) for (j in 0 until N) dfs(i, j)

        println(dp.maxOf { it.max() })
    }

    fun dfs(x: Int, y: Int): Int {
        if (dp[x][y] != Int.MIN_VALUE) return dp[x][y]

        var max = 0
        repeat(4) {
            val nx = x + dx[it]
            val ny = y + dy[it]
            if (nx in 0 until N && ny in 0 until N && board[nx][ny] > board[x][y]) max = maxOf(max, dfs(nx, ny))
        }
        dp[x][y] = max + 1

        return dp[x][y]
    }
}