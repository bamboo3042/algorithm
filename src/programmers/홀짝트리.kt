package programmers

class 홀짝트리 {
    class Solution {
        private val map = mutableMapOf<Int, MutableList<Int>>()
        private val visited = mutableSetOf<Int>()

        fun solution(nodes: IntArray, edges: Array<IntArray>): IntArray {
            val answer = intArrayOf(0, 0)
            initMap(nodes, edges)

            map.forEach { k, v ->
                if (visited.contains(k)) return@forEach

                val queue = ArrayDeque<Int>()
                var isParity = true
                var isRevParity = true
                var parityRootCount = 0
                var revParityRootCount = 0

                visited.add(k)
                queue.add(k)

                while (queue.isNotEmpty()) {
                    val temp = queue.removeFirst()
                    val rootChildCount = map[temp]!!.size
                    val notRootChildCount = map[temp]!!.size - 1

                    visited.add(temp)

                    if ((temp + notRootChildCount) % 2 == 0) {
                        if (revParityRootCount == 0) {
                            revParityRootCount++
                            if ((temp + rootChildCount) % 2 == 0) isRevParity = false
                        }
                        else isRevParity = false
                    }
                    else {
                        if (parityRootCount == 0) {
                            parityRootCount++
                            if ((temp + rootChildCount) % 2 == 1) isParity = false
                        }
                        else isParity = false
                    }

                    map[temp]!!.forEach { next -> if (next !in visited) queue.add(next) }
                }

                if (isParity && parityRootCount == 1) answer[0]++
                if (isRevParity && revParityRootCount == 1) answer[1]++
            }

            return answer
        }

        fun initMap(nodes: IntArray, edges: Array<IntArray>) {
            nodes.forEach { node -> map[node] = mutableListOf() }

            edges.forEach { (n, m) ->
                map[n]!!.add(m)
                map[m]!!.add(n)
            }
        }
    }
}