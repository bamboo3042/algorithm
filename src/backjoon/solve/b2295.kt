package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b2295 {
    var N = 0
    lateinit var U: IntArray

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        U = List(N) { readLine().toInt() }.sortedDescending().toIntArray()
        solve()
    }

    fun solve() {
        val tempList = mutableSetOf<Int>()
        for (i in U) {
            for (j in U) {
                tempList.add(i + j)
            }
        }
        val list = tempList.distinct().sorted()

        for (i in U) {
            for (j in U) {
                val temp = i - j
                if (temp <= 0) continue

                var left = 0
                var right = list.size - 1
                while (left < right) {
                    val mid = (left + right) / 2

                    if (list[mid] == temp) {
                        println(i)
                        return
                    }

                    if (list[mid] > temp) right = mid - 1
                    else left = mid + 1
                }
            }
        }
    }
}