package programmers

import java.util.PriorityQueue

class 튜플 {
    class Solution {
        fun solution(s: String): IntArray {
            val answer = mutableListOf<Int>()
            val set = mutableSetOf<Int>()
            val pq = stringToPq(s)

            while (pq.isNotEmpty()) {
                val temp = pq.poll()

                for (i in temp) {
                    if (!set.contains(i)) {
                        answer.add(i)
                        set.add(i)
                        break
                    }
                }
            }

            return answer.toIntArray()
        }

        fun stringToPq(s: String): PriorityQueue<Set<Int>> {
            var n = 0
            val list = mutableSetOf<Int>()
            val pq = PriorityQueue<Set<Int>>() { o1, o2 -> o1.size - o2.size }

            s.forEach { c ->
                if (c == '{') return@forEach
                if (c == '}') {
                    if (n == 0) return@forEach
                    list.add(n)
                    pq.add(list.toSet())
                    list.clear()
                    n = 0
                    return@forEach
                }
                if (c == ',') {
                    if (n == 0) return@forEach
                    list.add(n)
                    n = 0
                    return@forEach
                }

                n = n * 10 + c.code - '0'.code
            }

            return pq
        }
    }
}