package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b27065 {
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        repeat(readLine().toInt()) {
            println(if (check(readLine().toInt())) "Good Bye" else "BOJ 2022")
        }
    }

    fun check(n: Int): Boolean {
        val list = div(n)
        if (list.sum() <= n) return false

        list.forEach { if (div(it).sum() > it) return false }

        return true
    }

    fun div(n: Int): List<Int> {
        val list = mutableListOf<Int>()
        for (i in 1 until n) {
            if (n%i == 0) list.add(i)
        }
        return list
    }
}