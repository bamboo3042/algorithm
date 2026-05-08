package programmers.java;

import java.util.Arrays;
import java.util.Scanner;

public class 선인장_숨기기 {
    class Solution {
        public int[] solution(int m, int n, int h, int w, int[][] drops) {
            int[] answer = new int[2];
            int[][] map = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.MAX_VALUE;
                }
            }

            for (int i = 0; i < drops.length; i++) {
                int[] drop = drops[i];

                int dm = drop[0];
                int dn = drop[1];

                for (int tm = Math.max(dm - h + 1, 0); tm <= dm; tm++) {
                    for (int tn = Math.max(dn - w + 1, 0); tn <= dn; tn++) {
                        if (map[tm][tn] != Integer.MAX_VALUE) continue;

                        map[tm][tn] = i;
                    }
                }
            }

            int maxDrop = 0;

            for (int i = 0; i <= m - h; i++) {
                for (int j = 0; j <= n - w; j++) {
                    if (map[i][j] > maxDrop) {
                        maxDrop = map[i][j];
                        answer[0] = i;
                        answer[1] = j;
                    }

                    if (map[i][j] == Integer.MAX_VALUE) return answer;
                }
            }

            return answer;
        }
    }

    public static void main(String[] args) {

        선인장_숨기기 outer = new 선인장_숨기기();
        Solution solution = outer.new Solution();

        int m = 4;
        int n = 5;
        int h = 2;
        int w = 2;

        int[][] drops = {
                {0, 0}, {3, 1}, {1, 3}, {2, 4}, {1, 1}, {2, 2}, {2, 3}, {0, 4}
        };

        int[] result = solution.solution(m, n, h, w, drops);

        System.out.println(Arrays.toString(result));
    }
}