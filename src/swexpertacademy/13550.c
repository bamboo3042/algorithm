#include <stdio.h>
#include <stdlib.h>
unsigned a, d, n;
unsigned *arr;
unsigned mod = 1000003;
unsigned temp;
unsigned answer = 0;
char c;
int main(void)
{
	int test_case;
	int T;
	setbuf(stdout, NULL);
	scanf("%d\n", &T);
	for (test_case = 1; test_case <= T; ++test_case)
	{
        temp = 0;
        while((c = getchar()) != ' ') temp = temp*10 + (c-'0');
        a = temp;
        temp = 0;
        while((c = getchar()) != ' ') temp = temp*10 + (c-'0');
        d = temp;
        temp = 0;
        while((c = getchar()) != '\n' && c != EOF) temp = temp*10 + (c-'0');
        n = temp;

        arr = (unsigned*)malloc(sizeof(unsigned) * mod);
        temp = 0;
        arr[temp] = a;
        do{
            a = (a + d) % mod;
            arr[temp+1] = (arr[temp] * a) % mod;
            temp++;
        }while(arr[temp] != arr[0] && n > temp);
        printf("#%d %u\n", test_case, arr[temp == n ? n - 1 : n % temp]);
        free(arr);
	}
	return 0; //정상종료시 반드시 0을 리턴해야 합니다.
}