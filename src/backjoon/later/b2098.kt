package backjoon.later

import java.io.BufferedReader
import java.io.InputStreamReader

class b2098 {
    var N = 0
    lateinit var board: Array<IntArray>
    var answer = Int.MAX_VALUE
    lateinit var dp: Array<IntArray>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        board = Array(N) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        dp = Array(1.shl(N)+1) { IntArray(N) { Int.MAX_VALUE} }
        dfs(0, 1, 0)
        println(answer)

    }

    fun dfs(index: Int, visited: Int, d: Int) {
        if (visited == 1.shl(N)-1 && board[index][0] != 0) { answer = answer.coerceAtMost(d + board[index][0]) }
        else {
            for (i in 0 until N) {
                if (1.shl(i) and visited == 0 && board[index][i] != 0 && (dp[visited][i] == Int.MAX_VALUE || dp[visited][i] > d + board[index][i])){
                    dp[visited][i] = d + board[index][i]
                    dfs(i, visited + 1.shl(i), d + board[index][i])
                }
            }
        }
    }
}