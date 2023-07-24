package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b15683 {
    val dxs = arrayOf(1, 0, -1, 0)
    val dys = arrayOf(0, 1, 0, -1)
    var n = 0
    var m = 0
    var answer = Int.MAX_VALUE
    lateinit var board: Array<IntArray>
    val cameras: MutableList<Pair<Int, Int>> = mutableListOf()

    data class Check(
        val ci: Int,
        val di: Int,
    )

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        n = input[0].toInt()
        m = input[1].toInt()
        board = Array(n) {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (board[i][j] != 0 && board[i][j] != 6) cameras.add(i to j)
            }
        }

        dfs(0, listOf())

        println(answer)
    }

    fun dfs(ci: Int, check: List<Check>) {
        if(ci == cameras.size) {
            val tempBoard = board.map { it.copyOf() }.toTypedArray()
            check.map {
                var i = cameras[it.ci].first
                var j = cameras[it.ci].second
                val dx = dxs[it.di]
                val dy = dys[it.di]
                while(i+dx in 0 until n && j+dy in 0 until m && tempBoard[i+dx][j+dy] != 6) {
                    i += dx
                    j += dy
                    tempBoard[i][j] = -1
                }
            }

            var count = 0
            tempBoard.map { count += it.count { n -> n == 0 } }
            answer = answer.coerceAtMost(count)
        }
        else {
            when(board[cameras[ci].first][cameras[ci].second]) {
                1 -> repeat(4) { dfs(ci + 1, check + Check(ci, it)) }
                2 -> repeat(2) { dfs(ci + 1, check + Check(ci, it) + Check(ci, it + 2)) }
                3 -> repeat(4) { dfs(ci +1, check + Check(ci, it) + Check(ci, (it + 1)%4 )) }
                4 -> repeat(4) { dfs(ci + 1, check + Check(ci, it) + Check(ci, + (it + 1)%4) + Check(ci, (it + 2)%4)) }
                5 -> dfs(ci + 1, check + Check(ci, 0) + Check(ci, 1) + Check(ci, 2) + Check(ci, 3))
            }
        }
    }
}