package backjoon;

import java.util.*;

public class Solution_JAVA {

    public class Solution {

        public int[][] solution(int n, int m, int[] _a, int[] _b, int k, int m1, int m2, int[] _e1, int[] _e2) {
            List<int[]> answer = new ArrayList<>();
            int[] a = _a.clone();
            int[] b = _b.clone();
            int[] e1 = _e1.clone();
            int[] e2 = _e2.clone();

            for (int i = 0; i < a.length; i++) {
                a[i]--;
                b[i]--;
            }
            for (int i = 0; i < e1.length; i++) e1[i]--;
            for (int i = 0; i < e2.length; i++) e2[i]--;

            boolean[] dots = new boolean[n];
            Arrays.fill(dots, false);

            // Set<Integer> removeE = new HashSet<>(toSet(e1));
            // Set<Integer> endE = new HashSet<>(toSet(e2));
            // Set<Integer> addE = new HashSet<>(endE);
            // addE.removeAll(removeE);
            // removeE.removeAll(endE);

            Set<Integer> removeE = new HashSet<>();
            for (int t: e1) removeE.add(t);
            Set<Integer> endE = new HashSet<>();
            for (int t: e2) endE.add(t);
            Set<Integer> addE = new HashSet<>();
            for (int t: endE) if (!removeE.contains(t)) addE.add(t);

            for (int value : e1) {
                dots[a[value]] = true;
                dots[b[value]] = true;
            }

            int totalSize = e1.length;
            while (!(removeE.isEmpty() && addE.isEmpty())) {
                if (totalSize > k - 2 && !removeE.isEmpty()) {
                    int removed = -1;
                    for (int it : removeE) {
                        removed = it;
                        break;
                    }

                    answer.add(new int[]{0, removed + 1});
                    removeE.remove(removed);
                    dots[a[removed]] = false;
                    dots[b[removed]] = false;

                    totalSize--;
                    continue;
                }

                int removed = -1;
                for (int it: addE) {
                    if (!dots[a[it]] && !dots[b[it]]) {
                        removed = it;
                        break;
                    }
                }

                if (removed != -1) {
                    answer.add(new int[]{1, removed + 1});
                    addE.remove(removed);
                    dots[a[removed]] = true;
                    dots[b[removed]] = true;
                    totalSize++;
                }
            }

            return answer.toArray(new int[0][]);
        }
    }
}
