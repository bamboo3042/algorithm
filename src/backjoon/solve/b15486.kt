package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b15486 {
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        val price = Array(n + 2) {0}
        val inputs = (1 .. n).map { readLine().split(" ").map { it.toInt() } }
        var day = n
        for((t, p) in inputs.reversed()) {
            if(day + t > n + 1) price[day] = price[day + 1]
            else price[day] = price[day+1].coerceAtLeast(p + price[day + t])
            day--
        }

        println(price[1])
    }
}