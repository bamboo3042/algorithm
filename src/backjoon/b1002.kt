package backjoon

import java.util.Scanner
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class b1002 {
    fun main()  = with(Scanner(System.`in`)) {
        var tryCount = this.nextInt()
        nextLine()
        while(tryCount-- > 0) {
            val list = nextLine().split(" ").map { it.toInt() }
            val answer: Int
            val x = abs(list[0] - list[3]).toDouble().pow(2.0)
            val y = abs(list[1] - list[4]).toDouble().pow(2.0)
            val r1 = list[2]
            val r2 = list[5]
            val dis = sqrt(x + y)

            if(dis == 0.0) {
                answer = if(r1 == r2) -1 else 0
            } else {
                if(r1 > r2) {
                    answer = if (r1 > dis) {
                        if(r1.toDouble() > (r2 + dis)) 0 else if(r1.toDouble() == (r2 + dis)) 1 else 2
                    }
                    else {
                        if(dis > (r1 + r2)) 0 else if(dis == (r1 + r2).toDouble()) 1 else 2
                    }
                }
                else if(r2 > r1) {
                    answer = if(r2 > dis) {
                        if(r2.toDouble() > (r1 + dis)) 0 else if(r2.toDouble() == (r1 + dis)) 1 else 2
                    } else {
                        if(dis > (r1 + r2)) 0 else if(dis == (r1 + r2).toDouble()) 1 else 2
                    }
                }
                else {
                    answer = if(dis == (r1 + r2).toDouble()) 1
                    else if(dis > (r1 + r2).toDouble()) 0
                    else 2
                }
            }
            println(answer)
        }
    }
}