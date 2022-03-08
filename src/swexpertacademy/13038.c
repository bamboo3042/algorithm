#include <stdio.h>
#include <math.h>
int main(void)
{
	int test_case;
	int T;
	setbuf(stdout, NULL);
	scanf("%d", &T);
	for (test_case = 1; test_case <= T; ++test_case)
	{
        int n, arr[7], weight = 0, min = 100000, p, temp;
        scanf("%d\n", &n);
        for(int i = 0; i < 7; i++){
            scanf("%d", &arr[i]);
        }
        // for(int i = 0; i < 7; i++){
        //     weight = 1000;
        //     if(arr[i]){
        //         weight = 1;
        //         for(int j = 1; j < 7; j++){
        //             if(arr[(i+j)%7]) weight += arr[(i+j)%7] * pow(2, j);
        //         }
        //     }
        //     if(weight < min){
        //         min = weight;
        //         p = i;
        //     }
        // }
        // answer = 0;
        // while(n != 0){
        //     if(arr[(p+answer)%7]) n--;
        //     answer++;
        // }
        for(int i = 0; i < 7; i++){
            if(arr[i]){
                temp = n;
                p = 0;
                while(temp > 0){
                    if(arr[(i+p++)%7]) temp--;
                }
                min = p < min ? p : min;
            }
        }
        printf("#%d %d\n", test_case, min);
	}
	return 0; //정상종료시 반드시 0을 리턴해야 합니다.
}