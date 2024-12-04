package backjoon

import kotlin.math.absoluteValue
import kotlin.math.sqrt

fun main(args : Array<String>) {
}

//1 2 1
//2 1 2
//2 2 1
//3 1 3
//3 2 2
//4 1 3
//4 2 2
//5 1 4
//5 2 3

class TestSolution {
    var N = 0
    var K = 0
    var T = Int.MAX_VALUE
    var MAX_K = 0

    fun solution(n: Int, k: Int): Int {
        N = n
        K = k
        MAX_K = sqrt(n.toFloat()).toInt()

        if (K == 2) MAX_K *= 2

        println(MAX_K)

        if (K == 2) dfs(2, 3, 1)
        dfs(1, 2, 1)

        return T
    }

    fun dfs(tk: Int, d: Int, t: Int) {
        if (T != Int.MAX_VALUE) return
        if (tk <= 0 || tk > MAX_K) return
        if (d > N) return
        if (d == N) {
            if (tk <= K) T = minOf(T, t)
            return
        }

        println("tk: $tk, d: $d, t: $t")

        if (K == 2) dfs(tk + 2, d + tk, t + 1)
        dfs(tk + 1, d + tk, t + 1)
        dfs(tk, d + tk, t + 1)
        dfs(tk - 1, d + tk, t + 1)
        if (K == 2) dfs(tk - 2, d + tk, t + 1)
    }
}

class Solution {
    fun solution(expression: String): Long {
        var answer: Long = 0
        val numbers = mutableListOf<Long>()
        val match = mutableListOf<Char>()
        var number = 0L

        this.javaClass.classLoader

        expression.forEach { s ->
            if (s in listOf('*', '+', '-')) {
                numbers.add(number)
                number = 0
                match.add(s)
            }
            else number = number * 10 + s.code - '0'.code
        }

        val priorityList = listOf(
            listOf('*', '+', '-'),
            listOf('*', '-', '+'),
            listOf('+', '*', '-'),
            listOf('+', '-', '*'),
            listOf('-', '+', '*'),
            listOf('-', '*', '+'),
        )

        priorityList.forEach { priority ->
            answer = maxOf(answer, findMax(numbers.toMutableList(), match.toMutableList(), priority))
        }

        return answer
    }

    private fun findMax(numbers: MutableList<Long>, match: MutableList<Char>, priority: List<Char>): Long {
        println("]-----] [-----[")
        println(numbers)
        println(match)
        println(priority)
        priority.forEach { p ->

            var count = 0

            match.forEachIndexed { index, c ->
                if (c == p) {
                    println("$c")
                    println("$index $count")
                    val a = numbers.removeAt(index - count)
                    val b = numbers.removeAt(index - count)

                    numbers.add(
                        index - count,
                        when(p) {
                            '*' -> a * b
                            '+' -> a + b
                            else -> a - b
                        }
                    )

                    count++
                }
            }

            match.removeAll { it == p }
        }

        return numbers.first().absoluteValue
    }
}