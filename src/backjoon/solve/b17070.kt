package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b17070 {
    lateinit var board: Array<IntArray>
    lateinit var dp: Array<Array<IntArray>>

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val N = readLine().toInt()
        board = Array(N) { readLine().split(" ").map  { it.toInt() }.toIntArray() }
        dp = Array(N) { Array(N) { IntArray(3) {0} } }
        dp[0][1][0] = 1

        for (i in 0 until N) {
            for (j in 0 until N) {
                if (j+1 < N && board[i][j+1] != 1) dp[i][j+1][0] += dp[i][j][0] + dp[i][j][1]
                if (i+1 < N && j+1 < N && board[i+1][j] != 1 && board[i+1][j+1] != 1 && board[i][j+1] != 1) dp[i+1][j+1][1] += dp[i][j].sum()
                if (i+1 < N && board[i+1][j] != 1) dp[i+1][j][2] += dp[i][j][1] + dp[i][j][2]
            }
        }
        println(dp[N-1][N-1].sum())
    }
}