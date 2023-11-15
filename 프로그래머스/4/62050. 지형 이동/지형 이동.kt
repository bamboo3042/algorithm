import java.util.*
import kotlin.math.absoluteValue

class Solution {
    fun solution(land: Array<IntArray>, height: Int): Int {
        var answer = 0
        val dx = arrayOf(1, -1, 0, 0)
        val dy = arrayOf(0, 0, 1, -1)
        val heap = PriorityQueue<Triple<Int, Int, Int>> { o1, o2 -> o1.first - o2.first }
        val visited = Array (land.size) { BooleanArray(land[0].size) { false } }
        heap.offer(Triple(0, 0, 0))
        while (heap.isNotEmpty()) {
            val (d, x, y) = heap.poll()
            if (visited[x][y]) continue
            visited[x][y] = true

            answer += d

            repeat(4) {
                val nx = x + dx[it]
                val ny = y + dy[it]

                if (nx in land.indices && ny in land[0].indices && !visited[nx][ny]) {
                    val nd = if ((land[x][y] - land[nx][ny]).absoluteValue <= height) 0 else (land[x][y] - land[nx][ny]).absoluteValue
                    heap.offer(Triple(nd, nx, ny))
                }
            }
        }
        return answer
    }
}