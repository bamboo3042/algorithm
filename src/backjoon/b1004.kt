package backjoon

import java.util.*
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class b1004 {
    fun b1004() = with(Scanner(System.`in`)) {
        val t = nextLine().toInt()
        repeat(t) {
            val x1 = nextInt()
            val y1 = nextInt()
            val x2 = nextInt()
            val y2 = nextInt()
            var answer = 0
            repeat(nextInt()) {
                val cx = nextInt()
                val cy = nextInt()
                val r = nextInt()
                val dis1 = sqrt(abs(x1 - cx).toDouble().pow(2) + abs(y1 - cy).toDouble().pow(2))
                val dis2 = sqrt(abs(x2 - cx).toDouble().pow(2) + abs(y2 - cy).toDouble().pow(2))
                if(dis1 > r && dis2 < r) answer += 1
                else if(dis1 < r && dis2 > r) answer += 1
            }

            println(answer)
        }
    }
}