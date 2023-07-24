package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b11505 {
    var N = 0
    var M = 0
    var K = 0
    val MOD = 1000000007
    lateinit var tree: LongArray
    lateinit var arr: IntArray

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ").map { it.toInt() }
        N = input[0]
        M = input[1]
        K = input[2]
        tree = LongArray(N * 4) {0}
        arr = IntArray(N) { readLine().toInt() }
        init(0, N-1, 1)
        repeat(M + K) {
            val (a, b, c) = readLine().split(" ").map { it.toInt() }
            if (a == 1) {
                arr[b-1] = c
                update(0, N-1, 1, b-1, c)
            }
            else println(find(0, N-1, 1, b-1, c-1))
        }
    }

    fun init(s: Int, e: Int, n: Int): Long {
        if (s == e) tree[n] = arr[s].toLong()
        else {
            val m = (s + e) / 2
            tree[n] = (init(s, m, n shl 1) * init(m+1, e, (n shl 1) + 1)) % MOD
        }
        return tree[n]
    }

    fun update(s: Int, e: Int, n: Int, i: Int, d: Int): Long {
        if (i < s || i > e) return tree[n]
        if (s == e) tree[n] = d.toLong()
        else {
            val m = (s + e) / 2
            tree[n] = (update(s, m, n shl 1, i, d) * update(m+1, e, (n shl 1) + 1, i, d)) % MOD
        }
        return tree[n]
    }

    fun find(s: Int, e: Int, n: Int, l: Int, r: Int): Long {
        if (l > e || r < s) return 1
        if (l <= s && e <= r) return tree[n]
        val m = (s + e) / 2
        return (find(s, m, n shl 1, l, r) * find(m+1, e, (n shl 1) + 1, l, r)) % MOD
    }
}