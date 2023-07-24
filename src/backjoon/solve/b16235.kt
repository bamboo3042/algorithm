package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class b16235 {
    var N = 0
    var M = 0
    var K = 0
    val dx = arrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
    val dy = arrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
    lateinit var A: Array<IntArray>
    lateinit var forest: Array<Array<PriorityQueue<Int>>>
    lateinit var ground: Array<IntArray>

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val input = readLine().split(" ")
        N = input[0].toInt()
        M = input[1].toInt()
        K = input[2].toInt()
        A = Array(N) { IntArray(N) { 0 } }
        forest = Array (N) { Array(N) { PriorityQueue() } }
        ground = Array(N) { IntArray(N) { 5 } }
        repeat(N) { i ->
            val arr = readLine().split(" ").map { it.toInt() }
            arr.mapIndexed { j, num -> A[i][j] = num }
        }
        repeat(M) {
            val (x, y, z) = readLine().split(" ").map { it.toInt() }
            forest[x-1][y-1].add(z)
        }

        springAndSummer(1)

        println(forest.sumOf { line -> line.sumOf { it.size } })
    }

    fun springAndSummer(n: Int) {
        for (i in 0 until N) {
            for (j in 0 until N) {
                val queue = PriorityQueue<Int> ()
                while (forest[i][j].isNotEmpty() && ground[i][j] >= forest[i][j].peek()) {
                    val temp = forest[i][j].poll()
                    ground[i][j] -= temp
                    queue.add(temp + 1)
                }

                ground[i][j] += forest[i][j].sumOf { it / 2 }
                forest[i][j] = queue
            }
        }

        fall(n)
    }

    fun fall(n: Int) {
        for (i in 0 until N) {
            for (j in 0 until N) {
                forest[i][j].filter { it % 5 == 0 }.map {
                    for (k in 0 until 8) {
                        val nx = i + dx[k]
                        val ny = j + dy[k]
                        if(nx in 0 until N && ny in 0 until N) forest[nx][ny].offer(1)
                    }
                }
            }
        }

        winter(n)
    }

    fun winter(n: Int) {
        if (n == K) return

        for (i in 0 until N) {
            for (j in 0 until N) {
                ground[i][j] += A[i][j]
            }
        }

        springAndSummer(n + 1)
    }

    fun printGround() {
        ground.map { l ->
            l.map { print("$it ") }
            println()
        }
        println()
    }

    fun printForest() {
        println("----- forest -----")
        forest.map { l ->
            l.map {
                print("$it ")
            }
            println()
        }
    }
}