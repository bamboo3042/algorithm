package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b1717 {
    var N = 0
    var M = 0
    lateinit var group: IntArray
    lateinit var rank: IntArray

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ").map { it.toInt() }
        N = input[0]
        M = input[1]
        group = IntArray(N+1) {it}
        rank = IntArray(N+1) {0}
        repeat(M) {
            val (x, a, b) = readLine().split(" ").map { it.toInt() }
            if (x == 0) unionRoot(a, b)
            else println(if (findRoot(a) == findRoot(b)) "YES" else "NO")
        }
    }

    fun findRoot(n: Int): Int =
        if (n == group[n]) n
        else {
            group[n] = findRoot(group[n])
            group[n]
        }

    fun unionRoot(a: Int, b: Int) {
        val x = findRoot(a)
        val y = findRoot(b)

        if (x != y) {
            if (rank[x] < rank[y]) group[x] = y
            else group[y] = x

            if (rank[x] == rank[y]) rank[y]++
        }
    }
}