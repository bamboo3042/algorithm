package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class b1689 {
    var N = 0
    var answer = 0
    lateinit var list: Array<Pair<Int, Int>>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        list = Array(N) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            x to y
        }
        list.sortWith(compareBy({it.first}, {it.second}))
        val pq = PriorityQueue<Int>()
        list.forEach { (x, y) ->
            while (pq.isNotEmpty() && pq.peek() <= x) pq.poll()
            pq.add(y)
            answer = answer.coerceAtLeast(pq.size)
        }
        println(answer)
    }
}