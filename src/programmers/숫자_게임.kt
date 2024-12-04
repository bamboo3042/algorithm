package programmers

class 숫자_게임 {
    class Solution {
        fun solution(A: IntArray, B: IntArray): Int {
            var answer = 0

            val b = B.sorted().toMutableList()
            A.forEach { a ->
                val i = searchIndex(b, a)
                if (b.size == i || b[i] <= a) b.removeFirst()
                else {
                    b.removeAt(i)
                    answer++
                }
            }
            return answer
        }

        fun searchIndex(b: MutableList<Int>, n: Int): Int {
            var s = 0
            var e = b.size-1
            var m = 0
            while (s <= 2) {
                m = (s + e) / 2

                if (b[m] < n) s = m+1
                else e = m+1
            }

            return s
        }
    }
}