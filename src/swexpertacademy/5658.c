#include <stdio.h>
#include <stdlib.h>

long long *list;
int size;
int N, K, rotate;
char *arr;

void insert(long long n){
    for(int i = 0; i < size; i++){
        if(list[i] == n) return;
    }
    list[size++] = n;
}

long long getNum(int start){
    long long sum = 0;
    for(int i = 0; i < rotate; i++){
        sum *= 16;
        if(arr[(start + i)%N] >= 65) sum += arr[(start + i)%N] - 55;
        else sum += arr[(start + i)%N] - 48;
    }
    return sum;
}

void quickSort(int first, int last)
{
    int pivot;
    int i;
    int j;
    int temp;

    if (first < last)
    {
        pivot = first;
        i = first;
        j = last;

        while (i < j)
        {
            while (list[i] >= list[pivot] && i < last)
            {
                i++;
            }
            while (list[j] < list[pivot])
            {
                j--;
            }
            if (i < j)
            {
                temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        }
        
        temp = list[pivot];
        list[pivot] = list[j];
        list[j] = temp;

        quickSort(first, j - 1);
        quickSort(j + 1, last);
    }
}

int main(void)
{
	int test_case;
	int T;
	setbuf(stdout, NULL);
	scanf("%d", &T);
	for (test_case = 1; test_case <= T; ++test_case)
	{
        size = 0;
        scanf("%d %d\n", &N, &K);
        arr = (char*)malloc(sizeof(char) * (N+1));
        list = (long long*)calloc(100, sizeof(long long));
        rotate = N/4;
        scanf("%s\n", arr);
        for(int i = 0; i < rotate; i++){
            insert(getNum(i));
            insert(getNum(i + rotate));
            insert(getNum(i + rotate*2));
            insert(getNum(i + rotate*3));
        }

        quickSort(0, 100);
        printf("#%d %lld\n", test_case, list[(K - 1)]);

        free(arr);
        free(list);

	}
	return 0; //정상종료시 반드시 0을 리턴해야 합니다.
}