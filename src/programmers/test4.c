#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

long long **map;

long long distance(int a, int b, int** edges, size_t edges_rows){
    int k;
    if(map[a][b]) return map[a][b];
    else if(a == b) return 0;

    for(int r1 = 0; r1 < edges_rows; r1++){
        if(edges[r1][0] == a){
            k = edges[r1][1];
            for(int r2 = 0; r2 < edges_rows; r2++){
                if(edges[r2][0] == k && edges[r2][1] == b){
                    map[a][b] = distance(a, k, edges, edges_rows) + distance(k, b, edges, edges_rows);
                    map[b][a] = map[a][b];
                    return map[a][b];
                }else if(edges[r2][1] == k && edges[r2][0] == b){
                    map[a][b] = distance(a, k, edges, edges_rows) + distance(k, b, edges, edges_rows);
                    map[b][a] = map[a][b];
                    return map[a][b];
                }
            }
        }
        else if(edges[r1][1] == a){
            k = edges[r1][0];
            for(int r2 = 0; r2 < edges_rows; r2++){
                if(edges[r2][0] == k && edges[r2][1] == b){
                    map[a][b] = distance(a, k, edges, edges_rows) + distance(k, b, edges, edges_rows);
                    map[b][a] = map[a][b];
                    return map[a][b];
                }else if(edges[r2][1] == k && edges[r2][0] == b){
                    map[a][b] = distance(a, k, edges, edges_rows) + distance(k, b, edges, edges_rows);
                    map[b][a] = map[a][b];
                    return map[a][b];
                }
            }
        }
    }
    return map[a][b];
}

// edges_rows는 2차원 배열 edges의 행 길이, edges_cols는 2차원 배열 edges의 열 길이입니다.
long long solution(int n, int** edges, size_t edges_rows, size_t edges_cols) {
    printf("1\n");
    long long answer = 0;
    int x, y;
    map = (long long**)malloc(n * sizeof(long long*));
    printf("2\n");
    for(int i = 0; i < n; i++) map[i] = (long long*)calloc(n, sizeof(long long));
    for(int r = 0; r < edges_rows; r++){
        int i = edges[r][0];
        int j = edges[r][1];
        map[i][j] = 1;
        map[j][i] = 1;
    }
    printf("3\n");
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            if(i == j) continue;
            for(int k = 0; k < n; k++){
                if(j == k) continue;
                printf("i %d j %d k %d i - j %d j - k %d i - k %d\n", i, j, k, distance(i, j, edges, edges_rows), distance(j, k, edges, edges_rows), distance(i, k, edges, edges_rows));
                if(distance(i, k, edges, edges_rows) == distance(i, j, edges, edges_rows) + distance(j, k, edges, edges_rows)){
                    answer++;
                }
            }
        }
    }
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            printf("%d ", map[i][j]);
        }
        printf("\n");
    }
    return answer;
}

int main(int argc, char const *argv[])
{
    int num = 4;
    int **arr = (int**)malloc(sizeof(int) * 4);
    for(int i = 0; i < 4; i++) arr[i] = (int*)malloc(sizeof(int) * 2);
    arr[0][0] = 0;
    arr[0][1] = 1;
    arr[1][0] = 1;
    arr[1][1] = 2;
    arr[2][0] = 2;
    arr[2][1] = 3;
    printf("%lld\n", solution(num, arr, 3, 2));
    return 0;
}
