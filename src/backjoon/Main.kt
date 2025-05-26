package backjoon

import java.time.ZoneId
import java.time.ZonedDateTime

fun main(args : Array<String>) {
//    println(
//        "answer: ${Solution().solution(30, arrayOf("d", "e", "bb", "aa", "ae"))}"
//    )

    val map = mutableMapOf(
        User(1) to "A",
        User(2) to "B",
        User(3) to "C"
    )

    val keepIds = listOf(1, 3)

    map.keys.removeIf { it.id !in keepIds }

    println(map)  // 출력 결과: {User(id=1)=A, User(id=3)=C}

//    Solution().test()
}

data class User(val id : Int)
//d-4, e-5, aa-27, bb-54, ae-131
//temp: 30 count: 4
//temp: 31 count: 5
//temp: 32 count: 27
//count: 3
//54
//temp: 33
//answer: ag

class Solution {
    fun test() {
        for (i in 1L .. 800) {
            val str = i.toStr()
            println("$i.\t$str\t${str.toLong()}")
        }
    }

    fun solution(n: Long, bans: Array<String>): String {
        val newBans = bans.sortedWith(compareBy({it.toLong()}))
        var temp = n
        var count = 0

        println(newBans.joinToString(", ") { "$it-${it.toLong()}" })

        while (count != newBans.size && newBans[count].toLong() <= temp) {
            println("temp: $temp count: ${newBans[count].toLong()}")
            count++
            temp++
        }

        println("count: $count")
        if(count != newBans.size) {
            println(newBans[count].toLong())
        }
        println("temp: $temp")

        return temp.toStr()
    }

    private fun Long.toStr(): String {
        var result = ""
        var n = this

        while (n > 0) {
            val mod = (n - 1) % 26
            result = (mod + 'a'.code).toInt().toChar() + result
            n = (n - 1) / 26
        }

        return result
    }

    private fun String.toLong(): Long {
        var number = 0L

        this.reversed().forEachIndexed { i, c ->
            number += (c - 'a' + 1) * (26L.pow(i))
        }

        return number
    }

    private fun Long.pow(exp: Int): Long {
        var result = 1L
        repeat(exp) { result *= this }
        return result
    }
}