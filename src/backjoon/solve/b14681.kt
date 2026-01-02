package backjoon.solve

class b14681 {
    fun main() = with(System.`in`.bufferedReader()) {
        val x = readLine().toInt()
        val y = readLine().toInt()

        val answer = when {
            x > 0 && y > 0 -> 1
            x > 0 && y < 0 -> 4
            x < 0 && y > 0 -> 2
            else -> 3
        }

        println(answer)
    }
}