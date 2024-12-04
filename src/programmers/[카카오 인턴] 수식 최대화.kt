package programmers

import kotlin.math.absoluteValue

class `카카오 인턴 수식 최대화` {
    class Solution {
        fun solution(expression: String): Long {
            var answer: Long = 0
            val numbers = mutableListOf<Long>()
            val match = mutableListOf<Char>()
            var number = 0L

            expression.forEach { s ->
                if (s in listOf('*', '+', '-')) {
                    numbers.add(number)
                    number = 0
                    match.add(s)
                }
                else number = number * 10 + s.code - '0'.code
            }

            numbers.add(number)

            val priorityList = listOf(
                listOf('*', '+', '-'),
                listOf('*', '-', '+'),
                listOf('+', '*', '-'),
                listOf('+', '-', '*'),
                listOf('-', '+', '*'),
                listOf('-', '*', '+'),
            )

            priorityList.forEach { priority ->
                answer = maxOf(answer, findMax(numbers.toMutableList(), match.toMutableList(), priority))
            }

            return answer
        }

        private fun findMax(numbers: MutableList<Long>, match: MutableList<Char>, priority: List<Char>): Long {
            priority.forEach { p ->
                var count = 0

                match.forEachIndexed { index, c ->
                    if (c == p) {
                        val a = numbers.removeAt(index - count)
                        val b = numbers.removeAt(index - count)

                        numbers.add(
                            index - count,
                            when(p) {
                                '*' -> a * b
                                '+' -> a + b
                                else -> a - b
                            }
                        )

                        count++
                    }
                }

                match.removeAll { it == p }
            }

            return numbers.first().absoluteValue
        }
    }
}