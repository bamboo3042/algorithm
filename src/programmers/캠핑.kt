//package programmers
//
//import java.util.*
//import kotlin.math.max
//import kotlin.math.min
//
//
//class 캠핑 {
//    fun solution(n: Int, data: Array<IntArray>): Int {
//        val xSet: MutableSet<Int> = TreeSet()
//        val ySet: MutableSet<Int> = TreeSet()
//
//        for (point in data) {
//            xSet.add(point[0])
//            ySet.add(point[1])
//        }
//
//        val xMap: MutableMap<Int, Int> = HashMap()
//        val yMap: MutableMap<Int, Int> = HashMap()
//
//        var idx = 0
//        for (x in xSet) {
//            xMap[x] = idx++
//        }
//
//        idx = 0
//        for (y in ySet) {
//            yMap[y] = idx++
//        }
//
//        val maxX = xMap.size
//        val maxY = yMap.size
//
//        val grid = Array(maxX) { IntArray(maxY) }
//        for ((px, py) in data) {
//            val x = xMap[px]!!
//            val y = yMap[py]!!
//            grid[x][y] = 1
//        }
//
//        val ps = Array(maxX + 1) { IntArray(maxY + 1) }
//
//        for (i in 1..maxX) {
//            for (j in 1..maxY) {
//                ps[i][j] = ps[i - 1][j] + ps[i][j - 1] - ps[i - 1][j - 1] + grid[i - 1][j - 1]
//            }
//        }
//
//        var count = 0
//
//        for (i in 0..<n) {
//            val x1 = xMap[data[i][0]]!!
//            val y1 = yMap[data[i][1]]!!
//
//            for (j in i + 1..<n) {
//                val x2 = xMap[data[j][0]]!!
//                val y2 = yMap[data[j][1]]!!
//
//                if (x1 == x2 || y1 == y2) continue
//
//                val minX = min(x1.toDouble(), x2.toDouble()).toInt()
//                val maxX_ = max(x1.toDouble(), x2.toDouble()).toInt()
//                val minY = min(y1.toDouble(), y2.toDouble()).toInt()
//                val maxY_ = max(y1.toDouble(), y2.toDouble()).toInt()
//
//                val internalPoints =
//                    ps[maxX_][maxY_] - ps[minX + 1][maxY_] - ps[maxX_][minY + 1] + ps[minX + 1][minY + 1]
//
//                if (internalPoints == 0) {
//                    count++
//                }
//            }
//        }
//
//        return count
//    }
//}