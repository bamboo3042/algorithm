package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b21611 {
    var N = 0
    var M = 0
    lateinit var board: Array<IntArray>
    lateinit var bubbles: MutableList<Int>
    val dx = arrayOf(0, 1, 0, -1)
    val dy = arrayOf(-1, 0, 1, 0)
    var answer = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()
        board = Array(N) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        boardToList()
        repeat(M) {
            val (d, s) = readLine().split(" ").map { it.toInt() }
            println("]-----] Blizzard [-----[")
            blizzard(d, s)
            boardChange()
            printBoard()
            dropBubbles()
            while (bubbleCheck()) dropBubbles()
            println("Bubble Change")
            bubbleChange()
            boardChange()
            printBoard()
            println()
        }
        println(answer)
    }

    fun boardToList() {
        val list = mutableListOf<Int>()
        var x = N/2
        var y = N/2
        var d = 0
        var turn = 1
        while (list.size != N * N - 1) {
            for (t in 0 until turn) {
                x += dx[d]
                y += dy[d]
                if (x == 0 && y == -1) break
                list.add(board[x][y])
            }

            if (d%2 == 1) turn++
            d = (d+1)%4
        }
        bubbles = list
    }

    fun blizzard(d: Int, s: Int) {
        var a = when(d) {
            1 -> 6
            2 -> 2
            3 -> 0
            4 -> 4
            else -> 0
        }
        var n = a+1+8
        for (t in 0 until s) {
            val index = a
            bubbles[index] = 0
            a += n
            n += 8
        }
    }

    fun dropBubbles() {
        for (i in bubbles.indices) {
            if (bubbles[i] == 0) {
                for (j in i + 1 until bubbles.size) {
                    if (bubbles[j] != 0) {
                        bubbles[i] = bubbles[j]
                        bubbles[j] = 0
                        break
                    }
                }
            }
        }
        boardChange()
        printBoard()
    }

    fun bubbleCheck(): Boolean {
        var i = 0
        var chcek = false
        while (i < bubbles.size - 4) {
            if (bubbles[i] == 0) {
                i++
                continue
            }
            var j = i
            while (++j < bubbles.size && bubbles[j] == bubbles[i]) {}
            if (j - i >= 4) {
                answer += bubbles[i] * (j - i)
                var k = i
                while (k < bubbles.size && k <= j-1) bubbles[k++] = 0
                chcek = true
            }
            i = j
        }
        boardChange()
        printBoard()
        return chcek
    }

    fun bubbleChange() {
        val list = mutableListOf<Int>()
        var i = 0
        while (i < bubbles.size) {
            if (bubbles[i] == 0) break
            else {
                var j = i
                while (++j < bubbles.size && bubbles[j] == bubbles[i]) {}
                if (list.size < bubbles.size) list.add(j-i)
                if (list.size < bubbles.size) list.add(bubbles[i])
                i = j
            }
        }
        while (list.size < bubbles.size) list.add(0)
        bubbles = list
    }

    fun boardTest(n: Int) {
        var x = n/2
        var y = n/2
        var d = 0
        var turn = 1
        var cnt = 1
        board = Array(n) { IntArray(n) {0} }
        while (x != 0 || y != -1) {
            for (t in 0 until turn) {
                x += dx[d]
                y += dy[d]
                if (x == 0 && y == -1) break
                println("$x, $y")
                board[x][y] = cnt++
            }

            if (d%2 == 1) turn++
            d = (d+1)%4
        }
    }

    fun boardChange() {
        var x = N/2
        var y = N/2
        var d = 0
        var turn = 1
        var cnt = 0
        while (x != 0 || y != -1) {
            for (t in 0 until turn) {
                x += dx[d]
                y += dy[d]
                if (x == 0 && y == -1) break
                board[x][y] = bubbles[cnt++]
            }

            if (d%2 == 1) turn++
            d = (d+1)%4
        }
    }

    fun printBoard() {
        println("]-----] Board [-----[")

        board.forEach { l ->
            l.forEach { print("${String.format("%1d", it)} ") }
            println()
        }
    }
}