package programmers

import java.util.PriorityQueue
import kotlin.math.sign

class `지형 편집` {
    class Solution {
//        fun solution(land: Array<IntArray>, P: Int, Q: Int): Long {
//            val heightMap = sortedMapOf<Int, Int>()
//            var totalCount = 0
//            var requiredCount = 0
//            val areaSize = land.size * land.size
//            var holeCount = 0
//            var prevHeight = 0
//            var holePrice = 0
//
//            land.forEach { list ->
//                list.forEach {
//                    heightMap[it] = (heightMap[it] ?: 0) + 1
//                    totalCount += it
//                }
//            }
//
//            var answer = totalCount.toLong() * Q
//
//            heightMap.forEach { (height, c) ->
//                totalCount -= c + (areaSize - holeCount) * (height - prevHeight)
//                holeCount += c
//                requiredCount += (holeCount + c)
//
//                val removePrice = totalCount * Q
//                val addPrice = (height - prevHeight)
//                val sum = (totalCount.toLong() * Q) + ((requiredCount + areaSize - c) * P)
//
//                holeCount += c
//
//                answer = minOf(answer, sum)
//            }
//
//            return answer
//        }

        fun solution(land: Array<IntArray>, P: Int, Q: Int): Long {
            val pq = PriorityQueue<Int>()
            var removePrice = 0L
            land.map { list ->
                list.map {
                    pq.add(it)
                    removePrice += it
                }
            }

            removePrice *= Q
            var addPrice = 0L
            var answer = removePrice
            var index = 0
            var prevHeight = 0

            while (pq.isNotEmpty()) {
                val height = pq.poll()
                val diff = (height - prevHeight).toLong()

                addPrice += diff * index * P
                removePrice -= diff * (pq.size + 1) * Q

                index++
                prevHeight = height

                answer = minOf(answer, addPrice + removePrice)
            }

            return answer
        }
    }
}