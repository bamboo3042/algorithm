package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b1275 {
    var N = 0
    var Q = 0
    lateinit var tree: LongArray
    lateinit var arr: LongArray

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ").map { it.toInt() }
        val sb = StringBuilder()
        N = input[0]
        Q = input[1]
        arr = readLine().split(" ").map { it.toLong() }.toLongArray()
        tree = LongArray(N+1) {0}
        init()
        repeat(Q) {
            val (x, y, a, b) = readLine().split(" ").map { it.toLong() }
            val (l, r) = if (x < y) x to y else y to x
            sb.append(sum(r) - sum(l-1)).appendLine()
            update(a, b - arr[(a-1).toInt()])
            arr[(a-1).toInt()] = b
        }
        println(sb)
    }

    fun init() {
        for (i in arr.indices) update(i+1L, arr[i])
    }

    fun update(_i: Long, v: Long) {
        var i = _i
        while (i < tree.size) {
            tree[i.toInt()] += v
            i += i and -i
        }
    }

    fun sum(_i: Long): Long {
        var result = 0L
        var i = _i
        while (i > 0) {
            result += tree[i.toInt()]
            i -= i and -i
        }
        return result
    }
}