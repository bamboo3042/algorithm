package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class b19236 {
    val dx = arrayOf(-1, -1, 0, 1, 1, 1, 0, -1)
    val dy = arrayOf(0, -1, -1, -1, 0, 1, 1, 1)

    data class Shark(
        var x: Int,
        var y: Int,
        var d: Int
    )

    data class Fish(
        var x: Int,
        var y: Int,
        var d: Int
    )

    var answer = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val fishes = Array<Fish?>(16) { null }
        repeat(4) {  i ->
            val st = StringTokenizer(readLine())
            repeat(4) { j ->
                fishes[st.nextToken().toInt() - 1] = Fish(
                    x = i,
                    y = j,
                    d = st.nextToken().toInt() - 1
                )
            }
        }
        val firstIndex = fishes.indexOfFirst { it != null && it.x == 0 && it.y == 0 }
        dfs(Shark(0, 0, fishes[firstIndex]!!.d), fishes.also { it[firstIndex] = null }, firstIndex + 1)

        println(answer)
    }

    fun dfs(shark: Shark, fishes: Array<Fish?>, sum: Int) {
        fishMove(shark, fishes)
        val sharkMoves = sharkMoveCheck(shark, fishes)
        if (sharkMoves.isEmpty()) answer = answer.coerceAtLeast(sum)
        else {
            sharkMoves.map {  index ->
                val tempFish = fishes[index]
                fishes[index] = null
                dfs(Shark(tempFish!!.x, tempFish.y, tempFish.d), Array(fishes.size) {fishes[it]?.copy()}, sum + index + 1)
                fishes[index] = tempFish
            }
        }
    }

    fun fishMove(shark: Shark, fishes: Array<Fish?>) {
        for (tempFish in fishes) {
            if (tempFish != null) {
                for (i in 0 until 8) {
                    val td = (tempFish.d + i) % 8
                    val nx = tempFish.x + dx[td]
                    val ny = tempFish.y + dy[td]
                    if (nx in 0 until 4 && ny in 0 until 4 && !(shark.x == nx && shark.y == ny)) {
                        tempFish.d = td
                        val targetFish = fishes.find { it != null && it.x == nx && it.y == ny }
                        if (targetFish == null) {
                            tempFish.x = nx
                            tempFish.y = ny
                        }
                        else {
                            val tx = tempFish.x
                            val ty = tempFish.y
                            tempFish.x = targetFish.x
                            tempFish.y = targetFish.y
                            targetFish.x = tx
                            targetFish.y = ty
                        }
                        break
                    }
                }
            }
        }
    }

    fun sharkMoveCheck(shark: Shark, fishes: Array<Fish?>): List<Int> {
        val result = mutableListOf<Int>()
        var mul = 1
        while (true) {
            val nx = shark.x + (dx[shark.d] * mul)
            val ny = shark.y + (dy[shark.d] * mul)
            if (nx !in 0 until 4 || ny !in 0 until 4) break
            else {
                val f = fishes.indexOfFirst { (it?.x == nx) && (it.y == ny) }
                if (f != -1) result.add(f)
            }
            mul++
        }
        return result
    }

    fun printBoard(shark: Shark, fishes: Array<Fish?>) {
        val board = Array(4) { Array<Pair<Int, Int>?>(4) { null } }
        fishes.forEachIndexed { index, fish ->
            if (fish != null) board[fish.x][fish.y] = index to fish.d
        }
        board[shark.x][shark.y] = -1 to shark.d
//    println("]-----] Shark: $shark [-----[")
        board.map { l ->
            l.map { print("${
                if (it != null) {
                    if (it.first == -1) "[S, ${it.second+1}]"
                    else "[${it.first+1}, ${it.second+1}]"
                }
                else "[ , ]"} ") }
            println()
        }
        println()
    }
}