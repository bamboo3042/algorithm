package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow

class b10993 {
    lateinit var board: Array<CharArray>

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val x = (2.0.pow(n+1)-3).toInt()
        val y = (2.0.pow(n)-1).toInt()
        board = Array(y) { CharArray(x) {' '} }
        printStart(n, 0, x-1, 0, y-1)
        val sb = StringBuilder()
        if (n % 2 == 0) {
            board.mapIndexed { cy, chars ->
                val m = x - cy
                for (i in 0 until m) {
                    sb.append(chars[i])
                }
                sb.appendLine()
            }
        }
        else {
            board.mapIndexed { cy, chars ->
                val m = x/2 + cy + 1
                for (i in 0 until m) {
                    sb.append(chars[i])
                }
                sb.appendLine()
            }
        }
        println(sb)
    }

    fun printStart(n: Int, x1: Int, x2: Int, y1: Int, y2: Int) {
        val x = (x1 + x2) / 2
        val y = (y1 + y2) / 2
        if (n == 1) {
            board[y][x] = '*'
            return
        }
        if (n % 2 == 1) {
            (y1 until y2).forEachIndexed { index, i ->
                board[i][x+index] = '*'
                board[i][x-index] = '*'
            }
            (x1 .. x2).forEach { board[y2][it] = '*' }
            printStart(n-1, (x1+x)/2+1, (x2+x)/2-1, y, y2)
        }
        else {
            (x1 .. x2).forEach { board[y1][it] = '*' }
            (y1+1 .. y2).forEachIndexed { index, i ->
                board[i][x1+index+1] = '*'
                board[i][x2-index-1] = '*'
            }
            printStart(n-1, (x1+x)/2+1, (x2+x)/2-1, y1+1, y)
        }
    }
}