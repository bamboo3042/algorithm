package programmers

import kotlin.math.max

class 도둑질 {

    class Solution {
        fun solution(money: IntArray): Int {
            if (money.size == 3) {
                return max(max(money[0].toDouble(), money[1].toDouble()), money[2].toDouble())
                    .toInt()
            }
            val nf = IntArray(money.size)
            val f = IntArray(money.size)
            nf[0] = 0
            nf[1] = money[1]
            f[0] = money[0]
            f[1] = max(money[0].toDouble(), money[1].toDouble()).toInt()
            for (i in 2 until money.size - 1) {
                nf[i] = max(nf[i - 1].toDouble(), (nf[i - 2] + money[i]).toDouble()).toInt()
                f[i] = max(f[i - 1].toDouble(), (f[i - 2] + money[i]).toDouble()).toInt()
            }
            nf[money.size - 1] =
                max(nf[money.size - 2].toDouble(), (nf[money.size - 3] + money[money.size - 1]).toDouble())
                    .toInt()
            f[money.size - 1] = f[money.size - 2]
            return max(nf[money.size - 1].toDouble(), f[money.size - 1].toDouble()).toInt()
        }
    }
}