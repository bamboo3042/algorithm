package backjoon

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.absoluteValue
import kotlin.math.sqrt

fun main(args : Array<String>) {
    println(Solution().solution(
        intArrayOf(-5, 0, 2, 1, 2),
        arrayOf(
            intArrayOf(0, 1),
            intArrayOf(3, 4),
            intArrayOf(2, 3),
            intArrayOf(0, 3),
        )
    ))
}

class Solution {
    lateinit var road: List<MutableSet<Int>>
    lateinit var count: IntArray
    lateinit var sumArray: LongArray
    val pq = PriorityQueue<Pair<Int, Int>> { o1, o2 -> o1.second - o2.second }

    fun solution(a: IntArray, edges: Array<IntArray>): Long {
        road = List(a.size) { mutableSetOf() }
        count = IntArray(a.size) { 0 }
        sumArray = LongArray(a.size) { a[it].toLong() }

        edges.forEach { (a, b) ->
            road[a].add(b)
            road[b].add(a)
            count[a]++
            count[b]++
        }

        count.forEachIndexed { index, i ->
            pq.offer(index to i)
        }

        return calculate(sumArray)
    }

    private fun calculate(a: LongArray): Long {
        var answer = 0L

        while (pq.isNotEmpty()) {
            val (index, tempCount) = pq.poll()

            if (tempCount > 1) {
                pq.offer(index to count[index])
                continue
            }

            val next = road[index].firstOrNull()

            if (next != null) {
                road[index].clear()
                road[next].remove(index)

                answer += a[index].absoluteValue
                a[next] += a[index]
                count[next]--
            }
            else {
                return if (a[index] == 0L) answer
                else -1
            }
        }

        return answer
    }
}