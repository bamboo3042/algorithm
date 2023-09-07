package programmers


class 아이템_줍기 {
    class Solution {

        val visited = Array(101) { BooleanArray(101) { false } }
        lateinit var rectangle: Array<IntArray>
        val dx = arrayOf(1, -1, 0, 0)
        val dy = arrayOf(0, 0, 1, -1)

        fun solution(rectangle: Array<IntArray>, characterX: Int, characterY: Int, itemX: Int, itemY: Int): Int {
            val queue = ArrayDeque<IntArray>()
            this.rectangle = Array(rectangle.size) { rectangle[it].map { it * 2 }.toIntArray() }
            queue.add(intArrayOf(characterX * 2, characterY * 2, 0))
            visited[characterX * 2][characterY * 2] = true

            while (queue.isNotEmpty()) {
                val (x, y, d) = queue.removeFirst()

                if (x == itemX * 2 && y == itemY * 2) return d / 2

                repeat(4) {
                    val nx = x + dx[it]
                    val ny = y + dy[it]
                    if (isMovable(nx, ny, x, y)) {
                        visited[nx][ny] = true
                        queue.addLast(intArrayOf(nx, ny, d + 1))
                    }
                }
            }

            return 0
        }

        fun isMovable(nx: Int, ny: Int, px: Int, py: Int): Boolean {
            if (nx < 0 || nx > 100 || ny < 0 || ny > 100) return false
            if (visited[nx][ny]) return false
            val list = rectangle.mapIndexedNotNull { index, r ->
                if (lineCheck(r[0], r[2], r[1], px, py, nx, ny) ||
                    lineCheck(r[0], r[2], r[3], px, py, nx, ny) ||
                    lineCheck(r[1], r[3], r[0], py, px, ny, nx) ||
                    lineCheck(r[1], r[3], r[2], py, px, ny, nx)
                ) index
                else null
            }

            if (list.isEmpty()) return false

            return rectangle.find { r -> nx in r[0]+1 until r[2] && ny in r[1]+1 until r[3] } == null
        }

        fun lineCheck(r1: Int, r2: Int, r3: Int, p1: Int, p2: Int, p3: Int, p4: Int): Boolean {
            return p1 in r1..r2 && p2 == r3 && p3 in r1 .. r2 && p4 == r3
        }
    }
}