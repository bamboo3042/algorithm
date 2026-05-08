package kakao

class Task2 {
/*
What is the lexicographically largest string we can obtain?
A string made of digits is lexicographically larger than some other string if it has a larger digit at the first position on which they differ.
For example, string "123" is lexicographically larger than "1234" as at the first position they differ, the first string has digit "2" and the second string has digit "1".
Write a function: fun solution(S: String): String
that, given string S, returns the lexicographically largest string we can obtain from S.
Examples:
1. Assuming S = "32581", it is optimal to replace "32" with "5" and "81" with "9". The function should return  "559".
2. Assuming S = "1119812", we can replace "11" with "2", obtaining "219812". Then we can replace "21" with "3" and "81" with "9". The function should return "3992".
3. Assuming S = "226228", we can replace "22" with "4" and "62" with" 8", obtaining "4828". The function should return "4828".
Write an efficient algorithm for the following assumptions:
the length of string S is within the range [1 .. 200,000];
string S consists only of non-zero digits (1 - 9)
* */
    fun solution(s: String): String {
        val stack = ArrayDeque<Int>()

        s.forEach { c ->
            val n = c.toNum()

            if (stack.isEmpty()) stack.addLast(n)
            else {
                if (stack.last() + n <= 9) stack.addLast(n + stack.removeLast())
                else stack.addLast(n)
            }
        }

        return stack.joinToString("")
    }

    fun Char.toNum() = this - '0'
}