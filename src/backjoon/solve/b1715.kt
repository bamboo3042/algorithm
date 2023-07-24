package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class b1715 {
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val q : PriorityQueue<Int> = PriorityQueue()
        var answer = 0
        repeat(n) { q.add(readLine().toInt()) }
        while(q.size > 1) {
            val temp1 = q.poll()
            val temp2 = q.poll()
            answer += temp1 + temp2
            q.add(temp1 + temp2)
        }

        println(answer)
    }
}