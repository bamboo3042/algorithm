package backjoon.solve

import java.util.Scanner

class b11382 {
    fun main() = with(Scanner(System.`in`)) {
        println(readLine()!!.split(" ").sumOf { it.toLong() })
    }
}