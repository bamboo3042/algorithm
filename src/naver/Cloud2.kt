package naver

class Cloud2 {
    fun solution(A: Int, B: Int): Int {
        val case1 = A / 4
        val case2 = minOf(A / 3, B / 3)
        val case3 = minOf(A / 2, B / 2)
        val case4 = minOf(A, B / 3)
        val case5 = B / 4

        return maxOf(case1, case2, case3, case4, case5)
    }
}