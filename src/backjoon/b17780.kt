package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b17780 {
    lateinit var board: Array<IntArray>
    val chessList = mutableListOf<Chess>()
    var N = 0
    var K = 0
    val dx = arrayOf(0, -1, 0, 1)
    val dy = arrayOf(1, 0, -1, 0)
    var isEnd = false
    var t = 0

    data class Chess(
        val index: Int,
        var x: Int,
        var y: Int,
        var d: Int,
        var up: Int?,
        var down: Int?
    )

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        K = input[1].toInt()
        board = Array(N) {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }

        repeat(K) {
            val (x, y, d) = readLine().split(" ").map { it.toInt() }
            chessList.add(
                Chess(it, x-1, y-1, when(d) {
                    1 -> 0
                    2 -> 2
                    3 -> 1
                    4 -> 3
                    else -> 0
                }, null, null))
        }

        t = 0
        while (!isEnd && t <= 1000) {
            t++
            for(i in 0 until K) {
                if (isEnd) break
                move(i, true)
            }
        }

        println(if (t > 1000) -1 else t)
    }

    fun move(cn: Int, isFirst: Boolean) {
        var chess = chessList[cn]

        if (chess.down != null) return

        val nx = chess.x + dx[chess.d]
        val ny = chess.y + dy[chess.d]
        if (nx in 0 until N && ny in 0 until N && board[nx][ny] != 2) {
            if (board[nx][ny] == 1) {
                var temp = chess
                while (temp.up != null) {
                    val i = temp.down
                    temp.down = temp.up
                    temp.up = i
                    temp = chessList[temp.down!!]
                }
                temp.up = temp.down
                temp.down = null
                chess = temp
            }
        }
        else if (isFirst) {
            chessList[cn].d = (chessList[cn].d + 2) % 4
            move(cn, false)
            return
        }
        else return

        var nextChess = chessList.firstOrNull { it.x == nx && it.y == ny && it.down == null }
        if (nextChess != null) {
            while (nextChess!!.up != null) nextChess = chessList[nextChess.up!!]
            nextChess.up = chess.index
            chess.down = nextChess.index
        }
        nextChess = chess
        while (nextChess!!.up != null) {
            nextChess.x = nx
            nextChess.y = ny
            nextChess = chessList[nextChess.up!!]
        }
        nextChess.x = nx
        nextChess.y = ny

        var count = 1
        while (nextChess!!.down != null) {
            nextChess = chessList[nextChess.down!!]
            count++
        }
        if(count >= 4) isEnd = true
    }

    fun printChess(n: Int) {
        println("]-----] n: $n [-----[")
        chessList.map {
            println(it)
        }
        println()
    }
}