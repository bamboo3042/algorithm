package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.absoluteValue

class b16234 {
    var N = 0
    var L = 0
    var R = 0
    val dx = arrayOf(1, -1, 0, 0)
    val dy = arrayOf(0, 0, 1, -1)

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        L = input[1].toInt()
        R = input[2].toInt()
        val board = Array(N) {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }

        println(solve(0, board))
    }

    fun solve(n: Int, prevBoard: Array<IntArray>): Int {
        val boardCheck = Array(N) { BooleanArray(N) { true } }
        val newBoard = Array(N) { IntArray(N) { 0 } }
        for (i in 0 until N) {
            for (j in 0 until N) {
                if (boardCheck[i][j]){
                    boardCheck[i][j] = false
                    val queue = ArrayDeque<Pair<Int, Int>> ()
                    queue.addLast(i to j)
                    val list = mutableListOf(i to j)
                    var sum = 0
                    var count = 0
                    while (queue.isNotEmpty()) {
                        val temp = queue.removeFirst()
                        sum += prevBoard[temp.first][temp.second]
                        count++

                        repeat(4) {
                            val nx = temp.first + dx[it]
                            val ny = temp.second + dy[it]
                            if (nx in 0 until N && ny in 0 until N && boardCheck[nx][ny] && (prevBoard[temp.first][temp.second] - prevBoard[nx][ny]).absoluteValue in L .. R ) {
                                boardCheck[nx][ny] = false
                                queue.addLast(nx to ny)
                                list.add(nx to ny)
                            }
                        }
                    }
                    val newCount = sum / count
                    list.map {
                        newBoard[it.first][it.second] = newCount
                    }
                }
            }
        }

        for (i in 0 until N) {
            for (j in 0 until N) {
                if (prevBoard[i][j] != newBoard[i][j]) return solve(n + 1, newBoard)
            }
        }

        return n
    }
}