package backjoon

import java.io.BufferedReader
import java.io.InputStreamReader

class b15311 {
    var N = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        println(2000)
        for (i in 0 until 1000) print("1 ")
        for (i in 0 until 1000) print("1000 ")
    }
}