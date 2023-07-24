package backjoon.solve;

import java.util.Scanner;

public class b14732 {
    public static void main(String[] args) {
        int[][] arr = new int[501][501];
        int n, x1, x2, y1, y2, sum = 0;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++){
            x1 = sc.nextInt();
            y1 = sc.nextInt();
            x2 = sc.nextInt();
            y2 = sc.nextInt();
            for(int x = x1; x < x2; x++){
                for(int y = y1; y < y2; y++){
                    if(arr[x][y] == 0){
                        sum++;
                        arr[x][y] = 1;
                    }
                }
            }
        }

        System.out.println(sum);
    }
}
