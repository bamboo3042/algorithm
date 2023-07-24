package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b9019 {
    var num  = 0
    var result = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        repeat(readLine().toInt()) {
            val input = readLine().split(" ").map { it.toInt() }
            num = input[0]
            result = input[1]
            println(bfs())
        }
    }

    fun bfs(): String {
        val queue = ArrayDeque<Pair<Int, String>>()
        val visited = BooleanArray(10000) {false}
        queue.add(num to "")
        visited[num] = true
        while (queue.isNotEmpty()) {
            val (n, str) = queue.removeFirst()

            if (n == result) return str

            val d = calD(n)
            val s = calS(n)
            val l = calL(n)
            val r = calR(n)
            if (!visited[d]) {
                visited[d] = true
                queue.add(d to str+"D")
            }
            if (!visited[s]) {
                visited[s] = true
                queue.add(s to str+"S")
            }
            if (!visited[l]) {
                visited[l] = true
                queue.add(l to str+"L")
            }
            if (!visited[r]) {
                visited[r] = true
                queue.add(r to str+"R")
            }

        }

        return ""
    }

    fun calD(n: Int): Int {
        return (n * 2) % 10000
    }

    fun calS(n: Int): Int {
        return if (n == 0) 9999 else n-1
    }

    fun calL(n: Int): Int {
        val t = n / 1000
        return (n % 1000) * 10 + t
    }

    fun calR(n: Int): Int {
        val t = n % 10
        return (n / 10) + t * 1000
    }
}