package programmers

import java.util.LinkedList
import java.util.Queue

class 다리를_지나는_트럭 {
    class Solution {
        fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
            var answer = 0
            var p = 0
            var sum = 0
            val queue:Queue<Int> = LinkedList()
            repeat(bridge_length) {queue.add(0)}
            while (queue.isNotEmpty()) {
                answer++

                val temp = queue.poll()
                sum -= temp

                if (p >= truck_weights.size) continue
                if (sum + truck_weights[p] <= weight) {
                    queue.add(truck_weights[p])
                    sum += truck_weights[p]
                    p++
                }
                else {
                    queue.add(0)
                }
            }
            return answer
        }
    }
}