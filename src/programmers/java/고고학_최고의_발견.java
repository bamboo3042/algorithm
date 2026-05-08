package programmers.java;

public class 고고학_최고의_발견 {
    class Solution {
        private int[] dx = {-1, 1, 0, 0};
        private int[] dy = {0, 0, -1, 1};

        private int minAnswer =  Integer.MAX_VALUE;
        
        public int solution(int[][] clockHands) {
            int[] firstRow = new int[clockHands.length];

            dfs(0, clockHands, firstRow);

            return minAnswer;
        }

        private void dfs(int n, int[][] clockHands, int[] firstRow) {
            if (n == firstRow.length) {
                simulate(clockHands, firstRow);
                return;
            }

            for (int i = 0; i < 4; i++) {
                firstRow[n] = i;

                dfs(n + 1, clockHands, firstRow);
            }
        }

        private void simulate(int[][] originClockHands, int[] firstRow) {
            int result = 0;
            int[][] clockHands = new int[originClockHands.length][originClockHands[0].length];

            for (int i = 0; i < originClockHands.length; i++) {
                clockHands[i] = originClockHands[i].clone();
            }

            for (int j = 0; j < clockHands[0].length; j++) {
                switchClockHand(clockHands, 0, j, firstRow[j]);
                result += firstRow[j];
            }

            for (int i = 1; i < clockHands.length; i++) {
                for (int j = 0; j < clockHands[i].length; j++) {
                    if (clockHands[i - 1][j] != 0) {
                        int c = 4 - clockHands[i - 1][j];
                        switchClockHand(clockHands, i, j, c);
                        result += c;
                    }
                }
            }

            if (checkRow(clockHands[clockHands.length - 1])) {
                if (result < minAnswer) minAnswer = result;
            }
        }

        private void switchClockHand(int[][] clockHands, int x, int y, int c) {
            if (c == 0) return;

            clockHands[x][y] = (clockHands[x][y] + c) % 4;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= clockHands.length || ny < 0 || ny >= clockHands[nx].length) continue;

                clockHands[nx][ny] = (clockHands[nx][ny] + c) % 4;
            }
        }

        private boolean checkRow(int[] row) {
            for (int r: row)
                if (r != 0) return false;

            return true;
        }
    }
}