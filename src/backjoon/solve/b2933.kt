package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2933 {
    var R = 0
    var C = 0
    val dx = arrayOf(0, 0, 1, -1)
    val dy = arrayOf(1, -1, 0, 0)
    lateinit var board: Array<CharArray>
    lateinit var visited: Array<BooleanArray>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ").map { it.toInt() }
        R = input[0]
        C = input[1]
        board = Array(R) { readLine().toCharArray() }
        var isLeft = true
        readLine()
        readLine().split(" ").forEach {
            val t = R - it.toInt()
            remove(t, isLeft)
            isLeft = !isLeft
            down(boardCheck())
        }
        board.forEach { println(it.joinToString("")) }
    }

    fun remove(t: Int, isLeft: Boolean) {
        for (i in board[t].indices.let { if (isLeft) it else it.reversed()}) {
            if (board[t][i] == 'x') {
                board[t][i] = '.'
                return
            }
        }
    }

    fun boardCheck(): List<Pair<Int, Int>> {
        visited = Array(R) { BooleanArray(C) {false} }
        for (i in 0 until R) {
            for (j in 0 until C) {
                if (board[i][j] == 'x' && !visited[i][j]) {
                    val list = groupCheck(i, j)
                    if (list.isNotEmpty()) return list
                }
            }
        }
        return listOf()
    }

    fun groupCheck(r: Int, c: Int): List<Pair<Int, Int>> {
        val list = mutableListOf<Pair<Int, Int>>()
        val queue = ArrayDeque<Pair<Int, Int>> ()
        var check = false
        queue.addLast(r to c)
        visited[r][c] = true

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            list.add(x to y)

            repeat(4) {
                val nx = x + dx[it]
                val ny = y + dy[it]
                if (nx in 0 until R && ny in 0 until C && !visited[nx][ny] && board[nx][ny] == 'x') {
                    if(nx == R-1) check = true

                    queue.addLast(nx to ny)
                    visited[nx][ny] = true
                }
            }
        }

        return if (check) listOf() else list
    }

    fun down(list: List<Pair<Int, Int>>) {
        if (list.isEmpty()) return
        for ((x, y) in list) board[x][y] = '.'

        var prevList: List<Pair<Int, Int>>
        var nextList = list.toMutableList()

        while (true) {
            prevList = nextList
            nextList = mutableListOf()

            prevList.forEach { (x, y) ->
                if (x + 1 in 0 until R) {
                    if (board[x + 1][y] == 'x') return@forEach
                    else nextList.add(x + 1 to y)
                }
            }

            if (nextList.size != prevList.size) break
        }
        prevList.forEach { (x, y) -> board[x][y] = 'x' }
    }
}