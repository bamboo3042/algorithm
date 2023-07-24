package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2447 {
    lateinit var board: Array<CharArray>

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        board = Array(n) { CharArray(n) { ' ' } }
        printStar(0, 0, n)
        board.map { println(it.joinToString("")) }
    }

    fun printStar(x: Int, y: Int, n: Int) {
        if (n ==  3) {
            for (i in 0 until 3) {
                for (j in 0 until 3) {
                    if (i == 1 && j == 1) continue
                    board[x+i][y+j] = '*'
                }
            }
            return
        }
        val m = n / 3
        for(i in 0 until n step m) {
            for(j in 0 until n step m) {
                if (i == m && j ==m) continue
                printStar(x + i, y + j, m)
            }
        }
    }
}