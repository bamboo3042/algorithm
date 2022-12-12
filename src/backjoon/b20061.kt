package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b20061 {
    var answer = 0
    val board = Array(2) { Array(6) { BooleanArray(4) { false } } }

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        repeat(readLine().toInt()) {
            val (t, x, y) = readLine().split(" ").map { it.toInt() }
            move(0, t, y)
            move(1, if (t == 2) 3 else if (t == 3) 2 else 1, x)
            checkBoard(0)
            checkBoard(1)
        }

        println(answer)
        println(board.sumOf { b -> b.sumOf { l -> l.count { it } } })
    }

    fun move(boardIndex: Int, t: Int, y: Int) {
        var tempX = 5
        for (i in 1 until 6) {
            if (blockCheck(boardIndex, t, i, y)) {
                tempX = i - 1
                break
            }
        }
        board[boardIndex][tempX][y] = true
        if (t == 2) board[boardIndex][tempX][y+1] = true
        else if (t == 3) board[boardIndex][tempX+1][y] = true
    }

    fun blockCheck(boardIndex: Int, t: Int, bx: Int, by: Int) = when(t) {
        1 -> board[boardIndex][bx][by]
        2 -> board[boardIndex][bx][by] || board[boardIndex][bx][by+1]
        3 -> bx+1 > 5 || board[boardIndex][bx][by] || board[boardIndex][bx+1][by]
        else -> false
    }

    fun checkBoard(boardIndex: Int) {
        for (i in 0 until 6) {
            if (false !in board[boardIndex][i]) {
                board[boardIndex][i] = BooleanArray(4) { false }
                answer++
            }
        }
        boardSort(boardIndex)

        val x = if (true in board[boardIndex][0]) 2
        else if (true in board[boardIndex][1]) 1
        else 0
        for (i in 0 until x) board[boardIndex][5-i] = BooleanArray(4) { false }
        boardSort(boardIndex)
    }

    fun boardSort(boardIndex: Int) {
        for (i in 5 downTo 0) {
            if (true !in board[boardIndex][i]) {
                for (j in i - 1 downTo 0) {
                    if (true in board[boardIndex][j]) {
                        board[boardIndex][i] = board[boardIndex][j]
                        board[boardIndex][j] = BooleanArray(4) { false }
                        break
                    }
                }
            }
        }
    }
    fun printBoard(boardIndex: Int) {
        println("---${if (boardIndex == 0) "GreenBoard" else "BlueBoard"}---")
        board[boardIndex].map { l ->
            l.map { print("${if (it) 1 else 0}") }
            println()
        }
        println()
    }
}