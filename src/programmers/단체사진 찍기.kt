package programmers

import kotlin.math.absoluteValue

class `단체사진 찍기` {
    class Solution {
        fun solution(n: Int, data: Array<String>): Int {
            val answer = 0

            val list = getPermutations("ACFJMNRT")

            list.filter { l ->
                data.forEach { d ->
                    val n = (l.indexOf(d[0]) - l.indexOf(d[2])).absoluteValue - 1
                    val m = d[4].code - '0'.code

                    when(d[3]) {
                        '=' -> if (n != m) return@filter false
                        '>' -> if (n <= m) return@filter false
                        else -> if (n >= m) return@filter false
                    }
                }

                true
            }
            return answer
        }

        private fun getPermutations(s: String): List<String> {
            fun permute(chars: CharArray, l: Int, r: Int, result: MutableList<String>) {
                if (l == r) {
                    result.add(String(chars))
                } else {
                    for (i in l..r) {
                        chars.swap(l, i)
                        permute(chars, l + 1, r, result)
                        chars.swap(l, i)
                    }
                }
            }

            val result = mutableListOf<String>()
            permute(s.toCharArray(), 0, s.length - 1, result)
            return result
        }

        private fun CharArray.swap(i: Int, j: Int) {
            val temp = this[i]
            this[i] = this[j]
            this[j] = temp
        }
    }
}