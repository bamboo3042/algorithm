package programmers

class 금과_은_운반하기 {
    class Solution {
        fun solution(a: Int, b: Int, g: IntArray, s: IntArray, w: IntArray, t: IntArray): Long {
            var left = 0L
            var right = 400000000000000L
            var result = 400000000000000L

            while(left <= right) {
                val mid = (left + right) / 2
                var total = 0L
                var gold = 0L
                var silver = 0L

                t.forEachIndexed { index, time ->
                    val c = (mid / time).let { if (it % 2 == 0L) it / 2 else it / 2 + 1}

                    val temp = minOf(c * w[index], g[index].toLong() + s[index])
                    total += temp
                    gold += minOf(g[index].toLong(), temp)
                    silver += minOf(s[index].toLong(), temp)
                }

                if (total >= a+b && gold >= a && silver >= b) {
                    right = mid - 1
                    result = mid
                }
                else left = mid + 1
            }

            return result
        }
    }
}