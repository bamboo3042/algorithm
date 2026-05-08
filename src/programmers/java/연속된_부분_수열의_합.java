package programmers.java;

public class 연속된_부분_수열의_합 {
    class Solution {
        public int[] solution(int[] sequence, int k) {
            int[] answer = new int[]{0, Integer.MAX_VALUE};
            int left = 0;
            int right = 0;

            int sum = 0;

            while (right < sequence.length) {
                sum += sequence[right];

                while (sum > k) sum -= sequence[left++];

                if (sum == k && right - left < answer[1] - answer[0]) {
                    answer[0] = left;
                    answer[1] = right;
                }

                right++;
            }

            return answer;
        }
    }
}