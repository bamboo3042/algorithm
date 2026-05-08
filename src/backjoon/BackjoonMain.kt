package backjoon

fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine()!!.split(' ').map { it.toInt() }
    val items = Array(N) { readLine().split(' ').map(String::toInt) }
    val dp = IntArray(K + 1) { 0 }

    for (i in 0 until N) {
        val (w, v) = items[i]

        for (j in K downTo 1) {
            if (w <= j) dp[j] = maxOf(dp[j], dp[j - w] + v)
        }
    }

    println(dp[K])
}