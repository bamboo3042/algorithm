import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.absoluteValue

var N = 0
var M = 0
var G = 0
var R = 0
val gPlace = mutableListOf<Int>()
val rPlace = mutableListOf<Int>()
val startPlace = mutableListOf<Pair<Int, Int>>()
lateinit var board: Array<IntArray>
val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)
var result = 0


fun main() = with(Scanner(System.`in`)) {
    readln().split(" ").map { it.toInt() }.run {
        N = this[0]
        M = this[1]
        G = this[2]
        R = this[3]
    }
    board = Array(N) { i -> readln().split(" ").mapIndexed { j, s -> s.toInt().apply { if (this == 2) startPlace.add(i to j) } }.toIntArray() }

    selectStart(0)

    println(result)
}

fun selectStart(n: Int) {
    if (gPlace.size + rPlace.size == G+R) {
        result = maxOf(result, countFlower())
        return
    }
    if (n >= startPlace.size) return

    for (i in n until startPlace.size) {
        if (gPlace.size < G) {
            gPlace.add(i)
            selectStart(i+1)
            gPlace.remove(i)
        }
        if (rPlace.size < R) {
            rPlace.add(i)
            selectStart(i+1)
            rPlace.remove(i)
        }
    }
}

fun countFlower(): Int {
    val temp = Array(N) { IntArray(M) {0} }
    val queue = ArrayDeque<Triple<Int, Int, Int>>()
    val checked = hashSetOf<Pair<Int, Int>>()

    gPlace.forEach {
        val (x, y) = startPlace[it]
        temp[x][y] = 1
        queue.add(Triple(x, y, 1))
    }
    rPlace.forEach {
        val (x, y) = startPlace[it]
        temp[x][y] = -1
        queue.add(Triple(x, y, -1))
    }

    while (queue.isNotEmpty()) {
        val (x, y, r) = queue.removeFirst()

        if (temp[x][y] == 0) continue

        val nr = if (r > 0) r+1 else r-1

        repeat(4) {
            val nx = x + dx[it]
            val ny = y + dy[it]

            if (nx in 0 until N && ny in 0 until M && board[nx][ny] != 0) {
                if (temp[nx][ny] == 0 && !checked.contains(nx to ny)) {
                    temp[nx][ny] = nr
                    queue.addLast(Triple(nx, ny, nr))
                }
                else if (temp[nx][ny] != nr && temp[nx][ny].absoluteValue == nr.absoluteValue) {
                    temp[nx][ny] = 0
                    checked.add(nx to ny)
                }
            }
        }
    }

    return checked.size
}