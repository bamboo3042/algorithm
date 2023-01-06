package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b1194 {
    var N = 0
    var M = 0
    lateinit var board: Array<CharArray>
    val dx = arrayOf(0, 0, 1, -1)
    val dy = arrayOf(1, -1, 0, 0)

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()
        board = Array(N) { readLine().toCharArray() }
        val (x, y) = setStart()
        val answer = solve(Move(x, y, 0, 0))
        println(answer)

    }

    fun setStart(): Pair<Int, Int> {
        for (i in 0 until N) {
            for (j in 0 until M) {
                if (board[i][j] == '0') {
                    board[i][j] = '.'
                    return i to j
                }
            }
        }
        return 0 to 0
    }

    fun solve(move: Move): Int {
        val queue = ArrayDeque<Move> ()
        val visited = Array(N) { Array(M) { BooleanArray(65) { false } } }
        queue.addLast(move)
        visited[move.x][move.y][move.keys] = true

        while (queue.isNotEmpty()) {
            val (x, y, d, keys) = queue.removeFirst()
            repeat(4) {
                val nx = x + dx[it]
                val ny = y + dy[it]
                if (nx in 0 until N && ny in 0 until M && !visited[nx][ny][keys] && board[nx][ny] != '#') {
                    visited[nx][ny][keys] = true
                    if(board[nx][ny] == '1') return d + 1
                    else if (board[nx][ny] == '.') {
                        queue.addLast(Move(nx, ny, d+1, keys))
                    }
                    else if (board[nx][ny] in 'A' .. 'F') {
                        if (1.shl(board[nx][ny] - 'A') and keys != 0){
                            queue.addLast(Move(nx, ny, d+1, keys))
                        }
                    }
                    else {
                        val newKeys = if (1.shl(board[nx][ny] - 'a') and keys == 0) keys + 1.shl(board[nx][ny] - 'a') else keys
                        visited[nx][ny][newKeys] = true
                        queue.addLast(Move(nx, ny, d+1, newKeys))
                    }
                }
            }
        }
        return -1
    }

    data class Move(
        val x: Int,
        val y: Int,
        val d: Int,
        val keys: Int,
    )
}