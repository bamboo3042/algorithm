package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b2512 {
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val arr = readLine().split(" ").map { it.toInt() }.toIntArray()
        var m = readLine().toInt()
        var answer = 0
        var min = 0
        var max = arr.sum()

        if (max <= m) println(arr.max())
        else {
            while (min < max) {
                val mid = (min + max) / 2
                val temp = arr.sumOf { if (it > mid) mid else it}

                if (temp <= m) {
                    answer = mid
                    min = mid + 1
                }
                else max = mid
            }

            println(answer)
        }
    }
}