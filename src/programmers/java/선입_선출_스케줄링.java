package programmers.java;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 선입_선출_스케줄링 {
    class Solution {
        public int solution(int n, int[] cores) {
            if (n <= cores.length) return n;

            n -= cores.length;

            int left = 1;
            int minCores = Integer.MAX_VALUE;

            for (int core: cores) {
                if (core < minCores) minCores = core;
            }

            int right = minCores * n;
            int time = 0;

            while(left <= right) {
                int mid = (left + right) / 2;
                int count = 0;

                for (int core: cores) {
                    count += mid / core;
                    if (count >= n) break;
                }

                if (count < n) left = mid + 1;
                else {
                    time = mid;
                    right = mid - 1;
                }
            }

            int checkTime = time - 1;
            int end = 0;
            for (int core: cores) end += checkTime / core;

            int work = n - end;

            for (int i = 0; i < cores.length; i++) {
                if (time % cores[i] == 0) {
                    work--;

                    if (work == 0) return i + 1;
                }
            }

            return -1;
        }
    }
}