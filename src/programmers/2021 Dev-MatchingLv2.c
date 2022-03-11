#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// queries_row_len은 2차원 배열 queries의 행(세로) 길이입니다.
// queries_col_len은 2차원 배열 queries의 열(가로) 길이입니다.
// queries[i][j]는 queries의 i번째 행의 j번째 열에 저장된 값을 의미합니다.
int* solution(int rows, int columns, int **queries, size_t queries_row_len, size_t queries_col_len) {
    // return 값은 malloc 등 동적 할당을 사용해주세요. 할당 길이는 상황에 맞게 변경해주세요.
    int* answer = (int*)malloc(queries_row_len * sizeof(int));
    int **num = (int**)malloc(rows * sizeof(int*));
    int x1, x2, y1, y2, temp, min, size = 0;
    for(int i = 0; i < rows; i++){
        num[i] = (int*)malloc(columns * sizeof(int));
        for(int  j = 0; j < columns; j++){
            num[i][j] = i * columns + j + 1;
        }
    }

    for(int r = 0; r < queries_row_len; r++){
        min = 100001;
        x1 = queries[r][0] - 1;
        x2 = queries[r][2] - 1;
        y1 = queries[r][1] - 1;
        y2 = queries[r][3] - 1;

        temp = num[x1][y1];
        min = temp < min ? temp : min;
        for(int i = y1 + 1; i <= y2; i++){
            temp = temp + num[x1][i];
            num[x1][i] = temp - num[x1][i];
            temp = temp - num[x1][i];
            min = temp < min ? temp : min;
        }
        for(int i = x1 + 1; i <= x2; i++){
            temp = temp + num[i][y2];
            num[i][y2] = temp - num[i][y2];
            temp = temp - num[i][y2];
            min = temp < min ? temp : min;
        }
        for(int i = y2 - 1; i >= y1; i--){
            temp = temp + num[x2][i];
            num[x2][i] = temp - num[x2][i];
            temp = temp - num[x2][i];
            min = temp < min ? temp : min;
        }
        for(int i = x2 - 1; i >= x1; i--){
            temp = temp + num[i][y1];
            num[i][y1] = temp - num[i][y1];
            temp = temp - num[i][y1];
            min = temp < min ? temp : min;
        }
        answer[size++] = min;
    }
    return answer;
}