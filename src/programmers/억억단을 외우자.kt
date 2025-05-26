package programmers

import kotlin.math.sqrt

class `억억단을 외우자` {
//    class Solution {
//
//        private lateinit var tree: Array<Pair<Int, Int>>
//
//        fun solution(e: Int, starts: IntArray): IntArray {
//            tree = Array(e * 4) { 0 to 0 }
//
//            init(1, 1, e)
//
//            return starts.map { s -> tree[find(1, s, e, 1, e)].first }.toIntArray()
//        }
//
//        fun find(n: Int, l: Int, r: Int, start: Int, end: Int): Int {
//            if (l > end || r < start) return 0
//            if (l <= start && r >= end) return n
//
//            val mid = (start + end) / 2
//            val left = find(n * 2, l, r, start, mid)
//            val right = find(n * 2 + 1, l, r, mid + 1, end)
//
//            return if (tree[left].second >= tree[right].second) left
//            else right
//        }
//
//        fun init(n: Int, start: Int, end: Int) {
//            if (start == end) {
//                tree[n] = start to count(start)
//                return
//            }
//
//            val mid = (start + end) / 2
//            val left = n * 2
//            val right = left + 1
//
//            init(n * 2, start, mid)
//            init(n * 2 + 1, mid + 1, end)
//
//            tree[n] = if (tree[left].second >= tree[right].second) tree[left]
//            else tree[right]
//        }
//
//        fun count(n: Int): Int {
//            if(n == 1) return 1
//
//            val max = sqrt(n.toDouble())
//            var count = 0
//
//            for (i in 1 until max.toInt() + 1) {
//                if (n % i == 0) count++
//            }
//
//            count *= 2
//
//            if (max.toInt() * max.toInt() == n) count -= 1
//
//            return count
//        }
//    }

    class Solution {
        fun solution(e: Int, starts: IntArray): IntArray {
            val count = IntArray(e + 1) { 0 }

            for (i in 1..e) {
                for (j in i .. e step i) {
                    count[j]++
                }
            }

            val dp = IntArray(e + 1) { 0 }
            var maxIndex = e

            for (i in e downTo 1) {
                if (count[i] >= count[maxIndex]) maxIndex = i

                dp[i] = maxIndex
            }

            return starts.map { s -> dp[s] }.toIntArray()
        }
    }
}