package programmers

import kotlin.math.absoluteValue

class 사라지는_발판 {
    class Solution {

        lateinit var board: Array<IntArray>
        var dx = arrayOf(1, -1, 0, 0)
        var dy = arrayOf(0, 0, 1, -1)

        fun solution(board: Array<IntArray>, aloc: IntArray, bloc: IntArray): Int {
            this.board = board

            return dfs(aloc[0] to aloc[1], bloc[0] to bloc[1], 0).absoluteValue
        }

        fun dfs(move: Pair<Int, Int>, wait: Pair<Int, Int>, n: Int): Int {
            if (board[move.first][move.second] == 0) return -n

            val movable = (0 until 4).mapNotNull {
                val nx = move.first + dx[it]
                val ny = move.second + dy[it]

                if (nx in board.indices && ny in board[0].indices && board[nx][ny] == 1) nx to ny
                else null
            }

            return if (movable.isEmpty()) -n
            else {
                board[move.first][move.second] = 0
                val result = movable.map { (nx, ny) -> -dfs(wait, nx to ny, n + 1) }.sorted()
                board[move.first][move.second] = 1
                return result.firstOrNull { it > 0 } ?: (result.firstOrNull { it < 0 } ?: n)
            }
        }
    }
}