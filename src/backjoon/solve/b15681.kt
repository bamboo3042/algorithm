package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader

class b15681 {
    lateinit var dp: IntArray
    lateinit var line: List<MutableList<Int>>
    lateinit var tree: List<MutableList<Int>>
    lateinit var visited: BooleanArray

    fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (N, R, Q) = readLine().split(" ").map { it.toInt() }
        val sb = StringBuilder()
        dp = IntArray(N+1) {1}
        line = List(N+1) { mutableListOf() }
        tree = List(N+1) { mutableListOf() }
        visited = BooleanArray(N+1) { false }
        repeat(N-1) {
            val (U, V) = readLine().split(" ").map { it.toInt() }
            line[U].add(V)
            line[V].add(U)
        }
        makeTree(R, -1)

        repeat(Q) { sb.appendLine(dp[readLine().toInt()]) }
        println(sb)
    }

    fun makeTree(n: Int, p: Int) {
        visited[n] = true
        for (c in line[n]) {
            if (c != p && !visited[c]) {
                tree[n].add(c)
                makeTree(c, n)
                dp[n] += dp[c]
            }
        }
    }
}