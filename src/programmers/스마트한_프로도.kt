package programmers

import java.util.*

class 스마트한_프로도 {
    class Solution {
        fun solution(
            n: Int,
            m: Int,
            _a: IntArray,
            _b: IntArray,
            k: Int,
            m1: Int,
            m2: Int,
            _e1: IntArray,
            _e2: IntArray
        ): Array<IntArray> {
            val answer: MutableList<IntArray> = ArrayList()
            val a = _a.clone()
            val b = _b.clone()
            val e1 = _e1.clone()
            val e2 = _e2.clone()

            for (i in a.indices) {
                a[i]--
                b[i]--
            }
            for (i in e1.indices) e1[i]--
            for (i in e2.indices) e2[i]--

            val dots = BooleanArray(n)
            Arrays.fill(dots, false)

            // Set<Integer> removeE = new HashSet<>(toSet(e1));
            // Set<Integer> endE = new HashSet<>(toSet(e2));
            // Set<Integer> addE = new HashSet<>(endE);
            // addE.removeAll(removeE);
            // removeE.removeAll(endE);
            val removeE: MutableSet<Int> = HashSet()
            for (t in e1) removeE.add(t)
            val endE: MutableSet<Int> = HashSet()
            for (t in e2) endE.add(t)
            val addE: MutableSet<Int> = HashSet()
            for (t in endE) if (!removeE.contains(t)) addE.add(t)

            for (value in e1) {
                dots[a[value]] = true
                dots[b[value]] = true
            }

            var totalSize = e1.size
            while (!(removeE.isEmpty() && addE.isEmpty())) {
                if (totalSize > k - 2 && !removeE.isEmpty()) {
                    var removed = -1
                    for (it in removeE) {
                        removed = it
                        break
                    }

                    answer.add(intArrayOf(0, removed + 1))
                    removeE.remove(removed)
                    dots[a[removed]] = false
                    dots[b[removed]] = false

                    totalSize--
                    continue
                }

                var removed = -1
                for (it in addE) {
                    if (!dots[a[it]] && !dots[b[it]]) {
                        removed = it
                        break
                    }
                }

                if (removed != -1) {
                    answer.add(intArrayOf(1, removed + 1))
                    addE.remove(removed)
                    dots[a[removed]] = true
                    dots[b[removed]] = true
                    totalSize++
                }
            }

            return answer.toTypedArray<IntArray>()
        }
    }
}