import java.util.PriorityQueue

class Solution {
    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        val board = IntArray(n+1) { -1 }
        val map = Array<MutableList<Int>> (n+1) { mutableListOf() }
        val pq = PriorityQueue<Pair<Int, Int>> { o1, o2 -> o1.first - o2.first }
        val visited = BooleanArray(n+1) { false }

        board[destination] = 0
        roads.forEach { (a, b) ->
            map[a].add(b)
            map[b].add(a)
        }
        pq.add(0 to destination)
        visited[destination] = true

        while (pq.isNotEmpty()) {
            val (d, t) = pq.poll()

            board[t] = d

            map[t].forEach { next ->
                if (!visited[next]) {
                    visited[next] = true
                    pq.add(d+1 to next)
                }
            }
        }

        return sources.map { board[it] }.toIntArray()
    }
}