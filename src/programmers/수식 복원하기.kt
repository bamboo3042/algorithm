package programmers

class `수식 복원하기` {
    class Solution {
        private val NUMBER_SET = mutableSetOf(2, 3, 4, 5, 6, 7, 8, 9)

        fun solution(expressions: Array<String>): Array<String> {
            val fixedExpressions = mutableListOf<List<String>>()
            val notFixedExpression = mutableListOf<List<String>>()

            expressions.forEach { expression ->
                expression.split(" ").let { list ->
                    if (list.last() != "X") fixedExpressions.add(list)
                    else notFixedExpression.add(list)
                }
            }

            fixedExpressions.forEach { list ->
                NUMBER_SET.removeIf {
                    !numberSystemCheck(list[0], list[2], list[4], list[1] == "+", it)
                }
            }

            notFixedExpression.forEach { list ->
                NUMBER_SET.removeIf {
                    !(numberSystemCheck(list[0], it) && numberSystemCheck(list[2], it))
                }
            }

            return notFixedExpression.map expressionMap@{ expression ->
                val numberResult = mutableListOf<String>()

                NUMBER_SET.forEach numberSetForEach@{ numberSystem ->
                    val n1 = expression[0].toSystemNumber(numberSystem)
                    val n2 = expression[2].toSystemNumber(numberSystem)
                    val n3 = if (expression[1] == "+") n1 + n2 else n1 - n2

                    val temp = n3.toSystemString(numberSystem)

                    if (numberResult.isEmpty()) numberResult.add(temp)

                    if (numberResult.first() != temp) return@expressionMap expressionModified(expression, "?")
                }

                expressionModified(expression, numberResult.first())
            }.toTypedArray()
        }

        fun expressionModified(expression: List<String>, result: String): String {
            return "${expression[0]} ${expression[1]} ${expression[2]} ${expression[3]} $result"
        }

        fun numberSystemCheck(num: String, systemNumber: Int): Boolean {
            return num.fold(true) { acc, c -> acc && c.digitToInt() < systemNumber }
        }

        fun numberSystemCheck(num1: String, num2: String, num3: String, isPlus: Boolean, systemNumber: Int): Boolean {
            if (!numberSystemCheck(num1, systemNumber)) return false
            if (!numberSystemCheck(num2, systemNumber)) return false
            if (!numberSystemCheck(num3, systemNumber)) return false

            val n1 = num1.toSystemNumber(systemNumber)
            val n2 = num2.toSystemNumber(systemNumber)
            val n3 = num3.toSystemNumber(systemNumber)

            return if (isPlus) (n1 + n2 == n3)
            else (n1 - n2 == n3)
        }

        fun String.toSystemNumber(n: Int): Int {
            var result = 0

            this.forEach {
                result *= n
                result += it.digitToInt()
            }

            return result
        }

        fun Int.toSystemString(n: Int): String {
            if (this == 0) return "0"

            var number = this
            val result = StringBuilder()

            while (number > 0) {
                result.append(number % n)
                number /= n
            }

            return result.reverse().toString()
        }
    }
}