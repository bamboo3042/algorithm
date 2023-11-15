import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        List<Integer> b = new ArrayList<>(Arrays.asList(Arrays.stream(B).boxed().toArray(Integer[]::new)));
        b.sort(null);
        
        for (int a : A) {
            int i = searchIndex(b, a+1);
            if (b.size() == i || b.get(i) <= a)
                b.remove(0);
            else {
                b.remove(i);
                answer++;
            }
        }

        return answer;
    }

    private int searchIndex(List<Integer> b, int n) {
        int s = 0;
        int e = b.size() - 1;
        int m = 0;

        while (s <= e) {
            m = (s + e) / 2;

            if (b.get(m) < n)
                s = m + 1;
            else
                e = m - 1;
        }
        
        return s;
    }
}
