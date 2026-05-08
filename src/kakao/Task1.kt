package kakao

class Task1 {
/*
You are given a string S consisting of N digits. What is the largest sum of two two-digit fragments of S? The selected fragments cannot overlap.
Write a function:
fun solution(S: String): Int
that, given a string S, returns the largest sum of two two-digit numbers that are non-overlapping fragments of S.
Examples:
1. Given S = "43798", the function should return 141. The chosen fragments are "43" and "98": "43 7 98"
2. Given S = "00101", the function should return 10. The chosen fragments are "00" and "10": "00 10 1". Note that fragments "01" and "10" cannot be chosen as they overlap.
3. Given S = "0332331", the function should return 66. The chosen fragments are "33" and "33": "0 33 2 33 1".
4. Given S = "00331", the function should return 34. The chosen fragments are "03" and "31": "0 03 31"
Assume that:
N is an integer within the range [4 .. 100]
string S is made only of digits (0 - 9).
In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
* */
    fun solution(s: String): Int {
        val sum = IntArray(s.length - 1) { 0 }
        var max = 0

        for (i in 0 until s.length - 1) {
            sum[i] = (s[i].toNum() * 10) + s[i+1].toNum()
        }

        for(i in 0 until s.length - 2) {
            for (j in i + 2 until s.length - 1) {
                max = maxOf(max, sum[i] + sum[j])
            }
        }

        return max
    }

    fun Char.toNum() = this - '0'

}