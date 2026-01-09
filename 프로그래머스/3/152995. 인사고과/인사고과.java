import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        int[] baseScore = scores[0].clone();
        int baseSum = baseScore[0] + baseScore[1];
        int[][] scoresSorted = Arrays.stream(scores).sorted(
                Comparator.comparingInt((int[] a) -> a[0]).reversed()
                        .thenComparing((int[] a) -> a[1])
        ).toArray(int[][]::new);
        int max = 0;
        int maxSum = 0;

        for (int[] score: scoresSorted) {
            if (max > score[1]) {
                if (Arrays.equals(score, baseScore)) return -1;
                continue;
            }
            else max = score[1];

            int sum = score[0] + score[1];
            maxSum = Math.max(maxSum, sum);

            if (sum > baseSum) answer++;
        }

        if (maxSum == baseScore[0] + baseScore[1]) return 1;

        return answer + 1;
    }
}