package programmers

class `두 큐 합 같게 만들기` {
    class Solution {
        fun solution(queue1: IntArray, queue2: IntArray): Int {
            var halfSum = 0L
            var sum = 0L

            queue1.forEach {
                halfSum += it
                sum += it
            }

            queue2.forEach { halfSum += it }

            halfSum /= 2

            var point1 = 0
            var point2 = 0
            val maxMove = queue1.size + queue2.size + maxOf(queue1.size, queue2.size)

            while (sum != halfSum && point1 + point2 <= maxMove) {
                if (sum > halfSum) {
                    sum -= if (point1 >= queue1.size) queue2[point1 - queue1.size]
                    else queue1[point1]
                    point1++
                }
                else {
                    sum += if (point2 >= queue2.size) queue1[point2 - queue2.size]
                    else queue2[point2]
                    point2++
                }
            }

            return if (sum == halfSum) point1 + point2 else -1
        }
    }
}