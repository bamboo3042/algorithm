package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b17144 {
    var R = 0
    var C = 0
    var T = 0
    val dx = arrayOf(0, 1, 0, -1)
    val dy = arrayOf(1, 0, -1, 0)
    lateinit var top: Pair<Int, Int>
    lateinit var bottom: Pair<Int, Int>
    lateinit var board: Array<IntArray>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        R = input[0].toInt()
        C = input[1].toInt()
        T = input[2].toInt()
        board = Array(R) {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }
        for (i in 2 until  R) {
            if (board[i][0] == -1) {
                top = i to 0
                bottom = i + 1 to 0
                break
            }
        }

        while (T-- > 0) {
            spread()
            cycle()
        }

        println(board.sumOf { line -> line.sumOf { it } } + 2)
    }

    fun spread() {
        val tempBoard = Array(R) { IntArray(C) { 0 } }
        for (i in 0 until R) {
            for (j in 0 until C) {
                if(board[i][j] == -1) tempBoard[i][j] = -1
                else if(board[i][j] == 0) continue
                else {
                    var n = board[i][j] / 5
                    tempBoard[i][j] += board[i][j]
                    for (k in 0 until 4) {
                        val nx = i + dx[k]
                        val ny = j + dy[k]
                        if (nx in 0 until R && ny in 0 until C && board[nx][ny] != -1) {
                            tempBoard[nx][ny] += n
                            tempBoard[i][j] -= n
                        }
                    }
                }
            }
        }

        board = tempBoard
    }

    fun cycle() {
        var t = 0
        var i = bottom.first
        var j = bottom.second
        var prev = 0
        var temp = 0
        while (true) {
            val nx = i + dx[t]
            val ny = j + dy[t]
            if (nx in 0 until R && ny in bottom.second until C) {
                if (board[nx][ny] == -1) break
                temp = board[nx][ny]
                board[nx][ny] = prev
                prev = temp
                i = nx
                j = ny
            }
            else t++
        }

        t = 0
        i = top.first
        j = top.second
        prev = 0
        temp = 0
        while (true) {
            val nx = i + dx[t]
            val ny = j + dy[t]
            if (nx in 0 until R && ny in bottom.second until C) {
                if (board[nx][ny] == -1) break
                temp = board[nx][ny]
                board[nx][ny] = prev
                prev = temp
                i = nx
                j = ny
            }
            else t = (t + 3) % 4
        }
    }
}