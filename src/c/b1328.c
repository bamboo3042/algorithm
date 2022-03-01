#include<stdio.h>

int main() {
    int n, l, r;
    long long arr[101][101][101] = {0};
    scanf("%d %d %d", &n, &l, &r);
    
    arr[1][1][1] = 1;
    arr[2][1][2] = 1;
    arr[2][2][1] = 1;
    for(int i = 3; i <= n; i++){
        for(int j = 1; j <= l; j++){
            for(int k = 1; k <= r; k++){
                if(i == j && k == 1){
                    arr[i][j][k] = 1;
                }
                else if(i == k && j == 1){
                    arr[i][j][k] = 1;
                }
                else{
                    arr[i][j][k] = ((arr[i-1][j][k]*(i-2))%1000000007 + arr[i-1][j-1][k] + arr[i-1][j][k-1])%1000000007;
                }
            }
        }
    }
    printf("%lld\n", arr[n][l][r]);
}