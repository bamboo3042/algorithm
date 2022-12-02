package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b13460 {
    var n: Int = 0
    var m: Int = 0
    lateinit var board: Array<CharArray>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        n = input[0].toInt()
        m = input[1].toInt()
        board = Array(n) { readLine().toCharArray() }

        println(solve())
    }

    fun solve(): Int {
        val queue = ArrayDeque<Triple<Int,Pair<Int, Int>, Pair<Int, Int>>> ()
        var b = Pair(0, 0)
        var r = Pair(0, 0)
        var hole = Pair(0, 0)

        for(i in 1 .. n - 2) {
            for(j in 1 .. m - 2) {
                if(board[i][j] == 'B') {
                    b = Pair(i, j)
                    board[i][j] = '.'
                }
                else if(board[i][j] == 'R') {
                    r = Pair(i, j)
                    board[i][j] = '.'
                }
                else if(board[i][j] == 'O') hole = Pair(i, j)
            }
        }

        queue.add(Triple(0, b, r))

        while(queue.isNotEmpty()) {
            val tq = queue.removeFirst()

            if(hole == tq.second && hole == tq.third) continue
            if(hole == tq.second) continue
            if(hole == tq.third) return tq.first
            if(tq.first == 10) continue

            val result1 = moveLeft(tq.second, tq.third)
            val result2 = moveRight(tq.second, tq.third)
            val result3 = moveUp(tq.second, tq.third)
            val result4 = moveDown(tq.second, tq.third)

            if(result1.first != tq.second || result1.second != tq.third) queue.add(Triple(tq.first + 1, result1.first, result1.second))
            if(result2.first != tq.second || result2.second != tq.third) queue.add(Triple(tq.first + 1, result2.first, result2.second))
            if(result3.first != tq.second || result3.second != tq.third) queue.add(Triple(tq.first + 1, result3.first, result3.second))
            if(result4.first != tq.second || result4.second != tq.third) queue.add(Triple(tq.first + 1, result4.first, result4.second))
        }

        return -1
    }

    fun bLeft(b: Pair<Int, Int>, r: Pair<Int, Int>): Pair<Int, Int> {
        for(i in b.second - 1 downTo 0) {
            if(board[b.first][i] == 'O') return Pair(b.first, i)
            if(i == r.second && b.first == r.first) return Pair(b.first, i+1)
            if(board[b.first][i] == '.') continue
            return Pair(b.first, i+1)
        }
        return Pair(b.first, 1)
    }

    fun moveLeft(b: Pair<Int, Int>, r: Pair<Int, Int>): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        var newB = Pair(b.first, b.second)
        var newR = Pair(r.first, r.second)

        if(b.second < r.second) {
            newB = bLeft(newB, newR)
            newR = bLeft(newR, newB)
        }
        else {
            newR = bLeft(newR, newB)
            newB = bLeft(newB, newR)
        }

        return Pair(newB, newR)
    }

    fun bRight(b: Pair<Int, Int>, r: Pair<Int, Int>): Pair<Int, Int> {
        for(i in b.second + 1 .. m - 1) {
            if(board[b.first][i] == 'O') return Pair(b.first, i)
            if(i == r.second && b.first == r.first) return Pair(b.first, i-1)
            if(board[b.first][i] == '.') continue
            return Pair(b.first, i-1)
        }
        return Pair(b.first, m - 2)
    }

    fun moveRight(b: Pair<Int, Int>, r: Pair<Int, Int>): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        var newB = Pair(b.first, b.second)
        var newR = Pair(r.first, r.second)

        if(b.second < r.second) {
            newR = bRight(newR, newB)
            newB = bRight(newB, newR)
        }
        else {
            newB = bRight(newB, newR)
            newR = bRight(newR, newB)
        }

        return Pair(newB, newR)
    }

    fun bDown(b: Pair<Int, Int>, r: Pair<Int, Int>): Pair<Int, Int> {
        for(i in b.first + 1 .. n - 1) {
            if(board[i][b.second] == 'O') return Pair(i, b.second)
            if(i == r.first && b.second == r.second) return Pair(i-1, b.second)
            if(board[i][b.second] == '.') continue
            return Pair(i-1, b.second)
        }
        return Pair(n - 2, b.second)
    }

    fun moveDown(b: Pair<Int, Int>, r: Pair<Int, Int>): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        var newB = Pair(b.first, b.second)
        var newR = Pair(r.first, r.second)

        if(newB.first < newR.first) {
            newR = bDown(newR, newB)
            newB = bDown(newB, newR)
        }
        else {
            newB = bDown(newB, newR)
            newR = bDown(newR, newB)
        }

        return Pair(newB, newR)
    }

    fun bUp(b: Pair<Int, Int>, r: Pair<Int, Int>): Pair<Int, Int> {
        for(i in b.first - 1 downTo  0) {
            if(board[i][b.second] == 'O') return Pair(i, b.second)
            if(i == r.first && b.second == r.second) return Pair(i+1, b.second)
            if(board[i][b.second] == '.') continue
            return Pair(i+1, b.second)
        }
        return Pair(1, b.second)
    }

    fun moveUp(b: Pair<Int, Int>, r: Pair<Int, Int>): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        var newB = Pair(b.first, b.second)
        var newR = Pair(r.first, r.second)

        if(newB.first < newR.first) {
            newB = bUp(newB, newR)
            newR = bUp(newR, newB)
        }
        else {
            newR = bUp(newR, newB)
            newB = bUp(newB, newR)
        }

        return Pair(newB, newR)
    }
}