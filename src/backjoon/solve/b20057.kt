package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b20057 {
    val dx = arrayOf(0, 1, 0, -1)
    val dy = arrayOf(-1, 0, 1, 0)

    val dust = arrayOf(
        arrayOf(
            arrayOf(0, 0, 2, 0, 0),
            arrayOf(0, 10, 7, 1, 0),
            arrayOf(5, 0, 0, 0, 0),
            arrayOf(0, 10, 7, 1, 0),
            arrayOf(0, 0, 2, 0, 0),
        ),
        arrayOf(
            arrayOf(0, 0, 0, 0, 0),
            arrayOf(0, 1, 0, 1, 0),
            arrayOf(2, 7, 0, 7, 2),
            arrayOf(0, 10, 0, 10, 0),
            arrayOf(0, 0, 5, 0, 0),
        ),
        arrayOf(
            arrayOf(0, 0, 2, 0, 0),
            arrayOf(0, 1, 7, 10, 0),
            arrayOf(0, 0, 0, 0, 5),
            arrayOf(0, 1, 7, 10, 0),
            arrayOf(0, 0, 2, 0, 0),
        ),
        arrayOf(
            arrayOf(0, 0, 5, 0, 0),
            arrayOf(0, 10, 0, 10, 0),
            arrayOf(2, 7, 0, 7, 2),
            arrayOf(0, 1, 0, 1, 0),
            arrayOf(0, 0, 0, 0, 0),
        ),
    )

    var answer = 0
    var N = 0
    lateinit var board: Array<IntArray>
    var x = 0
    var y = 0
    var d = 0
    var turn = 1

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        x = N / 2
        y = N / 2
        board = (0 until N).map {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }.toTypedArray()

        while (x != 0 || y != -1) {
            move()
        }

        println(answer)
    }

    fun move() {
        var t = 0
        while (t++ < turn) {
            x += dx[d]
            y += dy[d]
            if (x == 0 && y == -1) break

            var sum = 0
            for (i in 0 until 5) {
                for (j in 0 until 5) {
                    var td = dust[d][i][j]
                    if (td == 0) continue
                    val tx = x + i - 2
                    val ty = y + j - 2
                    td = board[x][y] * td / 100
                    if (tx in 0 until N && ty in 0 until N) board[tx][ty] += td
                    else answer += td
                    sum += td
                }
            }
            if (x + dx[d] in 0 until N && y + dy[d] in 0 until N) board[x+dx[d]][y+dy[d]] += board[x][y] - sum
            else answer += board[x][y] - sum
            board[x][y] = 0
        }

        if (d % 2 == 1) turn++
        d = (d + 1) % 4
    }
}