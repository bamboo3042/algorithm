package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b2110 {
    var N = 0
    var C = 0
    var min = 1
    var max = Int.MAX_VALUE
    lateinit var arr: IntArray

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ").map { it.toInt() }
        N = input[0]
        C = input[1]
        arr = IntArray(N) { readLine().toInt() }.sortedArray()
        max = arr.last() - arr.first()
        var answer = 0
        while (min <= max) {
            val mid = (min + max) / 2
            var prev = arr[0]
            var count = 1
            for (i in 1 until N) {
                if (arr[i] - prev >= mid) {
                    prev = arr[i]
                    count++
                }
            }
            if (count >= C) {
                answer = answer.coerceAtLeast(mid)
                min = mid + 1
            }
            else max = mid - 1
        }
        println(answer)
    }
}