package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b17619 {
    var N = 0
    var Q = 0
    lateinit var logs: Array<Triple<Int, Int, Int>>
    lateinit var group: IntArray

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        val sb = StringBuilder()
        N = input[0].toInt()
        Q = input[1].toInt()
        group = IntArray(N) {it}
        logs = Array(N) {
            val (x, y, _) = readLine().split(" ").map { it.toInt() }
            Triple(x, y, it)
        }
        logs.sortWith { a, b ->
            if (a.first == b.first) a.second - b.second
            else a.first - b.first
        }
        var l = logs[0].second
        for (i in 1 until logs.size) {
            val (x, y, n) = logs[i]
            l = if (l >= x) {
                unionRoot(n, logs[i-1].third)
                l.coerceAtLeast(y)
            } else y
        }

        repeat(Q) {
            val (a, b) = readLine().split(" ").map { it.toInt()-1 }
            if (findRoot(a) == findRoot(b)) sb.append(1) else sb.append(0)
            sb.append("\n")
        }
        println(sb)
    }

    fun findRoot(n: Int): Int {
        if (group[n] != n) group[n] = findRoot(group[n])
        return group[n]
    }

    fun unionRoot(a: Int, b: Int) {
        val g1 = findRoot(a)
        val g2 = findRoot(b)
        if (g1 < g2) group[g2] = g1
        else group[g1] = g2
    }
}