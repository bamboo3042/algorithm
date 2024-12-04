package programmers

class `도넛과 막대 그래프` {
    class Solution {
        fun solution(edges: Array<IntArray>): IntArray {
            val answer = IntArray(4) { 0 }

            val next = mutableMapOf<Int, MutableList<Int>>()
            val checkPoint = mutableSetOf<Int>()

            edges.forEach { (a, b) ->
                next.computeIfAbsent(a) { mutableListOf() }.add(b)

                checkPoint.add(b)
            }

            answer[0] = next.keys.first { !checkPoint.contains(it)  && next[it]?.size != 1 }

            next[answer[0]]?.forEach { start ->
                var temp: Int? = start

                do {
                    val size = next[temp]?.size ?: 0

                    if (size == 0) {
                        answer[2]++
                        return@forEach
                    }
                    else if (size == 2) {
                        answer[3]++
                        return@forEach
                    }
                    temp = next[temp]?.first()
                } while (temp != start)

                answer[1]++
            }

            return answer
        }
    }
}