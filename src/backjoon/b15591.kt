package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b15591 {
    var N = 0
    var Q = 0
    lateinit var dis: Array<MutableList<Pair<Int, Int>>>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val sb = StringBuilder()
        val input = readLine().split(" ").map { it.toInt() }
        N = input[0]
        Q = input[1]
        dis = Array(N) { mutableListOf() }

        repeat(N-1) {
            val (p, q, r) = readLine().split(" ").map { it.toInt() }
            dis[p-1].add(q-1 to r)
            dis[q-1].add(p-1 to r)
        }

        repeat(Q) {
            val (k, v) = readLine().split(" ").map { it.toInt() }
            sb.append(bfs(k, v-1)).appendLine()
        }
        println(sb)
    }

    fun bfs(k: Int, v: Int): Int {
        val visited = BooleanArray(N) { false }
        val queue = ArrayDeque<Pair<Int, Int>> ()
        var answer = 0
        visited[v] = true
        for ((q, r) in dis[v]) {
            visited[q] = true
            queue.add(q to r)
        }

        while (queue.isNotEmpty()) {
            val (q, r) = queue.removeFirst()

            if (r < k) continue

            answer += 1
            for ((nq, nr) in dis[q]) {
                if (!visited[nq]) {
                    visited[nq] = true
                    queue.add(nq to r.coerceAtMost(nr))
                }
            }
        }

        return answer
    }
}