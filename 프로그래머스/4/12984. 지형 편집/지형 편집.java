import java.util.PriorityQueue;

public class Solution {
    public long solution(int[][] land, int P, int Q) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long removePrice = 0L;

        for (int[] row : land) {
            for (int height : row) {
                pq.add(height);
                removePrice += height;
            }
        }

        removePrice *= Q;
        long addPrice = 0L;
        long answer = removePrice;
        int index = 0;
        int prevHeight = 0;

        while (!pq.isEmpty()) {
            int height = pq.poll();
            long diff = (long) (height - prevHeight);

            addPrice += diff * index * P;
            removePrice -= diff * (pq.size() + 1) * Q;

            index++;
            prevHeight = height;

            answer = Math.min(answer, addPrice + removePrice);
        }

        return answer;
    }
}
