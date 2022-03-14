package swexpertacademy;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int a, b, K;
            ArrayList<Integer> arr = new ArrayList<>();
            a = sc.nextInt();
            b = sc.nextInt();
            K = sc.nextInt();
            if(a == b){
                System.out.println("#" + test_case + " " + a);
                continue;
            }
            do{
                arr.add(Math.min(a, b));
                if(a <= b){
                    b = b - a;
                    a *= 2;
                }else {
                    a = a - b;
                    b *= 2;
                }
            }while(!(arr.contains(Math.min(a, b))) && arr.size() < K);
            System.out.println("#" + test_case + " " + (arr.size() == K ? Math.min(a, b) : arr.get(K % arr.size())));
        }
    }
}
