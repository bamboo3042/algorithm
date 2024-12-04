package programmers

class `광고 삽입` {
    class Solution {
        fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
            val playTime = play_time.toTime()
            val advTime = adv_time.toTime()
            val times = LongArray(playTime + 1) { 0 }

            logs.forEach {
                val (start, end) = it.split("-").map { it.toTime() }

                times[start] += 1L
                times[end] -= 1L
            }

            for (i in 1 .. playTime) times[i] += times[i - 1]
            for (i in 1 .. playTime) times[i] += times[i - 1]

            var sum = times[advTime - 1]
            var start = 0

            for (i in advTime until playTime ) {
                if (sum < times[i] - times[i - advTime]) {
                    sum = times[i] - times[i - advTime]
                    start = i - advTime + 1
                }
            }

            return start.toTimeString()
        }

        fun String.toTime(): Int {
            return this.split(":").map { it.toInt() }.let {
                it[0] * 3600 + it[1] * 60 + it[2]
            }
        }

        fun Int.toTimeString(): String {
            var time = this
            val hour = (time / 3600).toString().let { if (it.length == 1) "0$it" else it}
            time %= 3600
            val minute = (time / 60).toString().let { if (it.length == 1) "0$it" else it}
            time %= 60
            val second = time.toString().let { if (it.length == 1) "0$it" else it}
            return "$hour:$minute:$second"
        }
    }
}