package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b2243 {
    var N = 0
    var arr = IntArray(10000001) {0}
    var tree = IntArray(1000002) {0}

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val sb = StringBuilder()
        N = readLine().toInt()
        repeat(N) {
            val input = readLine().split(" ").map { it.toInt() }
            if (input[0] == 1) {
                val p = find(0, 1000000, input[1])
                update(p, -1)
                sb.append(p).appendLine()
            }
            else {
                update(input[1], input[2])
            }
        }
        println(sb)
    }

    fun update(_i: Int, v: Int) {
        var i = _i
        while (i < tree.size) {
            tree[i] += v
            i += i and -i
        }
    }

    fun sum(_i: Int): Int {
        var result = 0
        var i = _i
        while (i > 0) {
            result += tree[i]
            i -= i and -i
        }
        return result
    }

    fun find(l: Int, r: Int, n: Int): Int {
        if (l >= r) return l
        val mid = (l + r) / 2
        val ls = sum(mid)
        return if (n <= ls) find(l, mid, n) else find(mid+1, r, n)
    }
}