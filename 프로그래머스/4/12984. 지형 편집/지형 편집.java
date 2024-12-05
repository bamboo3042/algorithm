import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public long solution(int[][] land, int P, int Q) {
        List<Integer> heightList = new ArrayList<>();
        
        for (int[] row : land) {
            for (int height : row) {
                heightList.add(height);
            }
        }

        Collections.sort(heightList);

        long addPrice = 0L;
        long removePrice = heightList.stream().mapToLong(Integer::longValue).sum() * Q;
        long answer = removePrice;

        for (int index = 0; index < heightList.size(); index++) {
            long diff = 0;
            
            if(index > 0) diff = (long) (heightList.get(index) - heightList.get(index - 1));
            else diff = heightList.get(0);

            addPrice += diff * index * P;
            removePrice -= diff * (heightList.size() - index) * Q;

            answer = Math.min(answer, addPrice + removePrice);
        }

        return answer;
    }
}
