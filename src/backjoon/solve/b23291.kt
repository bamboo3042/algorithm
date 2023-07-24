package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b23291 {
    val dx = arrayOf(-1, 0, 1, 0)
    val dy = arrayOf(0, -1, 0, 1)
    var N = 0
    var K = 0
    var answer = 0
    lateinit var bowl: IntArray

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        K = input[1].toInt()
        bowl = readLine().split(" ").map { it.toInt() }.toIntArray()

        while (bowl.max() - bowl.min() > K) {
            answer++
            step1()
            step2()
        }

        println(answer)
    }

    fun step1() {
        val min = bowl.min()
        for (i in bowl.indices) if (bowl[i] == min) bowl[i]++
        var board = mutableListOf(mutableListOf(bowl[0]))
        val bottom = ArrayDeque<Int> (N)
        bowl.forEach { bottom.addLast(it) }
        bottom.removeFirst()
        while (board.size + 1 <= bottom.size - board[0].size) {
            board.add(bottom.subList(0, board.first().size).toMutableList())
            repeat(board.first().size) { bottom.removeFirst() }
            val newBoard = mutableListOf<MutableList<Int>>()
            for (i in board.last().indices) {
                val list = mutableListOf<Int>()
                for (j in board.size - 1 downTo 0) {
                    list.add(board[j][i])
                }
                newBoard.add(list)
            }
            board = newBoard
        }
        board.add(bottom)
        control(board)
    }

    fun step2() {
        var board = listOf(bowl.toList())
        repeat(2) {
            val left = board.map { it.subList(0, it.size/2) }
            val right = board.map { it.subList(it.size/2, it.size) }
            val newBoard = ArrayDeque<List<Int>>()
            left.forEach { newBoard.addFirst(it.reversed()) }
            right.forEach { newBoard.addLast(it) }
            board = newBoard
        }
        control(board)
    }

    fun control(board: List<List<Int>>) {
        val newBoard = board.map { it.toMutableList() }
        for (i in board.indices) {
            for (j in board[i].indices) {
                repeat(4) {
                    val nx = i + dx[it]
                    val ny = j + dy[it]
                    if (nx in board.indices && ny in board[nx].indices) {
                        val d = (board[i][j] - board[nx][ny])/5
                        if (d > 0){
                            newBoard[i][j] -= d
                            newBoard[nx][ny] += d
                        }
                    }
                }
            }
        }
        var c = 0
        for (j in newBoard.last().indices) {
            for (i in newBoard.size - 1 downTo 0) {
                if (j in newBoard[i].indices) {
                    bowl[c++] = newBoard[i][j]
                }
            }
        }
    }
}