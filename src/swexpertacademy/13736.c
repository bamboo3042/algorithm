#include <stdio.h>
#include <stdlib.h>
int a, b, K, x, temp, max;
int **arr;
int main(void)
{
	int test_case;
	int T;
	setbuf(stdout, NULL);
	scanf("%d", &T);
	for (test_case = 1; test_case <= T; ++test_case)
	{
        scanf("%d %d %d\n", &a, &b, &K);
        if(a == b){
            printf("#%d %d\n", test_case, a);
            continue;
        }
        max = a > b ? a : b;
        arr = (int**)malloc(sizeof(int*) * 2);
        arr[0] = (int*)calloc(max, sizeof(int));
        arr[1] = (int*)calloc(max, sizeof(int));
        arr[0][0] = a;
        arr[1][0] = b;
        temp = 0;
        do{
            if(arr[0][temp] <= arr[1][temp]){
                arr[1][temp+1] = arr[1][temp] - arr[0][temp];
                arr[0][temp+1] = arr[0][temp] * 2;
            }else{
                arr[0][temp+1] = arr[0][temp] - arr[1][temp];
                arr[1][temp+1] = arr[1][temp] * 2;
            }
            temp++;
        }while(arr[0][0] != arr[0][temp] && arr[1][0] != arr[0][temp]);
        printf("#%d %d\n", test_case, arr[0][K%temp] < arr[1][K%temp] ? arr[0][K%temp] : arr[1][K%temp]);
        free(arr[0]);
        free(arr[1]);
        free(arr);
	}
	return 0; //정상종료시 반드시 0을 리턴해야 합니다.
}