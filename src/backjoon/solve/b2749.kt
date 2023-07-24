package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2749 {
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        var n = readLine().toLong()
        if (n < 2) println(n)
        else {
            var a = 0
            var b = 1
            n %= 1500000
            while (n-- > 0) {
                val temp = (a + b) % 1000000
                a = b
                b = temp
            }
            println(a)
        }
    }
}