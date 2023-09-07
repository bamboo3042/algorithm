package programmers

class 기능개발 {
    class Solution {
        fun solution(progresses: IntArray, speeds: IntArray): IntArray {
            val answer = mutableListOf<Int>()
            var time = 0
            progresses.forEachIndexed { index, i ->
                if (i + (time * speeds[index]) >= 100) {
                    answer[answer.lastIndex] += 1
                }
                else {
                    val d = (100 - i) / speeds[index]
                    val r = if ((100 - i) % speeds[index] == 0) 0 else 1
                    time = d + r
                    answer.add(1)
                }
            }
            return answer.toIntArray()
        }
    }
}