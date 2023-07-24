package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class b5719 {
    lateinit var road: List<MutableList<Pair<Int, Int>>>
    lateinit var dis: IntArray
    var N = 0
    var S = 0
    var D = 0

    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        while (true) {
            val (n, m) = readLine().split(" ").map { it.toInt() }

            if (n == 0) break

            val (s, d) = readLine().split(" ").map { it.toInt() }
            N = n
            S = s
            D = d
            road = List(N) { mutableListOf() }
            repeat(m) {
                val (u, v, p) = readLine().split(" ").map { it.toInt() }
                road[u].add(v to p)
            }
            dijkstra()
            removeRoad(D)
            dijkstra()
            println(if (dis[D] == Int.MAX_VALUE) -1 else dis[D])
        }
    }

    fun removeRoad(n: Int) {
        if (n == S) return
        val d = dis[n]

        for (temp in dis.indices) {
            if (dis[temp] > d) continue

            val t = road[temp].find { (v, p) -> v == n && p + dis[temp] == d }
            if (t != null) {
                road[temp].remove(t)
                removeRoad(temp)
            }
        }
    }

    fun dijkstra() {
        val queue = PriorityQueue<Pair<Int, Int>> (compareBy { it.first })
        dis = IntArray(N) { Int.MAX_VALUE }
        queue.add(0 to S)
        dis[S] = 0

        while (queue.isNotEmpty()) {
            val (d, temp) = queue.poll()

            if (dis[temp] < d) continue

            for ((v, p) in road[temp]) {
                val nd = d + p
                if (nd < dis[v]) {
                    dis[v] = nd
                    queue.add(nd to v)
                }
            }
        }
    }
}