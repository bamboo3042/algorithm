package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b1707 {
    lateinit var board: Array<MutableList<Int>>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        repeat(readLine().toInt()) {
            val (V, E) = readLine().split(" ").map { it.toInt() }
            board = Array(V) { mutableListOf() }
            repeat(E) {
                val (u, v) = readLine().split(" ").map { it.toInt() - 1}
                board[u].add(v)
                board[v].add(u)
            }

            println(if (dfs(V)) "YES" else "NO")
        }
    }

    fun dfs(v: Int): Boolean {
        val colors = IntArray(v) {0}
        val queue = ArrayDeque<Int> ()

        for (t in 0 until v) {
            if (colors[t] != 0) continue

            colors[t] = 1
            queue.addLast(t)

            while (queue.isNotEmpty()) {
                val temp = queue.removeFirst()
                val color = colors[temp]

                for (i in board[temp]) {
                    if (colors[i] == 0) {
                        colors[i] = if (color == 1) -1 else 1
                        queue.addLast(i)
                    }
                    else if (colors[i] == color) return false
                }
            }
        }

        return true
    }
}