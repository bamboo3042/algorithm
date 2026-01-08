class Solution {
    public int solution(int[] sticker) {
        int[] temp = new int[4];
        int[] next = new int[4];

        temp[1] = sticker[0];

        for (int i = 1; i < sticker.length - 1; i++) {
            next[0] = Math.max(temp[0], temp[1]);
            next[1] = temp[0] + sticker[i];
            next[2] = Math.max(temp[2], temp[3]);
            next[3] = temp[2] + sticker[i];

            temp = next.clone();
        }

        int answer;

        answer = Math.max(temp[0], temp[1]);
        answer = Math.max(answer, temp[3]);
        answer = Math.max(answer, temp[2] + sticker[sticker.length-1]);

        return answer;
    }
}