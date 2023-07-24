package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.HashSet
import kotlin.math.sqrt

class b28138 {
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (N, R) = readLine().split(" ").map { it.toLong() }
        val n = N - R
        val set = HashSet<Long>()
        val end = sqrt(n.toDouble()).toLong()

        for (i in 1  .. end) {
            if (n % i == 0L) {
                if (i > R) set.add(i)
                if (n/i > R) set.add(n/i)
            }
        }
        println(set.sum())
    }
}