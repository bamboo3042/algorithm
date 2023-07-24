package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b17143 {
    var R = 0
    var C = 0
    var M = 0
    lateinit var board: Array<Array<Shark?>>
    var answer = 0
    val dx = arrayOf(-1, 0, 1, 0)
    val dy = arrayOf(0, 1, 0, -1)

    data class Shark (
        val s: Int,
        var d: Int,
        val z: Int
    ) {
        override fun toString(): String {
            return "[$s, $d, $z]"
        }
    }

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        R = input[0].toInt()
        C = input[1].toInt()
        M = input[2].toInt()
        board = Array(R) { Array(C) { null } }
        repeat(M) {
            val temp = readLine().split(" ").map { it.toInt() }
            val d = when(temp[3]) {
                1 -> 0
                2 -> 2
                3 -> 1
                4 -> 3
                else -> 0
            }
            board[temp[0]-1][temp[1]-1] = Shark(temp[2], d, temp[4])
        }

        for (n in 0 until C) {
            catch(n)
            sharkMove()
        }

        println(answer)
    }

    fun catch(h: Int) {
        for (i in 0 until R) {
            if (board[i][h] != null) {
                answer += board[i][h]!!.z
                board[i][h] = null
                break
            }
        }
    }

    fun sharkMove() {
        val tempBoard = Array(R) { Array<Shark?>(C) { null } }
        for (i in 0 until R) {
            for (j in 0 until C) {
                if (board[i][j] != null) {
                    var t = 0
                    val shark = board[i][j]!!
                    var nx = i
                    var ny = j
                    while (t < shark.s) {
                        nx += dx[shark.d]
                        ny += dy[shark.d]
                        if (nx in 0 until R && ny in 0 until C) t++
                        else {
                            nx -= dx[shark.d]
                            ny -= dy[shark.d]
                            shark.d = (shark.d + 2) % 4
                        }
                    }
                    if (tempBoard[nx][ny] == null) tempBoard[nx][ny] = shark
                    else if (tempBoard[nx][ny]!!.z < shark.z) tempBoard[nx][ny] = shark
                }
            }
        }
        board = tempBoard
    }
}