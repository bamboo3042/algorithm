package backjoon.solve

import java.util.*

class b1953 {
    var N = 0
    var team = listOf(mutableSetOf<Int>())
    lateinit var visited: BooleanArray
    lateinit var board: Array<BooleanArray>

    fun main()  = with(Scanner(System.`in`)) {
        N = readln().toInt()
        board = Array(N+1) { BooleanArray(N+1) {false} }
        team = List(2) { mutableSetOf() }
        visited = BooleanArray(N+1) {false}
        repeat(N) {
            val arr = readln().trim().split(" ").map { it.toInt() }
            for (i in 1 until arr.size) {
                board[it+1][arr[i]] = true
                board[arr[i]][it+1] = true
            }
        }

        repeat(N) { dfs(it+1, true) }

        println(team[0].size)
        println(team[0].sorted().joinToString(" "))
        println(team[1].size)
        println(team[1].sorted().joinToString(" "))

    }
    fun dfs(n: Int, isTeam1: Boolean) {
        if (visited[n]) return

        val teamIndex1 = if (isTeam1) 0 else 1
        visited[n] = true
        team[teamIndex1].add(n)

        for (i in 1 .. N) {
            if (board[n][i]) { dfs(i, !isTeam1) }
        }
    }
}