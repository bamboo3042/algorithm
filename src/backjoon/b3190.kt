package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayDeque

class b3190 {
    var n: Int = 0
    var k: Int = 0
    var l: Int = 0
    lateinit var board: Array<IntArray>
    lateinit var moveList: Queue<Pair<Int, String>>
    val snake = ArrayDeque<Pair<Int, Int>> ()
    val dxs = arrayOf(0, 1, 0, -1)
    val dys = arrayOf(1, 0, -1, 0)

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        snake.addFirst(Pair(0, 0))
        moveList = LinkedList()
        n = readLine().toInt()
        board = Array(n) { IntArray(n) {0} }
        k = readLine().toInt()
        repeat(k) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            board[x-1][y-1] = 1
        }
        l = readLine().toInt()
        repeat(l) {
            val (x, y) = readLine().split(" ")
            moveList.add(Pair(x.toInt(), y))
        }

        println(solve())
    }

    fun solve(): Int {
        var answer = 0
        var d = 0
        var move: Pair<Int, String>? = moveList.poll()

        while (true) {
            answer++
            val head = snake.first()
            val x = head.first + dxs[d]
            val y = head.second + dys[d]

            if(x >= n || x < 0 || y >= n || y < 0) break
            if(Pair(x, y) in snake) break

            snake.addFirst(Pair(x, y))

            if(board[x][y] == 1) board[x][y] = 0
            else snake.removeLast()

            if(move != null) {
                if(answer == move.first) {
                    d = if(move.second == "D") (d + 1) % 4
                    else (d + 3) % 4
                    move = moveList.poll()
                }
            }
        }

        return answer
    }
}