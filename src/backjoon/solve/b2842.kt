package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2842 {
    var N = 0
    lateinit var board: Array<CharArray>
    lateinit var heights: Array<IntArray>
    val house = mutableListOf<Pair<Int, Int>>()
    lateinit var postOffice: Pair<Int, Int>
    val dx = arrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
    val dy = arrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        board = Array(N) { readLine().toCharArray() }
        heights = Array(N) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        val arr = heights.flatMap { it.toList() }.distinct().sorted()

        board.forEachIndexed { i, chars ->
            chars.forEachIndexed { j, c ->
                if (c == 'K')  house.add(i to j)
                else if (c == 'P') postOffice = i to j
            }
        }

        var left = 0
        var right = 0
        var answer = Int.MAX_VALUE

        while (right < arr.size) {
            while (left <= right && bfs(arr[left], arr[right])) {
                answer = answer.coerceAtMost(arr[right] - arr[left])
                left++
            }
            right++
        }

        println(answer)
    }

    fun bfs(min: Int, max: Int): Boolean {
        if (heights[postOffice.first][postOffice.second] !in min .. max) return false

        val visited = Array(N) { BooleanArray(N) {false} }
        val queue = ArrayDeque<Pair<Int, Int>>()
        visited[postOffice.first][postOffice.second] = true
        queue.add(postOffice.first to postOffice.second)

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()

            repeat(8) {
                val nx = x + dx[it]
                val ny = y + dy[it]
                if (nx in 0 until N && ny in 0 until N && heights[nx][ny] in min .. max && !visited[nx][ny]) {
                    visited[nx][ny] = true
                    queue.addLast(nx to ny)
                }
            }
        }

        house.forEach { (x, y) -> if (!visited[x][y]) return false }

        return true
    }
}