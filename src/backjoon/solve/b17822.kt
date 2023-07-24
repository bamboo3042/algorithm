package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b17822 {
    lateinit var board: Array<IntArray>
    var N = 0
    var M = 0
    var T = 0
    var dx = arrayOf(-1, 1)

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()
        T = input[2].toInt()

        board = Array(N) {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }

        repeat(T) {
            val (x, d, k) = readLine().split(" ").map { it.toInt() }
            for (i in 0 until N) if ((i + 1) % x == 0) rotate(i, d, k)

            if (!adjacentTest()) averageCheck()
        }
        println(board.sumOf { l -> l.sumOf { it } })
    }

    fun rotate(n: Int, d: Int, k: Int) {
        val tempArray = IntArray(M) { board[n][(it + dx[d]*k + M) % M] }
        board[n] = tempArray
    }

    fun adjacentTest(): Boolean {
        var b = false
        val tempBoard = Array(N) { n ->
            val tempArray = IntArray(M) { m ->
                if (board[n][m] == board[n][(m + 1) % M]) 0
                else if (board[n][m] == board[n][(m + M - 1) % M]) 0
                else if (n < N - 1 && board[n][m] == board[n+1][m]) 0
                else if (n > 0 && board[n][m] == board[n-1][m]) 0
                else board[n][m]
            }
            b = b || !(tempArray.contentEquals(board[n]))
            tempArray
        }
        board = tempBoard
        return b
    }

    fun averageCheck() {
        var sum = 0
        var cnt = 0

        board.forEach { l -> l.forEach {
            if (it != 0) {
                sum += it
                cnt ++
            }
        }}

        val avg = sum.toDouble() / cnt.toDouble()

        board.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, n ->
                if (n != 0) {
                    if (n > avg) board[i][j]--
                    else if (n < avg) board[i][j]++
                }
            }
        }
    }

    fun printBoard(t: Int) {
        println("]-----] t: $t [-----[")
        repeat(N) { i ->
            repeat(N) { j ->
                print("${board[i][j]} ")
            }
            println()
        }
    }
}