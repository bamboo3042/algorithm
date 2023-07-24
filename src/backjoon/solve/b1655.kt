package backjoon.solve

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class b1655 {
    fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val sb = StringBuilder()
        val N = readLine().toInt()
        val minHeap = PriorityQueue<Int> (Collections.reverseOrder())
        val maxHeap = PriorityQueue<Int> ()
        repeat(N) {
            val temp = readLine().toInt()

            if (minHeap.isEmpty()) minHeap.add(temp)
            else if (maxHeap.isEmpty()) minHeap.add(temp)
            else {
                if (temp <= maxHeap.peek()) minHeap.add(temp)
                else {
                    minHeap.add(maxHeap.poll())
                    maxHeap.add(temp)
                }
            }
            if (minHeap.size - maxHeap.size == 2) maxHeap.add(minHeap.poll())

            sb.append(minHeap.peek()).appendLine()
        }
        println(sb)
    }
}