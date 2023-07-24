package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b9376 {
    var h = 0
    var w = 0
    lateinit var board: List<List<Char>>
    val dx = arrayOf(1, -1, 0, 0)
    val dy = arrayOf(0, 0, 1, -1)

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        repeat(readLine().toInt()) {
            val input = readLine().split(" ").map { it.toInt() }
            h = input[0]
            w = input[1]
            board = listOf(".".repeat(w+2).toList()) + List(h) { ('.' + readLine() + '.').toList() } + listOf(".".repeat(w+2).toList())
            solve()
        }
    }
    fun solve() {
        val list = mutableListOf(0 to 0)
        val checkBoard = Array(h+2) { IntArray(w+2) {100000} }
        var answer = Int.MAX_VALUE
        board.forEachIndexed { i, chars ->
            chars.forEachIndexed { j, c ->
                if (board[i][j] == '$') list.add(i to j)
            }
        }

        list.forEach { (a, b) ->
            val queue = ArrayDeque<Triple<Int, Int, Int>> ()
            val visited = Array(h+2) { BooleanArray(w+2) {false} }
            queue.add(Triple(a, b, 0))
            visited[a][b] = true

            while (queue.isNotEmpty()){
                val (x, y, d) = queue.removeFirst()

                if (checkBoard[x][y] == 100000) checkBoard[x][y] = d else checkBoard[x][y] += d

                repeat(4) {
                    val nx = x + dx[it]
                    val ny = y + dy[it]

                    if (nx in 0 until h+2 && ny in 0 until w+2 && !visited[nx][ny]) {
                        if (board[nx][ny] == '.') queue.addFirst(Triple(nx, ny, d))
                        else if (board[nx][ny] == '#') queue.addLast(Triple(nx, ny, d+1))
                        else if (board[nx][ny] == '$') queue.add(Triple(nx, ny, d))
                        visited[nx][ny] = true
                    }
                }
            }
        }

        checkBoard.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, n ->
                if (board[i][j] == '*') return@forEachIndexed
                answer = answer.coerceAtMost(if (board[i][j] == '#') n-2 else n)
            }
        }

        println(answer)
    }
}