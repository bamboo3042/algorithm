package backjoon

fun main() = with(System.`in`.bufferedReader()) {
    val (a, b, c) = readLine().split(" ").map { it.toInt() }.sorted()

    val answer = when {
        a == b && b == c -> 10000 + a * 1000
        a == b -> 1000 + a * 100
        b == c -> 1000 + b * 100
        a == c -> 1000 + a * 100
        else -> c * 100
    }

    println(answer)
}