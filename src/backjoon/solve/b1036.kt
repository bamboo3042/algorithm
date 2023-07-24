package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class b1036 {
    val str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val strList = mutableListOf<String>()
    var answer = ""
    val pq = PriorityQueue<Pair<Char, String>> { o1, o2 ->
        if(o1.second.length > o2.second.length) -1
        else if(o1.second.length < o2.second.length) 1
        else -o1.second.compareTo(o2.second)
    }

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readLine().toInt()
        repeat(n) { strList.add(readLine()) }
        val k = readLine().toInt()

        str.map { changeStr(it) }

        var tempStr = ""

        repeat(k) {
            tempStr += pq.poll().first
        }

        getAnswer(tempStr)

        println(answer)
    }

    fun changeStr(c: Char) {
        val resultStr = strList.map { sl ->
            var s = ""
            sl.map { s += if(it == c) "Z" else it }
            s.reversed()
        }

        val result = mutableListOf<Int>()
        resultStr.map { rs ->
            rs.mapIndexed { index, c ->
                if(index >= result.size) result.add(str.indexOf(c))
                else result[index] += str.indexOf(c)
            }
        }
        var i = 0
        while(i < result.size) {
            if(result[i] > 35) {
                if(i == result.size - 1) result.add(result[i] / 36)
                else result[i + 1] += result[i] / 36
                result[i] = result[i] % 36
            }
            i++
        }

        result.reverse()
        pq.add(Pair(
            c,
            result.map { str[it] }.joinToString("")))
    }

    fun getAnswer(tempStr: String) {
        val resultStr = strList.map { sl ->
            var s = ""
            sl.map { s += if(it in tempStr) "Z" else it }
            s.reversed()
        }

        val result = mutableListOf<Int>()

        resultStr.map { rs ->
            rs.mapIndexed { index, c ->
                if(index >= result.size) result.add(str.indexOf(c))
                else result[index] += str.indexOf(c)
            }
        }

        var i = 0
        while(i < result.size) {
            if(result[i] > 35) {
                if(i == result.size - 1) result.add(result[i] / 36)
                else result[i + 1] += result[i] / 36
                result[i] = result[i] % 36
            }
            i++
        }

        result.reverse()

        if(result.size > answer.length) changeAnswer(result)
        else if (result.size == answer.length) {
            i = 0
            while(i < result.size) {
                if(str.indexOf(answer[i]) < result[i]) changeAnswer(result)
                else if(str.indexOf(answer[i]) > result[i]) break
                i++
            }
        }
    }

    fun changeAnswer(result: List<Int>) {
        answer = ""
        result.map {
            answer += str[it]
        }
    }
}