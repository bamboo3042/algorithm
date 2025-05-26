public class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int position = 1;
        int index = 0;

        while (position <= n) {
            if (index < stations.length && position >= stations[index] - w) {
                position = stations[index] + w + 1;
                index++;
            } else {
                answer++;
                position += 2 * w + 1;
            }
        }

        return answer;
    }
}
