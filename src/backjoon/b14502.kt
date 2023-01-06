package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b14502 {
    var N = 0
    var M = 0
    lateinit var board: Array<IntArray>
    val dx = arrayOf(0, 1, 0, -1)
    val dy = arrayOf(-1, 0, 1, 0)
    var answer = 0
    val virus = mutableListOf<Pair<Int, Int>>()
    var wallCount = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()
        board = Array(N) {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }
        board.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, n ->
                when (n) {
                    2 -> virus.add(i to j)
                    1 -> wallCount++
                }
            }
        }

        addWalls(0, 0, 0)

        println(answer)
    }

    fun addWalls(x: Int, y: Int, n: Int) {
        if (n == 3) dfs()
        else {
            for (i in x until N) {
                for (j in 0 until M) {
                    if (x == i && j < y) continue
                    if (board[i][j] == 0) {
                        board[i][j] = 1
                        addWalls(i, j + 1, n + 1)
                        board[i][j] = 0
                    }
                }
            }
        }
    }

    fun dfs() {
        val queue = mutableListOf<Pair<Int, Int>>()
        val tempBoard = Array(N) { board[it].clone() }
        var tempCount = N * M - wallCount - 3 - virus.size
        virus.forEach { queue.add(it) }

        while (queue.isNotEmpty() && tempCount > answer) {
            val q = queue.removeFirst()
            tempBoard[q.first][q.second] = 2
            repeat(4) {
                val nx = q.first + dx[it]
                val ny = q.second + dy[it]
                if (nx in 0 until N && ny in 0 until M && tempBoard[nx][ny] == 0) {
                    tempBoard[nx][ny] = 2
                    tempCount--
                    queue.add(nx to ny)
                }
            }
        }
        answer = answer.coerceAtLeast(tempCount)
    }
}