package programmers

import java.util.PriorityQueue

class `미로 탈출` {
    class Solution {
        fun solution(n: Int, start: Int, end: Int, roads: Array<IntArray>, traps: IntArray): Int {
            var answer: Int = 0
            val board = Array(n+1) { IntArray(n+1) { Int.MAX_VALUE } }

            roads.forEach { (a, b, c) -> board[a][b] = board[a][b].coerceAtMost(c) }

            val pq = PriorityQueue<Triple<Int, Int, Int>> { o1, o2 -> o1.first - o2.first }
            val visited = BooleanArray(n+1) { false }
            pq.add(Triple(0, start, 0))

            while (pq.isNotEmpty()) {
                val (d, t, tv) = pq.poll()

                if (t == end) {
                    answer = d
                    break
                }

                val tTrapIndex = traps.indexOf(t)

                visited[t] = true

                board[t].forEachIndexed { n, nd ->
                    if (nd == Int.MAX_VALUE && board[n][t] == Int.MAX_VALUE) return@forEachIndexed

                    val nTrapIndex = traps.indexOf(n)
                    if (visited[n] && tTrapIndex == -1 && nTrapIndex == -1) return@forEachIndexed

                    var trapCount = 0

                    if (tTrapIndex != -1 && 1.shl(tTrapIndex) and tv != 0) trapCount++
                    if (nTrapIndex != -1 && 1.shl(nTrapIndex) and tv != 0) trapCount++

                    val nextD = if (trapCount % 2 == 0) nd
                    else board[n][t]

                    if(nextD == Int.MAX_VALUE) return@forEachIndexed

                    val nv = if (nTrapIndex == -1) tv
                    else tv xor 1.shl(nTrapIndex)

                    pq.add(Triple(d + nextD, n, nv))
                }
            }

            return answer
        }
    }
}