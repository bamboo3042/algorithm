package programmers

class `숫자 타자 대회` {
    class Solution {
        val MOVE_MAP = listOf(
            listOf(1, 7, 6, 7, 5, 4, 5, 3, 2, 3),
            listOf(7, 1, 2, 4, 2, 3, 5, 4, 5, 6),
            listOf(6, 2, 1, 2, 3, 2, 3, 5, 4, 5),
            listOf(7, 4, 2, 1, 5, 3, 2, 6, 5, 4),
            listOf(5, 2, 3, 5, 1, 2, 4, 2, 3, 5),
            listOf(4, 3, 2, 3, 2, 1, 2, 3, 2, 3),
            listOf(5, 5, 3, 2, 4, 2, 1, 5, 3, 2),
            listOf(3, 4, 5, 6, 2, 3, 5, 1, 2, 4),
            listOf(2, 5, 4, 5, 3, 2, 3, 2, 1, 2),
            listOf(3, 6, 5, 4, 5, 3, 2, 4, 2, 1),
        )
        val dp = mutableMapOf<Triple<Int, Int, Int>, Int>()

        fun solution(numbers: String): Int {
            var dp = mutableMapOf<Pair<Int, Int>, Int>((4 to 6) to 0)

            numbers.forEach { s ->
                val nextN = s.digitToInt()
                val temp = mutableMapOf<Pair<Int, Int>, Int>()

                dp.forEach { (a, b), d ->
                    if (b != nextN) {
                        for (i in 0 until 10) {
                            val nd = d + MOVE_MAP[a][nextN]
                            temp.compute(nextN to b) { _, v -> minOf(v ?: Int.MAX_VALUE, nd) }
                        }
                    }
                    if (a != nextN) {
                        for (i in 0 until 10) {
                            val nd = d + MOVE_MAP[b][nextN]
                            temp.compute(a to nextN) { _, v -> minOf(v ?: Int.MAX_VALUE, nd) }
                        }
                    }
                }

                dp = temp
            }

            val lastN = numbers.last().digitToInt()

            return dp.filter { (k, v) -> k.first == lastN || k.second == lastN }.minOf { it.value }
        }
    }
}