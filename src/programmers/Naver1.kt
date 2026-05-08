package programmers

class Naver1 {
    fun solution(s: String): Int {
        // Implement your solution here

        val map = s.groupBy { it }.mapValues { it.value.size }

        val oddCount = map.count { (_, v) -> v % 2 == 1 }

        return when (oddCount) {
            0, 1 -> 0
            else -> oddCount - 1
        }
    }
}