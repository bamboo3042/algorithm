package programmers

class `1차_셔틀버스` {

    class Solution {
        fun solution(n: Int, t: Int, m: Int, timetable: Array<String>): String {
            timetable.sort()
            var answer = timeToString(strToTime(timetable[0]) - 1)
            val bus = IntArray(n+1) { 540 + (it*t) }
            var index = 0


            bus.forEach { time ->
                var count = 0
                while (count < m && strToTime(timetable[index]) <= time) {
                    count++
                    index++
                }

                if (count < m) answer = timeToString(time)
                else if (index > 0) {
                    answer = timeToString(strToTime(timetable[index-1])-1)
                }
            }

            return answer
        }

        fun strToTime(str: String): Int {
            val (h, m) = str.split(":").map { it.toInt() }
            return h * 60 + m
        }

        fun timeToString(time: Int): String = "${if (time/60 < 10) "0" + time/60 else time/60}:${if (time%60 < 10) "0" + time%60 else time%60}"
    }

}