package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b1654 {
    var N = 0
    var K = 0
    var min = 1L
    var max = Long.MAX_VALUE
    var answer = 1
    lateinit var cable: IntArray

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ").map { it.toInt() }
        K = input[0]
        N = input[1]
        cable = IntArray(K) { readLine().toInt() }
        max = cable.max().toLong()
        while (min <= max) {
            val mid = (max + min) / 2
            val temp = cable.sumOf { it / mid }
            if (temp >= N) {
                answer = mid.toInt()
                min = mid + 1
            }
            else max = mid - 1
        }
        println(answer)
    }
}