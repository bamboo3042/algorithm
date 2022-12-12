package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b17825 {
    lateinit var board: IntArray
    lateinit var dice: IntArray
    var answer = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        dice = readLine().split(" ").map { it.toInt() }.toIntArray()
        makeBoard()
        dfs(IntArray(4) { 0 }, 0, 0)

        println(answer)
    }

    fun dfs(pieces: IntArray, n: Int, sum: Int) {
        if (n == 10) answer = answer.coerceAtLeast(sum)
        else {
            repeat(4) {
                val next = moveCheck(pieces, it, dice[n])
                if (next != -1) {
                    val prev = pieces[it]
                    pieces[it] = next
                    dfs(pieces, n+1, sum + board[next])
                    pieces[it] = prev
                }
            }
        }
    }

    fun moveCheck(pieces: IntArray, pieceIndex: Int, moveCount: Int): Int {
        if (pieces[pieceIndex] == 21) return -1

        var isStart = true
        var move = pieces[pieceIndex]
        var cnt = moveCount

        while (cnt-- > 0 && move != 21) {
            if (isStart) {
                move = when (move) {
                    5 -> 22
                    10 -> 25
                    15 -> 27
                    24 -> 30
                    26 -> 30
                    32 -> 20
                    else -> move + 1
                }
                isStart = false
            }
            else {
                move = when (move) {
                    24 -> 30
                    26 -> 30
                    32 -> 20
                    else -> move + 1
                }
            }
        }

        return if (move != 21 && move in pieces) -1
        else move
    }

    fun makeBoard() {
        var cnt = 1
        board = IntArray(33) {
            if (it <= 20) it * 2
            else if (it == 21) 0
            else if (it < 25) 10 + (3 * cnt++)
            else if (it < 26) 22
            else if (it < 27) 24
            else if (it < 28) 28
            else if (it < 29) 27
            else if (it < 30) 26
            else if (it < 31) 25
            else if (it < 32) 30
            else if (it < 33) 35
            else 0
        }
    }
}