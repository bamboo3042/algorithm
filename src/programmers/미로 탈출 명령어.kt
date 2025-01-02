package programmers

import kotlin.math.absoluteValue

class `미로 탈출 명령어` {
    class Solution {
        val dir = listOf(
            1 to 0,
            0 to -1,
            0 to 1,
            -1 to 0
        )
        val answer = StringBuilder()
        val str = listOf("d", 'l', 'r', 'u')
        fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
            dfs(n, m, x-1, y-1, r-1, c-1, k)

            return if (answer.isEmpty()) "impossible"
            else answer.toString()
        }

        fun dfs(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int) {
            if (k == 0) return

            for (i in 0 until 4) {
                val (dx, dy) = dir[i]

                val nx = x + dx
                val ny = y + dy

                if (check(n, m, nx, ny, r, c, k-1)) {
                    answer.append(str[i])
                    return dfs(n, m, nx, ny, r, c, k-1)
                }
            }

            return
        }

        fun check(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): Boolean {
            if (x !in 0 until n) return false
            if (y !in 0 until m) return false

            val minDis = (x - r).absoluteValue + (y - c).absoluteValue

            return !(minDis > k || (k - minDis) % 2 != 0)
        }
    }
}