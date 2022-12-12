package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b20056 {
    val dx = arrayOf(-1, -1, 0, 1, 1, 1, 0, -1)
    val dy = arrayOf(0, 1, 1, 1, 0, -1, -1, -1)
    var N = 0
    var M = 0
    var K = 0
    lateinit var board: Array<Array<MutableList<FireBall>>>
    data class FireBall(
        var m: Int,
        var s: Int,
        var d: Int,
    )


    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()
        K = input[2].toInt()

        board = Array(N) { Array(N) { mutableListOf() } }
        mutableListOf(1).add(1)
        repeat(M) {
            val (r, c, m, s, d) = readLine().split(" ").map { it.toInt() }
            board[r-1][c-1] = mutableListOf(FireBall(m, s, d))
        }

        while (K-- > 0) {
            moveFireball()
            merge()
        }

        println(countM())
    }

    fun moveFireball() {
        val newBoard = Array(N) { Array<MutableList<FireBall>>(N) { mutableListOf() } }
        for (i in 0 until N) {
            for (j in 0 until N) {
                if (board[i][j].isNotEmpty()) {
                    board[i][j].forEach { f ->
                        val nx = (i + (dx[f.d] * f.s) + N * f.s) % N
                        val ny = (j + (dy[f.d] * f.s) + N * f.s) % N
                        newBoard[nx][ny].add(f)
                    }
                }
            }
        }
        board = newBoard
    }

    fun merge() {
        for (i in 0 until N) {
            for (j in 0 until N) {
                if (board[i][j].size > 1) {
                    var nm = 0
                    var ns = 0
                    var ndCheck = true
                    board[i][j].forEach {
                        nm += it.m
                        ns += it.s
                        ndCheck = if (board[i][j].first().d % 2 == it.d % 2) ndCheck else false
                    }
                    nm /= 5
                    if (nm == 0) board[i][j] = mutableListOf()
                    else {
                        ns /= board[i][j].size
                        val nd = if (ndCheck) 0 else 1

                        board[i][j] = (0 until 4).map {
                            FireBall(nm, ns, it * 2 + nd)
                        }.toMutableList()
                    }
                }
            }
        }
    }

    fun countM() = board.sumOf { l -> l.sumOf { fs -> fs.sumOf { it.m } } }
}