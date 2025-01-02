package programmers

class `외벽 점검` {
    class Solution {
        var answer = Int.MAX_VALUE
        private lateinit var visited: BooleanArray

        fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
            val newWeak = weak.toMutableList()

            weak.forEach { newWeak.add(n + it) }

            visited = BooleanArray(newWeak.size) { false }

            dfs(n, dist.sortedArrayDescending(), 0, newWeak, 0, 0)

            return if (answer == Int.MAX_VALUE) -1 else answer
        }

        fun dfs(n: Int, dist: IntArray, di: Int, weak: List<Int>, visitedCount: Int, count: Int) {
            if (visitedCount == weak.size) {
                answer = minOf(answer, count)
                return
            }
            if (di == dist.size) return
            if (count >= answer) return

            for (i in 0 until weak.size / 2) {
                if (visited[i]) continue

                val start = weak[i]
                val list = mutableListOf<Int>()
                var vc = 0
                var wi = i

                repeat(dist[di] + 1) {
                    val next = start + it

                    if (next > weak[wi]) wi++

                    if (next == weak[wi] && !visited[wi]) {
                        list.add(wi)
                        visited[wi] = true
                        vc++
                    }

                    if (next >= n) {
                        val bwi = wi - (weak.size / 2)
                        if (!visited[bwi]) {
                            list.add(bwi)
                            visited[bwi] = true
                            vc++
                        }
                    }
                    else {
                        val nwi = wi + (weak.size / 2)
                        if (nwi < weak.size && !visited[nwi]) {
                            list.add(nwi)
                            visited[nwi] = true
                            vc++
                        }
                    }
                }

                dfs(n, dist, di + 1, weak, visitedCount + vc, count + 1)

                list.forEach { visited[it] = false }
            }
        }
    }
}