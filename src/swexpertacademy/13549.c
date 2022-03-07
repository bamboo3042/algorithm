#include <stdio.h>
#include <stdlib.h>

int *seg;
int *arr;
int cap = 0;

int gcd(int a, int b){
    int r;
    
    if(a < b){
        r = a;
        a = b;
        b = r;
    }
    while(b != 0){
        r = a % b;
        a = b;
        b = r;
    }
    return a;
}

void init(int n){
    int i = 0, j = 0;
    for(cap = 1; cap < n; cap *= 2);
    seg = (int*)malloc(sizeof(int) * cap * 2);
    for(i = cap; i < cap + n; i++) seg[i] = arr[j++];
    for(; i < cap * 2; i++) seg[i] = 0;
    for(i = cap - 1; i > 0; i--) seg[i] = gcd(seg[i*2], seg[i*2+1]);
}

void update(int t, int data){
    int i = cap + t;
    seg[i] = data;
    for(i = i/2; i > 0; i /= 2) seg[i] = gcd(seg[i*2], seg[i*2+1]);
}

int main(void)
{
	int test_case;
	int T;
    int n, i, j, g, temp;
	setbuf(stdout, NULL);
	scanf("%d", &T);
	for (test_case = 1; test_case <= T; ++test_case)
	{
        scanf("%d", &n);
        arr = (int*)malloc(sizeof(int)*n);
        for(i = 0; i < n; i++) scanf("%d", &arr[i]);
        init(n);
        g = seg[1];
        for(i = 0; i < n; i++){
            temp = seg[cap + i];
            update(i, 0);
            g = g > seg[1] ? g : seg[1];
            update(i, temp);
        }
        printf("#%d %d\n", test_case, g);
        free(arr);
        free(seg);
	}
	return 0; //정상종료시 반드시 0을 리턴해야 합니다.
}