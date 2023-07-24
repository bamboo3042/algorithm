package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b1300 {
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val N = readLine().toInt()
        val k = readLine().toInt()
        var min = 1
        var max = k
        while (min < max) {
            val mid = (min + max) / 2
            var cnt = 0L

            for (i in 1 .. N.coerceAtMost(mid)) cnt += N.coerceAtMost((mid / i))

            if (k <= cnt) max = mid
            else min = mid + 1
        }

        println(min)
    }
}