package programmers

class `자물쇠와 열쇠` {
    class Solution {
        fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
            val M = key.size
            val N = lock.size

            var keyPart = key.mapIndexed { i, ints ->
                ints.mapIndexed { j, n ->
                    if (n == 1) i to j
                    else null
                }.filterNotNull()
            }.flatten()


            val mapHole = mutableSetOf<Pair<Int, Int>>()
            val mapPart = mutableSetOf<Pair<Int, Int>>()

            lock.forEachIndexed { i, ints ->
                ints.forEachIndexed { j, n ->
                    if (n == 0) mapHole.add(i to j)
                    else mapPart.add(i to j)
                }
            }

            if (keyPart.size < mapHole.size) return false

            val m = M - 1
            repeat(4) {
                keyPart = keyPart.map { (a, b) -> b to m - a }

                (-M + 1 until N).forEach first@{ i ->
                    (-M + 1 until N).forEach second@{ j ->
                        var count = 0

                        keyPart.forEach { (a, b) ->
                            val na = a + i
                            val nb = b + j

                            if (na to nb in mapPart) return@second
                            if (na to nb in mapHole) count++
                        }

                        if (count == mapHole.size) return true
                    }
                }
            }

            return false
        }
    }
}