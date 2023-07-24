package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b1948 {
    lateinit var distance: List<MutableList<Pair<Int, Int>>>
    lateinit var reverse: List<MutableList<Pair<Int, Int>>>
    var N = 0
    var M = 0
    val queue: ArrayDeque<Pair<Int, Int>> = ArrayDeque()
    lateinit var totalTime: IntArray
    lateinit var link: IntArray
    var count = 0

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        M = readLine().toInt()
        distance = List(N) { mutableListOf() }
        reverse = List(N) { mutableListOf() }
        totalTime = IntArray(N) {0}
        link = IntArray(N) {0}
        repeat(M) {
            val (a, b, c) = readLine().split(" ").map { it.toInt() }
            distance[a-1].add(b-1 to c)
            reverse[b-1].add(a-1 to c)
            link[b-1]++
        }
        val (first, last) = readLine().split(" ").map { it.toInt()-1 }
        queue.add(first to 0)

        while (queue.isNotEmpty()) {
            val (temp, time) = queue.removeFirst()
            distance[temp].forEach { (n, t) ->
                if (time + t > totalTime[n]) totalTime[n] = time + t
                if (--link[n] == 0) queue.add(n to totalTime[n])
            }
        }
        println(totalTime[last])
        val visited = BooleanArray(N) {false}
        queue.add(last to totalTime[last])
        while (queue.isNotEmpty()) {
            val (temp, time) = queue.removeFirst()
            reverse[temp].forEach { (n, t) ->
                if (time - t == totalTime[n]) {
                    count++
                    if (!visited[n]){
                        visited[n] = true
                        queue.add(n to totalTime[n])
                    }
                }
            }
        }
        println(count)
    }
}