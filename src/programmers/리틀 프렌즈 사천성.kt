package programmers

import java.util.*
import kotlin.math.abs
import kotlin.math.absoluteValue


class `리틀 프렌즈 사천성` {
    class Solution {
        private lateinit var board: Array<String>
        private val dx = intArrayOf(1, 0, -1, 0)
        private val dy = intArrayOf(0, 1, 0, -1)

        fun solution(m: Int, n: Int, board: Array<String>): String {
            this.board = board
            var answer = ""
            val set: MutableMap<Char, MutableList<Pair<Int, Int>>> = HashMap()

            for (x in board.indices) {
                for (y in 0 until board[x].length) {
                    val c = board[x][y]
                    if (c != '.' && c != '*') {
                        if (!set.containsKey(c)) {
                            set[c] = ArrayList()
                        }
                        set[c]!!.add(Pair(x, y))
                    }
                }
            }

            val wordCheck: MutableList<Char> = ArrayList(set.keys)
            wordCheck.sort()

            while (wordCheck.isNotEmpty()) {
                var removeChar: Char? = null

                for (c in wordCheck) {
                    val sx = set[c]!![0].first
                    val sy = set[c]!![0].second
                    val ex = set[c]!![1].first
                    val ey = set[c]!![1].second

                    if (removeCheck(sx, sy, ex, ey)) {
                        removeChar = c
                        board[sx] = board[sx].replace(c, '.')
                        board[ex] = board[ex].replace(c, '.')
                        answer += c

                        break
                    }
                }

                if (removeChar == null) break

                wordCheck.remove(removeChar)
            }

            return if (wordCheck.isEmpty()) answer else "IMPOSSIBLE"
        }

        private fun removeCheck(sx: Int, sy: Int, ex: Int, ey: Int): Boolean {
            val visited = Array(4) { mutableSetOf<Pair<Int, Int>>() }
            repeat(4) { visited[it] = HashSet() }

            val queue = ArrayList<Node>()

            for (i in 0..3) {
                val nx = sx + dx[i]
                val ny = sy + dy[i]

                if (nx >= 0 && nx < board.size && ny >= 0 && ny < board[0].length) {
                    if (nx == ex && ny == ey) return true
                    else if (board[nx][ny] == '.') {
                        queue.add(Node(nx, ny, i, true))
                        visited[i].add(Pair(nx, ny))
                    }
                }
            }

            while (!queue.isEmpty()) {
                val n = queue.removeAt(0)

                repeat (4) { dir ->
                    if (n.dir != dir && !n.isStraight) return@repeat
                    if ((n.dir - dir).absoluteValue == 2) return@repeat

                    val nx = n.x + dx[dir]
                    val ny = n.y + dy[dir]
                    val p = Pair(nx, ny)

                    if (nx >= 0 && nx < board.size && ny >= 0 && ny < board[0].length && !visited[dir].contains(p)) {
                        if (nx == ex && ny == ey) return true
                        else if (board[nx][ny] == '.') {
                            var isStraight = n.isStraight
                            if (isStraight) isStraight = n.dir == dir

                            queue.add(Node(nx, ny, dir, isStraight))
                            visited[dir].add(p)
                        }
                    }
                }
            }

            return false
        }

        data class Node(var x: Int, var y: Int, var dir: Int, var isStraight: Boolean)

        private class Pair<K, V>(val first: K, val second: V) {
            override fun equals(o: Any?): Boolean {
                if (this === o) return true
                if (o == null || javaClass != o.javaClass) return false
                val pair = o as Pair<*, *>
                return first == pair.first && second == pair.second
            }

            override fun hashCode(): Int {
                return Objects.hash(first, second)
            }
        }
    }
}