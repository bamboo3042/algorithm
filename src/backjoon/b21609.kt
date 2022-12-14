package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b21609 {
    var N = 0
    var M = 0
    var dx = arrayOf(-1, 0, 0, 1)
    var dy = arrayOf(0, -1, 1, 0)
    var rainbowCount = 0
    var answer = 0
    lateinit var board: Array<Array<Int?>>
    lateinit var boardCheck: Array<BooleanArray>
    lateinit var block: List<Pair<Int, Int>>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()
        board = Array(N) { readLine().split(" ").map { it.toInt() }.toTypedArray() }
        while (true) {
            rainbowCount = 0
            block = listOf()
            boardCheck = Array(N) { BooleanArray(N) {false} }
            for (i in 0 until N) {
                for (j in 0 until N) {
                    if (!boardCheck[i][j] && board[i][j] != null && board[i][j]!! > 0) findBlock(i, j)
                }
            }

            if (block.size < 2) break
            removeBlock()
            drop()
            rotate()
            drop()
        }

        println(answer)

    }

    fun findBlock(x: Int, y: Int) {
        val n = board[x][y]
        val queue = mutableListOf(x to y)
        val tempList = mutableListOf<Pair<Int, Int>>()
        var tempCount = 0
        boardCheck[x][y] = true

        while (queue.isNotEmpty()) {
            val temp = queue.removeFirst()
            tempList.add(temp)

            repeat(4) {
                val nx = temp.first + dx[it]
                val ny = temp.second + dy[it]
                if (nx in 0 until N && ny in 0 until N && !boardCheck[nx][ny] && (board[nx][ny] == 0 || board[nx][ny] == n)) {
                    if (board[nx][ny] == 0) tempCount++
                    queue.add(nx to ny)
                    boardCheck[nx][ny] = true
                }
            }
        }

        tempList.forEach { (x, y) -> if (board[x][y] == 0) boardCheck[x][y] = false }

        if (tempList.size < 2) return
        if (tempList.size > block.size) {
            block = tempList
            rainbowCount = tempCount
        }
        else if (tempList.size == block.size) {
            if (tempCount >= rainbowCount) {
                block = tempList
                rainbowCount = tempCount
            }
        }
    }

    fun removeBlock() {
        var count = 0
        block.forEach{ (x, y) ->
            count++
            board[x][y] = null
        }
        answer += count * count
    }

    fun drop() {
        for (j in 0 until N) {
            for(i in N-1 downTo 0) {
                if (board[i][j] != null) continue

                var b: Int? = null
                var k = i
                while (--k in i downTo 0) {
                    if (board[k][j] == -1) break
                    else if (board[k][j] != null) {
                        b = board[k][j]
                        board[k][j] = null
                        break
                    }
                }
                board[i][j] = b
            }
        }
    }

    fun rotate() {
        val tempBoard = Array(N) { Array<Int?>(N) { null } }

        for (i in 0 until N) {
            for (j in 0 until N) {
                tempBoard[N - j - 1][i] = board[i][j]
            }
        }

        board = tempBoard
    }

    fun printBoard() {
        println("]-----] Board [-----[")

        board.forEach { l ->
            l.forEach { print("${String.format("%4d", it)} ") }
            println()
        }
    }
}