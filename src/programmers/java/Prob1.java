package programmers.java;

public class Prob1 {
    class Solution {
        public int solution(int[] arr, int[] brr) {
            int answer = 0;
            int sumArr = 0;
            int sumBrr = 0;

            for (int i = 0; i < arr.length - 1; i++) {
                sumArr += arr[i];
                sumBrr += brr[i];

                if (sumArr != sumBrr) answer++;
            }

            return answer;
        }
    }
}