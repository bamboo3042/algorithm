#include <stdio.h>
#include <stdlib.h>

int find_max(char arr[10], int n){
    char temp;
    for(int i = 0; i < n; i++){
        for(int k = 9; k > arr[i] ; k--){
            for(int j = n - 1; j > i; j--){
                if(arr[j] == k){
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    return atoi(arr);
                }
            }
        }
    }
    return atoi(arr);
}

int find_min(char arr[10], int n){
    char temp;
    for(int i = 0; i < n; i++){
        for(int j = n - 1; j > i; j--){
            for(int k = 0; k < arr[i]; k--){
                if(arr[j] == k){
                    if(i == 0 && k == 0) continue;
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    return atoi(arr);
                }
            }
        }
    }
    return atoi(arr);
}

int main(void)
{
    int test_case;
    int T;

    setbuf(stdout, NULL);
    scanf("%d\n", &T);
    for (test_case = 1; test_case <= T; ++test_case)
    {
        // 기존코드
        // int n, max, min;
        // char num[10], c;
        // n = 0;
        // scanf("%d", &max);
        // min = max;
        // sprintf(num, "%d\n", max);
        // while(num[n++] != '\n');
        // if(n == 1) printf("#%d %c %c\n", test_case, c, c);
        // else{
        //     for(int i = 0; num[i] != '\n'; i++){
        //         for(int j = 0; num[j] != '\n'; j++){
        //             c = num[i];
        //             num[i] = num[j];
        //             num[j] = c;

        //             max = atoi(num) > max ? atoi(num) : max;
        //             if(num[0] != '0'){
        //                 min = atoi(num) < min ? atoi(num) : min;
        //             }

        //             c = num[i];
        //             num[i] = num[j];
        //             num[j] = c;
        //         }
        //     }
        //     printf("#%d %d %d\n", test_case, min, max);
        // }
        char num[10];
        int n;
        scnaf("%s\n", &num);
        n = strlen(num);
        printf("#%d %d %d\n", test_case, find_max(num, n), find_min(num, n));
    }
    return 0; //정상종료시 반드시 0을 리턴해야 합니다.
}