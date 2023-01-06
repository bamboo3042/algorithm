package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b12970 {
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (N, K) = readLine().split(" ").map { it.toInt() }
        if (N % 2 == 1 && N / 2 * (N / 2 + 1) < K) println(-1)
        else if (N % 2 == 0 && N / 2 * N / 2 < K) println(-1)
        else {
            val sb = StringBuilder()
            var aCount = 0
            var result = 0
            var size = 0
            repeat(N) {
                if (result == K) sb.append("A")
                else if (result + (aCount * (N - size)) == K) {
                    sb.append("B")
                    result += aCount
                }
                else if (result + ((aCount + 1) * (N - size - 1)) <= K) {
                    sb.append("A")
                    aCount += 1
                }
                else {
                    sb.append("B")
                    result += aCount
                }
                size += 1
            }
            println(sb)
        }
    }
}