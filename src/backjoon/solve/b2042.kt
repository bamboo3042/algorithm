package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2042 {
    var N = 0
    var M = 0
    var K = 0
    lateinit var arr: LongArray
    lateinit var tree: LongArray

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ").map { it.toInt() }
        N = input[0]
        M = input[1]
        K = input[2]
        arr = LongArray(N) {readLine().toLong()}
        tree = LongArray(4 * N) {0}
        init(0, N-1L, 1)
        repeat(M + K) {
            val (a, b, c) = readLine().split(" ").map { it.toLong() }
            if (a == 1L) {
                update(0, N-1L, 1, b-1, c - arr[(b-1L).toInt()])
                arr[(b-1L).toInt()] = c
            }
            else println(sum(0, N-1L, 1, b-1L, c-1L))
        }
    }

    fun init(s: Long, e: Long, n: Long): Long {
        if (s == e) tree[n.toInt()] = arr[s.toInt()]
        else {
            val m = (s + e) / 2
            tree[n.toInt()] = init(s, m, n*2) + init(m+1, e, n*2+1)
        }
        return tree[n.toInt()]
    }

    fun sum(s: Long, e: Long, n: Long, l: Long, r: Long): Long {
        if (l > e || r < s) return 0
        if (l <= s && e <= r) return tree[n.toInt()]
        val m = (s + e) / 2
        return sum(s, m, n*2, l, r) + sum(m+1, e, n*2+1, l, r)
    }

    fun update(s: Long, e: Long, n: Long, i: Long, d: Long) {
        if (i < s || i > e) return
        tree[n.toInt()] += d
        if (s == e) return
        val m = (s + e) / 2
        update(s, m, n*2, i, d)
        update(m+1, e, n*2+1, i, d)
    }
}