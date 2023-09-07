package programmers

import java.util.PriorityQueue

class 합승_택시_요금 {
    class Solution {

        lateinit var board: Array<IntArray>
        lateinit var dp: Array<IntArray>
        lateinit var visited: BooleanArray
        var N = 0

        fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
            var answer: Int = 0
            N = n
            board = Array(n+1) { IntArray(n+1) {-1} }
            fares.forEach { (c, d, f) ->
                board[c][d] = f
                board[d][c] = f
            }
            dp = Array(n+1) { IntArray(3) {Int.MAX_VALUE} }

            find(0, s)
            find(1, a)
            find(2, b)

            return dp.minOf { it.sum() }
        }

        fun find(target: Int, n: Int) {
            val queue = PriorityQueue<Pair<Int, Int>> { o1, o2 ->
                if (o1.second > o2.second) 1
                else if (o1.second == o2.second) 0
                else -1
            }

            queue.add(n to 0)
            visited = BooleanArray(N+1) {false}

            while (queue.isNotEmpty()) {
                val (temp, dis) = queue.poll()

                if (visited[temp]) continue

                visited[temp] = true
                dp[temp][target] = dis

                for (i in 1 .. N) {
                    if (board[temp][i] != -1 && !visited[i]) {
                        queue.add(i to dis + board[temp][i])
                    }
                }
            }
        }
    }
}