package backjoon.solve

class b2525 {
    fun main() = with(System.`in`.bufferedReader()) {
        val (h, m) = readLine().split(" ").map { it.toInt() }
        val t = (h * 60) + m + readLine().toInt()

        println("${(t / 60) % 24} ${t % 60}")
    }
}