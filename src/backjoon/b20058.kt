package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow

class b20058 {
    var N = 0
    var Q = 0
    var size = 0
    val dx = arrayOf(0, 0, 1, -1)
    val dy = arrayOf(1, -1, 0, 0)
    lateinit var board: Array<IntArray>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        Q = input[1].toInt()
        size = 2.0.pow(N).toInt()
        board = (0 until size).map {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }.toTypedArray()
        readLine().split(" ").map { s ->
            if (s.toInt() != 0) partition(0, 0, N, s.toInt())
            melt()
            printBoard()
        }

        println(board.sumOf { l -> l.sumOf { it } })
        println(bigIce())
    }

    fun partition(x: Int, y: Int, n: Int, s: Int) {
        val ts = (2.0).pow(n).toInt()
        if (n != s) {
            val nx = (x + x + ts)/2
            val ny = (y + y + ts)/2
            partition(x, y, n-1, s)
            partition(x, ny, n-1, s)
            partition(nx, ny, n-1, s)
            partition(nx, y, n-1, s)
        }
        else rotate(x, y, ts)
    }

    fun rotate(x: Int, y: Int, s: Int) {
//    println("rotate: $x, $y, $s")
        val tdx = arrayOf(0, 0, 1, 1)
        val tdy = arrayOf(0, 1, 1, 0)
        val temp = Array(s) { IntArray(s) {0} }
        val ey = y+s/2-1

//    repeat(4) {
//        val sx = x + (s/2)*tdx[it]
//        val sy = y + (s/2)*tdy[it]
//        val nx = x + (s/2)*tdx[(it+1)%4]
//        val ny = ey + (s/2)*tdy[(it+1)%4]
////        println("tx: $sx, ty: $sy")
//        for (i in 0 until s/2) {
//            for (j in 0 until s/2) {
////                println("bx: ${sx+i}, by: ${sy+j}, nx: ${nx+j}, ny:${ny-i}")
//                temp[nx + j - x][ny - i - y] = board[sx + i][sy + j]
//            }
//        }
//    }

        for (i in 0 until s) {
            for (j in 0 until s) {
                temp[j][s - i -1] = board[x + i][y + j]
            }
        }


        for (i in 0 until s) {
            for (j in 0 until s) {
                board[x + i][y + j] = temp[i][j]
            }
        }
    }

    fun melt() {
        val tempBoard = Array(size) { IntArray(size) {0} }
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (board[i][j] == 0) continue
                var c = 0
                repeat(4) {
                    val nx = i + dx[it]
                    val ny = j + dy[it]
                    if (nx in 0 until size && ny in 0 until size && board[nx][ny] > 0) c++
                }
                tempBoard[i][j] = if (c >= 3) board[i][j]
                else board[i][j] - 1
            }
        }

        board = tempBoard
    }

    fun bigIce(): Int {
        val checkBoard = Array(size) { BooleanArray(size) {true} }
        var result = 0
        val getIceSize = fun(x: Int, y: Int): Int {
            val queue = mutableListOf<Pair<Int, Int>>()
            var sum = 0
            queue.add(x to y)
            checkBoard[x][y] = false

            while (queue.isNotEmpty()) {
                val q = queue.removeFirst()
                sum++
                repeat(4) {
                    val nx = q.first + dx[it]
                    val ny = q.second + dy[it]

                    if (nx in 0 until size && ny in 0 until size && checkBoard[nx][ny] && board[nx][ny] != 0) {
                        queue.add(nx to ny)
                        checkBoard[nx][ny] = false
                    }
                }
            }

            return sum
        }
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (checkBoard[i][j] && board[i][j] != 0) {
                    result = result.coerceAtLeast(getIceSize(i, j))
                }
            }
        }
        return result
    }

    fun printBoard() {
        println("]-----] Board [-----[")
        board.forEach { l ->
            l.forEach { print("$it ") }
            println()
        }
    }
//3 1
//1 2 3 4 5 6 7 8
//8 7 6 5 4 3 2 1
//1 2 3 4 5 6 7 8
//8 7 6 5 4 3 2 1
//1 2 3 4 5 6 7 8
//8 7 6 5 4 3 2 1
//1 2 3 4 5 6 7 8
//8 7 6 5 4 3 2 1
//1
}