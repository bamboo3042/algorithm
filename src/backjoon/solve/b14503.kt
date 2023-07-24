package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b14503 {
    var N = 0
    var M = 0
    lateinit var board: Array<IntArray>
    val dx = arrayOf(-1, 0, 1, 0)
    val dy = arrayOf(0, -1, 0, 1)
    var answer = 0
    var r = 0
    var c = 0
    var d = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        var input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()
        input = readLine().split(" ")
        r = input[0].toInt()
        c = input[1].toInt()
        d = when(val n = input[2].toInt()) {
            1 -> 3
            3 -> 1
            else -> n
        }
        board = Array(N) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        board[r][c] = 2
        answer++

        while (true) {
            for (i in 1 .. 4) {
                val nx = r + dx[(d+i)%4]
                val ny = c + dy[(d+i)%4]
                if (nx in 0 until N && ny in 0 until M && board[nx][ny] == 0) {
                    r = nx
                    c = ny
                    d = (d+i)%4
                    break
                }
            }

            if (board[r][c] == 0) {
                board[r][c] = 2
                answer++
            }
            else {
                val nx = r + dx[(d+2)%4]
                val ny = c + dy[(d+2)%4]
                if (nx in 0 until N && ny in 0 until M && board[nx][ny] != 1) {
                    r = nx
                    c = ny
                }
                else break
            }
        }

        println(answer)
    }
}