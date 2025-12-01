class Solution {
    fun solution(info: Array<IntArray>, n: Int, m: Int): Int {
        val sorted = info.sortedArrayWith(compareBy({ -(it[0] / it[1]) }, { -it[0] }, { it[1] }))
        val sum = intArrayOf(0, 0)

        sorted.forEach { (a, b) ->
            if (b + sum[1] < m) sum[1] = sum[1] + b
            else sum[0] = sum[0] + a
        }

        return if (sum[0] >= n) -1
        else sum[0]
    }
}