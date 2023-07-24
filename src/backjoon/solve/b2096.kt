package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2096 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val N = readLine().toInt()
        val maxDp = readLine().split(" ").map { it.toInt() }.toIntArray()
        val minDp = maxDp.copyOf()
        var maxDpPrev = maxDp.copyOf()
        var minDpPrev = maxDp.copyOf()
        repeat(N - 1) {
            val temp = readLine().split(" ").map { it.toInt() }
            for (i in 0 until 3) {
                val left = if (i == 0) 0 else i - 1
                val right = if (i == 2) i else i + 1
                maxDp[i] = temp[i] + maxOf(maxDpPrev[left], maxDpPrev[i], maxDpPrev[right])
                minDp[i] = temp[i] + minOf(minDpPrev[left], minDpPrev[i], minDpPrev[right])
            }
            maxDpPrev = maxDp.copyOf()
            minDpPrev = minDp.copyOf()
        }

        println("${maxDp.max()} ${minDp.min()}")

    }
}