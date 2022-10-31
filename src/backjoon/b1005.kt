package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class b1005 {
    var dList: List<Int> = listOf()
    var indegree: MutableList<Int> = mutableListOf()
    var orderList: List<MutableList<Int>> = listOf()
    var w: Int = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val t = readLine().toInt()
        repeat(t) {
            val (n, k) = readLine().split(" ").map { it.toInt() }
            dList = readLine().split(" ").map { it.toInt() }
            indegree = MutableList(n) { 0 }
            orderList = List (n) { mutableListOf<Int>() }
            repeat(k) {
                val (a, b) = readLine().split(" ").map { it.toInt() }
                orderList[a-1].add(b-1)
                indegree[b-1] += 1
            }
            w = readLine().toInt() - 1

            topologicalSort()
        }
    }

    fun topologicalSort() {
        val q: Queue<Int> = LinkedList ()
        val answer = Array (dList.size) {0}

        for(index in indegree.indices) {
            answer[index] = dList[index]
            if(indegree[index] == 0) {
                q.add(index)
            }
        }

        while(q.isNotEmpty()) {
            val temp  = q.poll()

            for(o in orderList[temp]) {
                answer[o] = if(answer[o] > answer[temp] + dList[o]) answer[o] else answer[temp] + dList[o]
                if(--indegree[o] == 0) q.add(o)
            }
        }

        println(answer[w])
    }
}