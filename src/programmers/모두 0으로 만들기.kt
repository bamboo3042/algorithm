package programmers

import kotlin.collections.ArrayDeque
import kotlin.math.absoluteValue

class `모두 0으로 만들기` {
    class Solution {
        lateinit var road: MutableMap<Int, MutableSet<Int>>
        lateinit var sumArray: LongArray
        lateinit var visited: BooleanArray
        val queue = ArrayDeque<Int>()

        fun solution(a: IntArray, edges: Array<IntArray>): Long {
            road = mutableMapOf()
            sumArray = LongArray(a.size) { a[it].toLong() }
            visited = BooleanArray(a.size) { false }

            edges.forEach { (a, b) ->
                road.computeIfAbsent(a) { mutableSetOf() }.add(b)
                road.computeIfAbsent(b) { mutableSetOf() }.add(a)
            }

            road.forEach { (a, b) -> if (b.size == 1) queue.addLast(a) }

            return calculate(sumArray)
        }

        private fun calculate(a: LongArray): Long {
            var answer = 0L

            while (queue.isNotEmpty()) {
                val index = queue.removeFirst()

                val next = road[index]!!.firstOrNull()

                if (next != null) {
                    road[next]!!.remove(index)

                    answer += a[index].absoluteValue
                    a[next] += a[index]

                    if (road[next]!!.size == 1 && !visited[next]) {
                        queue.addLast(next)
                        visited[next] = true
                    }
                }
                else {
                    return if (a[index] == 0L) answer
                    else -1
                }
            }

            return answer
        }
    }
}