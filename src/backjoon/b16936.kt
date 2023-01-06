package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b16936 {
    var N = 0
    val answer = ArrayDeque<Long>()
    var list = mutableListOf<Long>()

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        list = readLine().split(" ").map { it.toLong() }.toMutableList()
        list.sort()
        answer.addFirst(list.removeFirst())
        while (list.isNotEmpty()) {
            val t = list.removeFirst()
            if ((t%3 == 0L && t/3 == answer.first()) || t*2 == answer.first()) answer.addFirst(t)
            else if (t == answer.last()*2 || (answer.last()%3 == 0L && t == answer.last()/3)) answer.addLast(t)
            else list.add(t)
        }
        println(answer.joinToString(" "))
    }
}