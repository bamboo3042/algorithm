package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b6087 {
    var H = 0
    var W = 0
    lateinit var board: Array<CharArray>
    val dx = arrayOf(1, 0, -1, 0)
    val dy = arrayOf(0, 1, 0, -1)

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        W = input[0].toInt()
        H = input[1].toInt()
        board = Array(H) { readLine().toCharArray() }
        solve()
    }

    fun solve() {
        val queue = ArrayDeque<Move> ()
        val laser = mutableListOf<Pair<Int, Int>>()
        val visited = Array(H) { Array(W) { BooleanArray(4) {false} } }
        var answer = -1

        board.forEachIndexed { i, chars -> chars.forEachIndexed { j, c -> if (board[i][j] == 'C') laser.add(i to j) } }

        repeat(4) { queue.add(Move(laser[0].first, laser[0].second, it, 0)) }

        while (queue.isNotEmpty()) {
            val m = queue.removeFirst()

            if (visited[m.x][m.y][m.d]) continue
            visited[m.x][m.y][m.d] = true

            if (laser[1] == m.x to m.y) {
                answer = m.c
                break
            }

            listOf(m.d, (m.d+1)%4, (m.d+3)%4).forEachIndexed { index, it ->
                val nx = m.x + dx[it]
                val ny = m.y + dy[it]

                if (nx in 0 until H && ny in 0 until W && board[nx][ny] != '*' && !visited[nx][ny][it]) {
                    if (index == 0) queue.addFirst(Move(nx, ny, it, m.c))
                    else queue.addLast(Move(nx, ny, it, m.c+1))
                }
            }
        }

        println(answer)
    }

    data class Move(
        val x: Int,
        val y: Int,
        val d: Int,
        val c: Int
    )
}