package programmers;

public class KAKAO_BLIND_RECRUITMENT_2020 {

    public class 문자열압축 {

        public int suchString(String s, int subLength){
            int sum = s.length();
            int count = 0;
            for(int i = 0; i < s.length() - subLength;){
                String sub = s.substring(i, i+subLength);
                i += subLength;
                count = 0;
                while(i+subLength <= s.length() && s.substring(i, i+subLength).equals(sub)){
                    sum -= subLength;
                    i += subLength;
                    count++;
                }
                if(count != 0) sum += Integer.toString(count).length();
            }
            return sum;
        }

        public int solve(String s){
            int answer = s.length();

            for(int i = s.length()/2; i > 0; i--){
                answer = Math.min(answer, suchString(s, i));
            }

            return answer;
        }

        public int solution(String s) {
            int answer = solve(s);
            return answer;
        }
    }
}
