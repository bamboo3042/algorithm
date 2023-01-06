package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class b19598 {
    var N = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        val pq1 = PriorityQueue<Pair<Int, Int>>(compareBy({it.first}, {it.second}))
        val pq2 = PriorityQueue<Int> ()
        repeat(N) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            pq1.add(x to y)
        }
        var t = pq1.peek().first
        var answer = 0
        while (pq1.isNotEmpty()) {
            val (x, y) = pq1.poll()
            while (pq2.isNotEmpty() && pq2.peek() <= x) pq2.poll()
            pq2.add(y)
            answer = answer.coerceAtLeast(pq2.size)
        }
        println(answer)
    }
}