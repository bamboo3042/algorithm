package programmers.java;

import java.util.*;

public class Prob3 {
    public long[] solution(int h, int w, int[][] blocks) {
        long[] answer = new long[h];

        Arrays.sort(blocks, (a, b) -> {
            if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
            else return Integer.compare(a[0], b[0]);
        });

        int[] blockCount = new int[h];

        for (int i = 0; i < blocks.length; i++) {
            int x = blocks[i][0];
            int y = blocks[i][1];

            if (i > 0 && blocks[i - 1][0] == x && blocks[i - 1][1] == y) continue;

            blockCount[y]++;
        }

        long sum = 0;
        for (int i = 0; i < h; i++) {
            sum += w - blockCount[i];

            answer[i] = sum;
        }

        return answer;
    }
}