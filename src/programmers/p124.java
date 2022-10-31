package programmers;

public class p124 {
    class Solution {
        public String solve(int n){
            StringBuilder s = new StringBuilder();
            while(n != 0){
                s.append(n % 3);
                n /= 3;
            }
            return s.reverse().toString().replaceAll("2", "4").replaceAll("1", "2").replaceAll("0", "1");
        }

        public String solution(int n) {
            String answer = solve(n);
            return answer;
        }
    }
}
