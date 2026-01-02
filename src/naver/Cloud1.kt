package naver

class Cloud1 {
    fun solution(visits: Array<String>): Int {
        var answer = 0
        var temp = WEEK.Sun.day

        visits.forEach { v ->
            val nt = WEEK.valueOf(v).day

            if (nt <= temp) answer += 1

            temp = nt
        }

        return answer
    }

    enum class WEEK(val day: Int) {
        Mon(1),
        Tue(2),
        Wed(3),
        Thu(4),
        Fri(5),
        Sat(6),
        Sun(7),
    }
}