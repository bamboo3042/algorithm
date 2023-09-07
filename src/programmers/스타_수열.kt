package programmers

class 스타_수열 {
    class Solution {
        fun solution(_a: IntArray): Int {
            val a = intArrayOf(_a.first()) + _a + intArrayOf(_a.last())
            val count = IntArray(a.size) {0}
            val checked = IntArray(a.size) {-1}
            if (a.size < 2) return 0
            for (i in 1 until a.size - 1) {
                if (a[i] != a[i-1] && a[i] != checked[i-1]) {
                    count[a[i]]++
                }
                else if (a[i] != a[i+1]) {
                    count[a[i]]++
                    checked[i+1] = a[i]
                }
            }
            return count.maxOf { it } * 2
        }
    }
}