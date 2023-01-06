package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b2836 {
    var N = 0
    var M = 0
    var list = mutableListOf<Pair<Int, Int>>()

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ").map { it.toInt() }
        N = input[0]
        M = input[1]
        repeat(N) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            if (y < x) list.add(y to x)
        }
        list = list.sortedWith(compareBy({it.first}, {it.second})).toMutableList()
        var last = list.first().second
        var count: Long = list.first().let { it.second - it.first }.toLong()
        for (i in 1 until list.size) {
            if (last >= list[i].first) {
                if (last < list[i].second) {
                    count += list[i].second - last
                    last = list[i].second
                }
            }
            else {
                count += list[i].second - list[i].first
                last = list[i].second
            }
        }

        println(count*2 + M)
    }
}