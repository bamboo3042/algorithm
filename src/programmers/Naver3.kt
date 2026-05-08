package programmers

class Naver3 {
    fun solution(a: IntArray): Int {
        var answer = 0
        var i = 1

        while (i < a.size) {
            if (a[i] < a[i - 1]) {
                answer++

                val addNum = a[i] - a[i - 1] + 1
                while (i  < a.size && a[i] <= a[i - 1]) {
                    a[i] = a[i] + addNum
                    i++
                }
            }
            else i++
        }

        return answer
    }
}