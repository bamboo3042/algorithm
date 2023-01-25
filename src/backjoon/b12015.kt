package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b12015 {
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val arr = readLine().split(" ").map { it.toInt() }.toIntArray()
        val d = IntArray(n) {0}
        var length = 1
        d[0] = arr[0]
        for (i in 1 until n) {
            if (arr[i] > d[length - 1]) d[++length - 1] = arr[i]
            else {
                var start = 0
                var end = length
                while (start < end) {
                    val mid = (start + end) / 2

                    if (d[mid] < arr[i]) start = mid + 1
                    else end = mid
                }
                d[start] = arr[i]
            }
        }
        println(length)
    }
}