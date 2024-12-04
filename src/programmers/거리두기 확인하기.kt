package programmers

class `거리두기 확인하기` {
    class Solution {
        val dx = listOf(1, -1, 0, 0)
        val dy = listOf(0, 0, 1, -1)

        fun solution(places: Array<Array<String>>): IntArray {
            val answer = mutableListOf<Int>()
            var count = 0

            places.forEach forEachPlace@{ place ->
                place.forEachIndexed { i, s ->
                    s.forEachIndexed { j, c ->
                        if (c == 'P') {
                            if (!check(place, i, j, 0, mutableSetOf())) {
                                answer.add(0)
                                return@forEachPlace
                            }
                        }
                    }
                }

                answer.add(1)
            }

            return answer.toIntArray()
        }

        fun check(place: Array<String>, x: Int, y: Int, n: Int, visited: MutableSet<Pair<Int, Int>>): Boolean {
            if (x !in place.indices || y !in place[x].indices) return true
            if (visited.contains(x to y)) return true

            visited.add(Pair(x, y))

            if (place[x][y] == 'X') return true
            if (n == 1 && place[x][y] == 'P') return false
            if (n == 2) return place[x][y] != 'P'

            repeat(4) { i ->
                if (!check(place, x + dx[i], y + dy[i], n + 1, visited.toMutableSet())) return false
            }

            return true
        }
    }
}