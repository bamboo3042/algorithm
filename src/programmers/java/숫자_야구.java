package programmers.java;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;

public class 숫자_야구 {
    class Solution {
        public int solution(int n, Function<Integer, String> submit) {
            List<Integer> list = getFullList();

            while(list.size() != 1) {
                Integer checkNum = list.get(0);
                String result = submit.apply(checkNum);

                int strike = result.charAt(0) - '0';
                int ball = result.charAt(3) - '0';

                list = checkList(list, checkNum, strike, ball);
            }

            return list.get(0);
        }

        public List<Integer> checkList(List<Integer> list, Integer temp, Integer strike, Integer ball) {
            List<Integer> result = new ArrayList<>();
            String tempStr = temp.toString();
            Set<Character> set = new HashSet<>();
            int s, b;

            for (int i = 0; i < tempStr.length(); i++) set.add(tempStr.charAt(i));

            for (Integer l: list) {
                String tempS = l.toString();
                s = 0;
                b = 0;

                for (int i = 0; i < 4; i++) {
                    if (tempS.charAt(i) == tempStr.charAt(i)) s++;
                    else if (set.contains(tempS.charAt(i))) b++;
                }

                if (s == strike && b == ball) result.add(l);
            }

            return result;
        }

        public List<Integer> getFullList() {
            List<Integer> result = new ArrayList<>();

            for (int a = 1; a < 10; a++) {
                for (int b = 1; b < 10; b++) {
                    if (b == a) continue;
                    for (int c = 1; c < 10; c++) {
                        if (c == a || c == b) continue;
                        for (int d = 1; d < 10; d++) {
                            if (d == a || d == b || d == c) continue;

                            result.add(a * 1000 + b * 100 + c * 10 + d);
                        }
                    }
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        Integer targetNum = 1952;
        Function<Integer, String> submit = (num) -> {
            String str = targetNum.toString();
            String checkStr = num.toString();

            int strike = 0;
            int ball = 0;

            for (int i = 0; i < 4; i++) {
                if (str.charAt(i) == checkStr.charAt(i)) strike++;
                else if (str.contains(String.valueOf(checkStr.charAt(i)))) ball++;
            }

            return MessageFormat.format("{0}S {1}B", strike, ball);
        };

        숫자_야구 outer = new 숫자_야구();
        Solution solution = outer.new Solution();

        System.out.println(solution.solution(0, submit));
    }
}