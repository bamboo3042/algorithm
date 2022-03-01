#include<stdio.h>

int main() {
    int n, k;
    int arr[11][11] = {0};
    scanf("%d %d", &n, &k);
    for(int i = 0; i < 11; i++){
        arr[i][0] = 1;
        arr[i][i] = 1;
    }
    
    for(int i = 1; i < 11; i++){
        for(int j = 1; j < 11; j++){
            arr[i][j] = arr[i-1][j-1] + arr[i-1][j];
        }
    }
    
    printf("%d\n", arr[n][k]);
}