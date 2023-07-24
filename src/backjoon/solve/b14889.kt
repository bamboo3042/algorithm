package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.absoluteValue

class b14889 {
    var N = 0
    lateinit var board: Array<IntArray>
    var answer = Int.MAX_VALUE

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        board = Array(N) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        solve(0, listOf(), listOf())
        println(answer)
    }

    fun solve(n: Int, l1: List<Int>, l2: List<Int>) {
        if (n == N) answer = answer.coerceAtMost((getScore(l1) - getScore(l2)).absoluteValue)
        else {
            if (l1.size < N/2) solve(n+1, l1+n, l2)
            if (l2.size < N/2) solve(n+1, l1, l2+n)
        }
    }

    fun getScore(list: List<Int>): Int {
        var score = 0
        for (i in list.indices) {
            for (j in i + 1 until list.size) {
                score += board[list[i]][list[j]] + board[list[j]][list[i]]
            }
        }
        return score
    }
}