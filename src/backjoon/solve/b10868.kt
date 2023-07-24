package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

class b10868 {
    lateinit var arr: IntArray
    lateinit var tree: IntArray

    fun main() = BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        val input = reader.readLine()!!.split(" ").map { it.toInt() }
        val n = input[0]
        val m = input[1]
        val sb = StringBuilder()
        arr = IntArray(n) {reader.readLine().toInt()}
        tree = IntArray(4 * n) {Int.MAX_VALUE}
        init(0, n-1, 1)
        for (i in 0 until m) {
            val (a, b) = reader.readLine()!!.split(" ").map { it.toInt() }
            sb.append(findMin(0, n-1, 1, a-1, b-1)).append("\n")
        }
        println(sb)
    }

    fun init(s: Int, e: Int, n: Int): Int {
        if (s == e) tree[n] = arr[s]
        else {
            val m = (s + e) / 2
            tree[n] = min(init(s, m, n shl 1), init(m+1, e, (n shl 1) + 1))
        }
        return tree[n]
    }

    fun findMin(s: Int, e: Int, n: Int, l: Int, r: Int): Int {
        if (l > e || r < s) return Int.MAX_VALUE
        if (l <= s && e <= r) return tree[n]
        val m = (s + e) / 2
        return min(findMin(s, m, n shl 1, l, r), findMin(m+1, e, (n shl 1) + 1, l, r))
    }
}