import java.util.PriorityQueue

class Solution {
    fun solution(food_times: IntArray, k: Long): Int {
        val pq = PriorityQueue<Pair<Int, Int>> { o1, o2 -> o1.first - o2.first }
        var prev = 0
        var tk = k

        food_times.forEachIndexed { index, i -> pq.add(i to index + 1) }

        while (pq.isNotEmpty() && tk >= (pq.size.toLong() * (pq.peek().first - prev)) ) {

            val (t, _) = pq.poll()

            tk -= (t - prev).toLong() * (pq.size + 1)

            while (pq.isNotEmpty() && pq.peek().first == t) pq.poll()

            prev = t
        }

        return if (pq.isEmpty()) -1
        else pq.toList().sortedBy { it.second }[(tk % pq.size.toLong()).toInt()].second
    }
}