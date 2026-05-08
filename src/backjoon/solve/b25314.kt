package backjoon.solve

class b25314 {
    fun main() = with(System.`in`.bufferedReader()) {
        val x = readLine().toInt()
        val count = (x / 4) + if (x % 4 == 0) 0 else 1

        println("${"long ".repeat(count)}int")
    }
}