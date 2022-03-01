#include<stdio.h>

int main() {
    
    int arr[100002] = {0};
    int N, x, size = 0, temp, left, right;
    
    scanf("%d", &N);

    while(N--){
        scanf("%d", &x);
        if(x == 0){
            if(size == 0){
                printf("0\n");
                continue;
            }
            printf("%d\n", arr[1]);
            arr[1] = arr[size--];
            temp = 1;
            left = temp*2;
            right = temp*2+1;
            while(left <= size && (arr[right] < arr[temp] || arr[left] < arr[temp])){
                if(arr[left] < arr[temp] && arr[left] < arr[right]){
                    arr[left] += arr[temp];
                    arr[temp] = arr[left] - arr[temp];
                    arr[left] = arr[left] - arr[temp];
                    temp = left;
                }
                else if(right <= size && arr[right] < arr[temp]){
                    arr[right] += arr[temp];
                    arr[temp] = arr[right] - arr[temp];
                    arr[right] = arr[right] - arr[temp];
                    temp = right;
                }
                left = temp*2;
                right = temp*2+1;
            }
        }
        else{
            size++;
            arr[size] = x;
            temp = size;
            while( temp > 0 && arr[temp] < arr[temp/2]){
                arr[temp] += arr[temp/2];
                arr[temp/2] = arr[temp] - arr[temp/2];
                arr[temp] = arr[temp] - arr[temp/2];
                temp /= 2;
            }
        }
    }
}