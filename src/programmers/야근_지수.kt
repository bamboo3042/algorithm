package programmers

import java.util.Collections
import java.util.PriorityQueue

class 야근_지수 {

    class Solution {
        fun solution(n: Int, works: IntArray): Long {
            val queue = PriorityQueue<Int> (Collections.reverseOrder())

            works.forEach { queue.add(it) }

            repeat(n) {
                if (queue.isEmpty()) return 0

                val temp = queue.poll()
                if (temp != 1) queue.add(temp-1)
            }

            var answer = 0L

            while (queue.isNotEmpty()) {
                val temp = queue.poll()
                answer += temp * temp
            }

            return answer
        }
    }

}