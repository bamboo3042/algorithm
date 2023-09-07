package programmers

class 최고의_집합 {
    class Solution {
        fun solution(n: Int, s: Int): IntArray {
            val m = s/n
            if (m < 1) return intArrayOf(-1)

            val answer = IntArray(n) {m}
            var total = m * n
            var r = n-1
            while (total < s) {
                answer[r--]++
                total++
            }

            return answer
        }
    }
}