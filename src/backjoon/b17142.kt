package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b17142 {
    var N = 0
    var M = 0
    lateinit var board: Array<IntArray>
    lateinit var baseBoard: Array<IntArray>
    val virus = mutableListOf<Pair<Int, Int>>()
    val walls = mutableListOf<Pair<Int, Int>>()
    val dx = arrayOf(1, -1, 0, 0)
    val dy = arrayOf(0, 0, 1, -1)
    var answer = Int.MAX_VALUE

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()

        board = Array(N) {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }

        board.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, n ->
                if (n == 2) virus.add(i to j)
                else if (n == 1) walls.add(i to j)
            }
        }

        baseBoard = Array(N) { IntArray(N) { 0 } }
        walls.map { baseBoard[it.first][it.second] = 1 }
        virus.map { baseBoard[it.first][it.second] = -2 }

        selectVirus(listOf(), 0)

        println(if (answer == Int.MAX_VALUE) -1 else answer)
    }

    fun selectVirus(list: List<Pair<Int, Int>>, n: Int) {
        if (list.size != M) for (i in n until virus.size) selectVirus(list + virus[i], i + 1)
        else spread(list)
    }

    fun spread(list: List<Pair<Int, Int>>) {
        val tempBoard = Array(N) { baseBoard[it].clone() }
        val queue = ArrayDeque<Triple<Int, Int, Int>>()
        var sum = N * N - walls.size - virus.size
        list.forEach {
            tempBoard[it.first][it.second] = -3
            queue.addLast(Triple(0, it.first, it.second))
        }

        while (queue.isNotEmpty()) {
            if (sum == 0) {
                answer = answer.coerceAtMost(queue.maxOf { it.first })
                break
            }
            val temp = queue.removeFirst()

            repeat(4) {
                val nx = temp.second + dx[it]
                val ny = temp.third + dy[it]
                if (nx in 0 until N && ny in 0 until N) {
                    if (tempBoard[nx][ny] == 0) {
                        sum--
                        tempBoard[nx][ny] = -1
                        queue.addLast(Triple(temp.first + 1, nx, ny))
                    }
                    else if (tempBoard[nx][ny] == -2) {
                        tempBoard[nx][ny] = -1
                        queue.addLast(Triple(temp.first + 1, nx, ny))
                    }
                }
            }
        }
    }
}