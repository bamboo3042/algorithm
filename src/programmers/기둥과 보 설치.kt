package programmers

class `기둥과 보 설치` {
    class Solution {
        fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
            val answer = mutableListOf<IntArray>()
            val beams = mutableSetOf<Pair<Int, Int>>()
            val pillars = mutableSetOf<Pair<Int, Int>>()

            fun canPlacePillar(x: Int, y: Int): Boolean {
                return y == 0 || pillars.contains(x to y - 1) || beams.contains(x - 1 to y) || beams.contains(x to y)
            }

            fun canPlaceBeam(x: Int, y: Int): Boolean {
                return pillars.contains(x to y - 1) || pillars.contains(x + 1 to y - 1) || (beams.contains(x - 1 to y) && beams.contains(x + 1 to y))
            }

            build_frame.forEach { (x, y, a, b) ->
                if (a == 0) {
                    if (b == 0) {
                        pillars.remove(x to y)
                        if (pillars.contains(x to y+1) && !canPlacePillar(x, y+1)) {
                            pillars.add(x to y)
                            return@forEach
                        }
                        if (beams.contains(x to y+1) && !canPlaceBeam(x, y+1)) {
                            pillars.add(x to y)
                            return@forEach
                        }
                        if (beams.contains(x-1 to y+1) && !canPlaceBeam(x-1, y+1)) {
                            pillars.add(x to y)
                            return@forEach
                        }
                    }
                    else {
                        if (canPlacePillar(x, y)) pillars.add(x to y)
                    }
                }
                else {
                    if (b == 0) {
                        beams.remove(x to y)
                        if (pillars.contains(x to y) && !canPlacePillar(x, y)) {
                            beams.add(x to y)
                            return@forEach
                        }
                        if (pillars.contains(x+1 to y) && !canPlacePillar(x+1, y)) {
                            beams.add(x to y)
                            return@forEach
                        }
                        if (beams.contains(x-1 to y) && !canPlaceBeam(x-1, y)) {
                            beams.add(x to y)
                            return@forEach
                        }
                        if (beams.contains(x+1 to y) && !canPlaceBeam(x+1, y)) {
                            beams.add(x to y)
                            return@forEach
                        }
                    }
                    else {
                        if (canPlaceBeam(x, y)) beams.add(x to y)
                    }
                }
            }

            beams.forEach { (x, y) -> answer.add(intArrayOf(x, y, 1)) }

            pillars.forEach { (x, y) -> answer.add(intArrayOf(x, y, 0)) }

            answer.sortWith(compareBy({it[0]}, {it[1]}, {it[2]}))

            return answer.toTypedArray()
        }
    }
}