package programmers.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class 서버_증설_횟수 {
    class Solution {
        public int solution(int[] players, int m, int k) {
            int answer = 0;
            int serverCount = 0;
            Map<Integer, Integer> map = new HashMap<>();

            for(int i = 0; i < 23; i++) {
                int p = players[i];
                int prevCount = serverCount;

                if (m * serverCount < p) while ((serverCount + 1) * m < p) serverCount++;

                System.out.printf("h: %d, p: %d, server: %d, count: %d\n", i, p, serverCount, serverCount - prevCount);

                map.put(k + i, serverCount - prevCount);
                answer += serverCount - prevCount;

                serverCount -= map.getOrDefault(i, 0);
            }

            return answer;
        }
    }
}