package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

class b14500 {
    lateinit var board: Array<IntArray>
    var n: Int = 0
    var m: Int = 0
    val tet = listOf(
        listOf(Pair(0, 0), Pair(0, 1), Pair(0, 2), Pair(0, 3)),
        listOf(Pair(0, 0), Pair(1, 0), Pair(2, 0), Pair(3, 0)),

        listOf(Pair(0, 0), Pair(0, 1), Pair(1, 0), Pair(1,1)),

        listOf(Pair(0, 0), Pair(0, 1), Pair(1, 0), Pair(2, 0)),
        listOf(Pair(0, 0), Pair(1, 0), Pair(1, 1), Pair(1, 2)),
        listOf(Pair(0, 0), Pair(0, 1), Pair(-1, 1), Pair(-2, 1)),
        listOf(Pair(0, 0), Pair(0, 1), Pair(0, 2), Pair(1, 2)),

        listOf(Pair(0, 0), Pair(0, 1), Pair(1, 1), Pair(2, 1)),
        listOf(Pair(0, 0), Pair(0, 1), Pair(0, 2), Pair(-1, 2)),
        listOf(Pair(0, 0), Pair(1, 0), Pair(2, 0), Pair(2, 1)),
        listOf(Pair(0, 0), Pair(0, 1), Pair(0, 2), Pair(1, 0)),

        listOf(Pair(0, 0), Pair(1, 0), Pair(1, 1), Pair(2, 1)),
        listOf(Pair(0, 0), Pair(0, 1), Pair(-1, 1), Pair(-1, 2)),
        listOf(Pair(0, 0), Pair(1, 0), Pair(1, -1), Pair(2, -1)),
        listOf(Pair(0, 0), Pair(0, 1), Pair(1, 1), Pair(1, 2)),

        listOf(Pair(0, 0), Pair(0, 1), Pair(-1, 1), Pair(0, 2)),
        listOf(Pair(0, 0), Pair(0, 1), Pair(1, 1), Pair(0, 2)),
        listOf(Pair(0, 0), Pair(1, 0), Pair(1, 1), Pair(2, 0)),
        listOf(Pair(0, 0), Pair(1, 0), Pair(1, -1), Pair(2, 0)),
    )

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        n = input[0].toInt()
        m = input[1].toInt()

        board = Array(n) { readLine().split(" ").map { num -> num.toInt() }.toIntArray() }

        var answer = 0

        for (i in 0 until n) {
            for (j in 0 until m) {
                for (t in tet) {
                    var temp = 0
                    for (p in t) {
                        val x = i + p.first
                        val y = j + p.second
                        if(x !in 0 until n || y !in 0 until m ) break
                        temp += board[x][y]
                    }
                    answer = max(answer, temp)
                }
            }
        }

        println(answer)
    }
}