import java.util.*;
class Solution {
    public int check(int[] answers, int[] arr){
        int sum = 0;
        int p = 0;
        for(int i: answers){
            if(i == arr[p++]){
                sum++;
            }
            if(p == arr.length) p = 0;
        }
        return sum;
    }
    
    public List<Integer> solution(int[] answers) {
        List<Integer> answer = new ArrayList<>();
        int[] arr1 = {1,2,3,4,5}, arr2 = {2,1,2,3,2,4,2,5}, arr3 = {3,3,1,1,2,2,4,4,5,5};
        int sum1 = check(answers, arr1), sum2 = check(answers, arr2), sum3 = check(answers, arr3);
        if(sum1 >= sum2 && sum1 >= sum3) answer.add(1);
        if(sum2 >= sum1 && sum2 >= sum3) answer.add(2);
        if(sum3 >= sum1 && sum3 >= sum2) answer.add(3);
        return answer;
    }
}