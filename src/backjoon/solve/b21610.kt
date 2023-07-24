package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b21610 {
    var N = 0
    var M = 0
    lateinit var board: Array<IntArray>
    lateinit var cloud: List<Pair<Int, Int>>
    val dx = arrayOf(0, -1, -1, -1, 0, 1, 1, 1)
    val dy = arrayOf(-1, -1, 0, 1, 1, 1, 0, -1)

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()
        board = Array(N) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        cloud = listOf(N-1 to 0, N-2 to 0, N-1 to 1, N-2 to 1)
        repeat(M) {
            val (d, s) = readLine().split(" ").map { it.toInt() }
            cloudMoveAndRain(d-1, s)
            copyWater()
            createCloud()
        }

        println(board.sumOf { l -> l.sumOf { it } })
    }

    fun cloudMoveAndRain(d: Int, s: Int) {
        cloud = cloud.map { (x, y) ->
            val nx = (x + (dx[d] * s) + s * N) % N
            val ny = (y + (dy[d] * s) + s * N) % N
            nx to ny
        }
        cloud.forEach { (x, y) -> board[x][y]++ }
    }
    fun copyWater() {
        val tempBoard = Array(N) { board[it].clone() }
        cloud.forEach { (x, y) ->
            var count = 0
            repeat(4) {
                val nx = x + dx[it*2+1]
                val ny = y + dy[it*2+1]
                if (nx in 0 until N && ny in 0 until N && board[nx][ny] > 0) count++
            }
            tempBoard[x][y] += count
        }
        board = tempBoard
    }

    fun createCloud() {
        val nextCloud = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until N) {
            for (j in 0 until N) {
                if (board[i][j] >= 2 && (i to j) !in cloud) {
                    nextCloud.add(i to j)
                    board[i][j] -= 2
                }
            }
        }
        cloud = nextCloud
    }

    fun printBoard() {
        println("]-----] Board [-----[")

        board.forEach { l ->
            l.forEach { print("${String.format("%3d", it)} ") }
            println()
        }
    }
}