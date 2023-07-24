package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b10830 {
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (N, B) = readLine().split(" ").map { it.toLong() }
        var board = Array(N.toInt()) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        power(board, B).forEach { l ->
            println(l.joinToString(" "))
        }
    }

    fun power(_x: Array<IntArray>, _p: Long): Array<IntArray> {
        var result = Array(_x.size) { i -> IntArray(_x.size) { j -> if (i == j) 1 else 0 } }
        var x = _x
        var p = _p
        while (p > 0) {
            if (p and 1 == 1.toLong()) result = mul(result, x)

            x = mul(x, x)
            p = p.shr(1)
        }
        return result
    }

    fun mul(a: Array<IntArray>, b: Array<IntArray>): Array<IntArray> {
        return Array(a.size) { i ->
            IntArray(a.size) { j ->
                b.map { l -> l[j] }.let { l ->
                    a[i].mapIndexed { index, i -> i * l[index] % 1000 }.sum() % 1000
                }
            }
        }
    }
}