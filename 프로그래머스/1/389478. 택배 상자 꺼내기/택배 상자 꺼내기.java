class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        num--;

        while(num < n) {
            num = num + w * 2 - 2 * (num % w) - 1;
            answer++;
        }

        return answer;
    }
}