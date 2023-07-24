package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b17386 {
    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (x1, y1, x2, y2) = readLine().split(" ").map { it.toLong() }
        val (x3, y3, x4, y4) = readLine().split(" ").map { it.toLong() }
        var a = x1 to y1
        var b = x2 to y2
        var c = x3 to y3
        var d = x4 to y4
        val ab = ccw(a, b, c)*ccw(a, b, d)
        val cd = ccw(c, d, a)*ccw(c, d, b)
        val result =
            if (ab == 0L && cd == 0L) {
                if (comparator(b, a)) {
                    val temp = a.copy()
                    a = b.copy()
                    b = temp.copy()
                }
                if (comparator(d, c)) {
                    val temp = d.copy()
                    d = c.copy()
                    c = temp.copy()
                }
                comparator(c, b) && comparator(a, d)
            }
            else ab <= 0 && cd <= 0
        println(if (result) 1 else 0)
    }

    fun ccw(a: Pair<Long, Long>, b: Pair<Long, Long>, c: Pair<Long, Long>): Long {
        val op = (a.first*b.second + b.first*c.second + c.first*a.second) - (a.second*b.first + a.first*c.second + c.first*b.second)
        return if (op > 0) 1
        else if (op < 0) -1
        else 0
    }

    fun comparator(a: Pair<Long, Long>, b: Pair<Long, Long>): Boolean {
        return if (a.first == b.first) a.second <= b.second
        else a.first <= b.first
    }
}