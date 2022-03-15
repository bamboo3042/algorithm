#include <stdio.h>
#include <stdlib.h>
unsigned a, b, K, temp, max;
unsigned *arr;
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
        arr = (unsigned*)malloc(sizeof(unsigned*) * max);
        arr[0] = a < b ? a : b;
        temp = 0;
        do{
            if(a <= b){
                b = b - a;
                a *= 2;
            }else{
                a = a - b;
                b *= 2;
            }
            arr[++temp] = a < b ? a : b;
        }while(arr[temp] != arr[0] && K > temp);
        printf("#%u %u\n", test_case, arr[K == temp ? K : K % temp]);
        free(arr);
	}
	return 0; //정상종료시 반드시 0을 리턴해야 합니다.
}