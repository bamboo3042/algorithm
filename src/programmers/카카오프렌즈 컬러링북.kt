package programmers

class `카카오프렌즈 컬러링북` {
    class Solution {
        val dx = listOf(1, -1, 0, 0)
        val dy = listOf(0, 0, 1, -1)

        fun solution(m: Int, n: Int, picture: Array<IntArray>): IntArray {
            var numberOfArea = 0
            var maxSizeOfOneArea = 0

            val visited = Array(m) { BooleanArray(n) { false } }

            fun countArea(color: Int, i: Int, j: Int): Int {
                if (i !in picture.indices || j !in picture[0].indices || picture[i][j] != color || visited[i][j]) return 0

                visited[i][j] = true

                var count = 1

                repeat(4) { count += countArea(color, i + dx[it], j + dy[it]) }

                return count
            }

            for (i in picture.indices) {
                for (j in picture[0].indices) {
                    if (picture[i][j] == 0 || visited[i][j]) continue

                    numberOfArea++
                    maxSizeOfOneArea = maxOf(maxSizeOfOneArea, countArea(picture[i][j], i, j))
                }
            }

            val answer = IntArray(2)
            answer[0] = numberOfArea
            answer[1] = maxSizeOfOneArea
            return answer
        }
    }
}