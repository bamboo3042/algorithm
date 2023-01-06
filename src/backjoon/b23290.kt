package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b23290 {
    var dx = arrayOf(0, -1, -1, -1, 0, 1, 1, 1)
    var dy = arrayOf(-1, -1, 0, 1, 1, 1, 0, -1)
    var sx = 0
    var sy = 0
    val sdx = arrayOf(-1, 0, 1, 0)
    val sdy = arrayOf(0, -1, 0, 1)
    lateinit var board: Array<Array<MutableList<Int>>>
    lateinit var copyBoard: Array<Array<MutableList<Int>>>
    lateinit var smell: Array<IntArray>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (m, s) = readLine().split(" ").map { it.toInt() }
        board = Array(4) { Array(4) { mutableListOf() } }
        smell = Array(4) { IntArray(4) {0} }
        repeat(m) {
            val (x, y, d) = readLine().split(" ").map { it.toInt() }
            board[x-1][y-1].add(d-1)
        }
        val input = readLine().split(" ")
        sx = input[0].toInt()-1
        sy = input[1].toInt()-1

        repeat(s) {
//        println("]----------] Round: ${it+1} [----------[")
//        printBoard()
            step1()
            step2()
//        printBoard()
            step3()
            step4()
            step5()
//        printBoard()
//        println()
        }

        println(board.sumOf { l -> l.sumOf { it.size } })
    }

    fun step1() {
        copyBoard = Array(4) { i -> Array(4) { j -> board[i][j]} }
    }

    fun step2() {
        val tempBoard = Array(4) { Array(4) { mutableListOf<Int>() } }
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                for (d in board[i][j]) {
                    var check = false
                    for (k in 8 downTo  1) {
                        val nd = (d+k)%8
                        val nx = i + dx[nd]
                        val ny = j + dy[nd]
                        if (nx in 0 until 4 && ny in 0 until 4 && (nx != sx || ny != sy) && smell[nx][ny] == 0) {
                            tempBoard[nx][ny].add(nd)
                            check = true
                            break
                        }
                    }
                    if (!check) tempBoard[i][j].add(d)
                }
            }
        }
        board = tempBoard
    }

    fun step3() {
        var list = listOf<Pair<Int, Int>>()
        var result = -1
        fun sharkMove(x: Int, y: Int, moves: List<Pair<Int, Int>>, n: Int) {
            if (moves.size == 3)  {
                if (n > result) {
                    list = moves
                    result = n
                }
            }
            else {
                repeat(4) {
                    val nx = x + sdx[it]
                    val ny = y + sdy[it]
                    if (nx in 0 until 4 && ny in 0 until 4) {
                        sharkMove(nx, ny, moves + (nx to ny), if (nx to ny !in moves )n + board[nx][ny].size else n)
                    }
                }
            }
        }
        sharkMove(sx, sy, list, 0)

        list.forEach { (x, y) ->
            if (board[x][y].isNotEmpty()) {
                smell[x][y] = 3
                board[x][y] = mutableListOf()
            }
        }
        sx = list.last().first
        sy = list.last().second
    }

    fun step4() {
        repeat(4) { i ->
            repeat(4) { j ->
                if (smell[i][j] > 0) smell[i][j]--
            }
        }
    }

    fun step5() {
        repeat(4) { i ->
            repeat(4) { j ->
                board[i][j].addAll(copyBoard[i][j])
            }
        }
    }

    fun printBoard() {
        println("]-----] PrintBoard(sx: $sx, sy: $sy) [-----[")
        board.forEachIndexed { i, list ->
            list.forEachIndexed { j, ints ->
                println("x: $i, y: $j, $ints")
            }
        }
        println("]-----] SmellBoard [-----[")
        smell.forEach { list ->
            list.forEach { i -> print("$i ") }
            println()
        }
    }
}