package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

class b17140 {
    var R = 0
    var C = 0
    var K = 0
    lateinit var board: Array<IntArray>

    data class CountNum(
        val num: Int,
        var count: Int
    ): Comparable<CountNum> {
        override fun compareTo(other: CountNum): Int {
            return if (this.count < other.count) -1
            else if (this.count > other.count) 1
            else {
                if (this.num < other.num) -1
                else 1
            }
        }

    }

    data class CountList(
        val list: PriorityQueue<CountNum> = PriorityQueue(CountNum::compareTo)
    ) {
        fun add(n: Int) {
            val countNum = list.find { it.num == n }
            if (countNum == null) list.add(CountNum(n, 1))
            else {
                list.remove(countNum)
                countNum.count++
                list.add(countNum)
            }
        }
    }

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        R = input[0].toInt()-1
        C = input[1].toInt()-1
        K = input[2].toInt()
        board = Array(3) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        var answer = 0
        while (answer <= 100) {
            if(R in board.indices && C in board[0].indices && board[R][C] == K) break

            if (board.size >= board[0].size) rowCal()
            else columnCal()
            answer++
        }

        println(if (answer > 100) -1 else answer)
    }

    fun rowCal() {
        val r = board.size
        val list = Array(r) { CountList() }

        board.mapIndexed { index, ints ->
            ints.map {
                if (it != 0) list[index].add(it)
            }
        }

        var max = 0
        list.map { max = max.coerceAtLeast(it.list.size) }

        val tempBoard = Array(r) {
            IntArray(max*2) { 0 }
        }

        list.mapIndexed { index, countList ->
            var j = 0
            while (countList.list.isNotEmpty()) {
                val temp = countList.list.poll()
                tempBoard[index][j] = temp.num
                tempBoard[index][j+1] = temp.count
                j += 2
            }
        }

        board = tempBoard
    }

    fun columnCal() {
        val r = board.size
        val c = board[0].size
        val list = Array(c) { CountList() }

        for (j in 0 until c) {
            for (i in 0 until r) {
                if (board[i][j] != 0) list[j].add(board[i][j])
            }
        }

        var max = 0
        list.map { max = max.coerceAtLeast(it.list.size) }

        val tempBoard = Array(max*2) {
            IntArray(c) { 0 }
        }

        list.mapIndexed { index, countList ->
            var i = 0
            while (countList.list.isNotEmpty()) {
                val temp = countList.list.poll()
                tempBoard[i][index] = temp.num
                tempBoard[i+1][index] = temp.count
                i += 2
            }
        }

        board = tempBoard
    }
}