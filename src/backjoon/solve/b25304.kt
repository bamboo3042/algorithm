package backjoon.solve

class b25304 {
    fun main() = with(System.`in`.bufferedReader()) {
        val x = readLine().toInt()
        val count = readLine().toInt()
        val y = IntArray(count) {
            readLine().split(" ").let { it[0].toInt() * it[1].toInt() }
        }.sum()

        if (x == y) println("Yes")
        else println("No")
    }
}