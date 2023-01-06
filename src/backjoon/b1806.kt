package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b1806 {
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (N, S) = readLine().split(" ").map { it.toInt() }
        val arr = readLine().split(" ").map { it.toInt() }
        var answer = Int.MAX_VALUE
        var sum = arr[0]
        var s = 0
        var e = 0
        while (true) {
            if (e == N-1) break
            if (sum < S) sum += arr[++e]
            else {
                answer = answer.coerceAtMost(e - s + 1)
                sum -= arr[s++]
            }
        }
        while (sum >= S) {
            answer = answer.coerceAtMost(e - s + 1)
            sum -= arr[s++]
        }
        println(if (answer == Int.MAX_VALUE) 0 else answer)
    }
}