package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.absoluteValue

class b2170 {
    var N = 0
    var answer = 0
    var list = mutableListOf<Pair<Int, Int>>()

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        N = readLine().toInt()
        repeat(N) {
            var (x, y) = readLine().split(" ").map { it.toInt() }
            while(true) {
                val (tx, ty) = list.find { (tx, ty) -> (tx in x..y) || ty in x..y || x in tx..ty || y in tx..ty} ?: break
                list.remove(tx to ty)

                if(x <= tx && y <= ty) y = ty
                else if (x >= tx && y >= ty) x = tx
                else if (x <= tx && y >= ty) continue
                else {
                    x = tx
                    y = ty
                }
            }
            list.add(x to y)
        }
        println(list.sumOf { (x, y) -> (y - x).absoluteValue })
    }
}