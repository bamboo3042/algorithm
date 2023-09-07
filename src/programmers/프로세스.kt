package programmers

class 프로세스 {
    class Solution {
        fun solution(priorities: IntArray, location: Int): Int {
            var answer = 0
            var p = 0
            var t = location
            var max = priorities.maxOf { it }
            val list = priorities.toMutableList()

            while (true) {
                if (p >= list.size) p = 0
                if (list[p] == max) {
                    answer++
                    list.removeAt(p)

                    if (list.isEmpty()) break

                    max = list.maxOf { it }

                    if (p == t) break
                    else if (p < t) t--
                }
                else p++
            }

            return answer
        }
    }
}