#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int test_case;
    int T;
    int n, max, min;
    char num[10], c;
    setbuf(stdout, NULL);
    scanf("%d\n", &T);
    for (test_case = 1; test_case <= T; ++test_case)
    {
        n = 0;
        scanf("%d", &max);
        min = max;
        sprintf(num, "%d\n", max);
        while(num[n++] != '\n');
        if(n == 1) printf("#%d %c %c\n", test_case, c, c);
        else{
            for(int i = 0; num[i] != '\n'; i++){
                for(int j = 0; num[j] != '\n'; j++){
                    c = num[i];
                    num[i] = num[j];
                    num[j] = c;

                    max = atoi(num) > max ? atoi(num) : max;
                    if(num[0] != '0'){
                        min = atoi(num) < min ? atoi(num) : min;
                    }

                    c = num[i];
                    num[i] = num[j];
                    num[j] = c;
                }
            }
            printf("#%d %d %d\n", test_case, min, max);
        }
    }
    return 0; //정상종료시 반드시 0을 리턴해야 합니다.
}