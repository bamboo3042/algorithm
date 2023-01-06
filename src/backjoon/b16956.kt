package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b16956 {
    var N = 0
    var M = 0
    lateinit var board: Array<CharArray>
    val dx = arrayOf(0, 0, 1, -1)
    val dy = arrayOf(1, -1, 0, 0)

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ").map { it.toInt() }
        N = input[0]
        M = input[1]
        board = Array(N) { readLine().toCharArray() }
        if (solve()) {
            println(1)
            board.forEach { println(it) }
        }
        else println(0)
    }

    fun solve(): Boolean {
        for (i in 0 until N) {
            for (j in 0 until M) {
                if (board[i][j] == 'W') {
                    repeat(4) {
                        val nx = i + dx[it]
                        val ny = j + dy[it]
                        if (nx in 0 until N && ny in 0 until M) {
                            if(board[nx][ny] == 'S') return false
                            else if (board[nx][ny] == '.') board[nx][ny] = 'D'
                        }
                    }
                }
            }
        }
        return true
    }
}