package programmers.java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Prob2 {
    class Solution {
        public int solution(int[] skills, int[] team, int k) {
            int answer = 0;
            boolean[] isTeam = new boolean[skills.length];

            for (int t: team) isTeam[t] = true;

            int teamCount = 0;
            int sum = 0;

            for (int i = 0; i < k; i++) {
                sum += skills[i];
                if (isTeam[i]) teamCount++;
            }

            if (teamCount == team.length) answer = sum * 2;
            else answer = sum;

            int s = 0;
            int e = k;
            while(e < skills.length) {
                sum = sum - skills[s] + skills[e];

                if (isTeam[s]) teamCount--;
                if (isTeam[e]) teamCount++;

                if (teamCount == team.length) answer = Math.max(answer, sum * 2);
                else answer = Math.max(answer, sum);

                s++;
                e++;
            }

            return answer;
        }
    }
}