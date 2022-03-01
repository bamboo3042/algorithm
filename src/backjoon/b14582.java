package backjoon;

import java.util.Scanner;

public class b14582 {
    public static void main(String[] args) {
        int[] arr1 = new int[9], arr2 = new int[9];
        int check = 0, s1 = 0, s2 = 0;
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < 9; i++){
            arr1[i] = sc.nextInt();
        }
        for(int i = 0; i < 9; i++){
            arr2[i] = sc.nextInt();
        }
        for(int i = 0; i < 9; i++){
            s1 += arr1[i];
            if(s1 > s2){
                check = 1;
                break;
            }
            s2 += arr2[i];
        }
        if(check == 1) System.out.println("Yes");
        else System.out.println("No");
    }
}
