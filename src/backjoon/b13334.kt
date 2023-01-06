package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class b13334 {
    var N = 0
    var d =0
    lateinit var list: List<Pair<Int, Int>>
    var answer = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        val pq = PriorityQueue<Pair<Int, Int>> { a, b ->
            a.first - b.first
        }
        list = List(N) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            if (x < y) x to y else y to x
        }
        d = readLine().toInt()
        list = list.sortedWith { a, b ->
            if (a.second == b.second) a.first - b.first else a.second - b.second
        }
        for ((x, y) in list) {
            if (y - x > d) continue
            val e = y-d
            while (pq.isNotEmpty() && pq.peek().first < e) pq.poll()
            pq.add(x to y)
            answer = answer.coerceAtLeast(pq.size)
        }
        println(answer)
    }
}