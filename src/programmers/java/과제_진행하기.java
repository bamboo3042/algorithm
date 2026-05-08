package programmers.java;

import java.util.*;

public class 과제_진행하기 {
    class Solution {
        public String[] solution(String[][] plans) {
            List<String> answer = new ArrayList<>();
            ArrayList<Plan> planList = new ArrayList<>();
            Stack<Plan> stack = new Stack<>();

            for (String[] plan : plans) {
                planList.add(new Plan(plan[0], plan[1], plan[2]));
            }

            planList.sort(Comparator.comparingInt(p -> p.start));

            for (int i = 0; i < planList.size() - 1; i++) {
                Plan current = planList.get(i);
                Plan next = planList.get(i + 1);

                stack.push(current);
                int timeGap = next.start -  current.start;

                while(!stack.isEmpty() && timeGap > 0) {
                    Plan plan = stack.peek();

                    if (plan.playTime > timeGap) {
                        plan.playTime -= timeGap;
                        timeGap = 0;
                    } else {
                        timeGap -= plan.playTime;
                        answer.add(stack.pop().name);
                    }
                }
            }

            answer.add(planList.get(planList.size() - 1).name);

            while(!stack.isEmpty()) {
                answer.add(stack.pop().name);
            }

            return answer.toArray(String[]::new);
        }

        private class Plan {
            String name;
            Integer start;
            Integer playTime;

            Plan(String name, String start, String playTime) {
                this.name = name;
                String[] time = start.split(":");
                this.start = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
                this.playTime = Integer.parseInt(playTime);
            }
        }
    }
}