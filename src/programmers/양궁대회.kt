package programmers

class 양궁대회 {
    class Solution {
        private var RYAN = intArrayOf()
        private var diff = 0
        private var APEACH = 0

        fun solution(n: Int, info: IntArray): IntArray {
            info.forEachIndexed { index, i -> if (i != 0) APEACH += 10 - index }

            dfs(IntArray(info.size) { 0 }, info, 0, n, 0)

            return if (RYAN.isEmpty()) intArrayOf(-1) else RYAN
        }

        private fun dfs(ryan: IntArray, apeach: IntArray, p: Int, c: Int, sum: Int) {
            if (c < 0) return
            if (p == 11) {
                if (c != 0) return

                val tempDiff = sum - APEACH

                if (tempDiff > diff) {
                    RYAN = ryan.copyOf()
                    diff = tempDiff

                    return
                }

                if (tempDiff == diff) {
                    RYAN = priorityCheck(ryan, RYAN).copyOf()

                    return
                }

                return
            }

            val temp = minOf(apeach[p] + 1, c)

            for (i in temp downTo 0) {
                val newSum = sum + if (i > apeach[p] && apeach[p] != 0) (10 - p) * 2
                else if (i > apeach[p]) (10 - p)
                else 0

                ryan[p] = i
                dfs(ryan, apeach, p + 1, c - i, newSum)
                ryan[p] = 0
            }
        }

        private fun priorityCheck(newArr: IntArray, prevArr: IntArray): IntArray {
            if (prevArr.isEmpty()) return prevArr

            for (i in newArr.indices.reversed()) {
                if (newArr[i] > prevArr[i]) return newArr
                else if (prevArr[i] > newArr[i]) return prevArr
            }

            return prevArr
        }
    }
}