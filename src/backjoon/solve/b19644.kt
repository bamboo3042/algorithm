package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b19644 {
    data class RecentlyC(
        var sum: Int = 0,
        var count: Int = 0,
        var size: Int = 0,
        val record: ArrayDeque<Boolean> = ArrayDeque(),
    ) {
        fun addRecord(fire: Boolean) {
            if (fire) count++
            if (size == 0) return
            if (fire) sum++

            if (record.size < size) record.addLast(fire)
            else {
                val first = record.removeFirst()
                if (first) sum--
                record.addLast(fire)
            }
        }
    }

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val L = readLine().toInt()
        val (ml, mk) = readLine().split(" ").map { it.toInt() }
        val C = readLine().toInt()
        val zombies = listOf(0) + List(L) {readLine().toInt()}
        val c = RecentlyC(sum = 0, count = 0, size = ml-1, record = ArrayDeque())
        var isEnd = false
        for (l in zombies.indices) {
            val fire = (minOf(l, ml) - c.sum) * mk
            val z = zombies[l] - fire
            if (z > 0 && c.count == C) {
                isEnd = true
                break
            }
            c.addRecord(z > 0)
        }

        if (isEnd) println("NO") else println("YES")
    }
}