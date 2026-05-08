package programmers.java;
import java.util.*;

public class Nol_0211_prob1 {
    class Solution {
        public int[] solution(int[] waiting) {
            int[] answer = {};
            Set<Integer> set = new LinkedHashSet<>();

            for (int id: waiting) {
                set.add(id);
            }

            return set.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}