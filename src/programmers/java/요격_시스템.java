package programmers.java;

import java.util.Arrays;
import java.util.Comparator;

public class 요격_시스템 {
    class Solution {
        public int solution(int[][] targets) {
            Arrays.sort(targets, Comparator.comparingInt(a -> a[1]));

            int answer = 0;
            int last = -1;

            for (int[] target : targets) {
                int s = target[0];
                int e = target[1];

                if (s >= last) {
                    answer++;
                    last = e;
                }
            }

            return answer;
        }
    }
}