#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// diagonals_rows는 2차원 배열 diagonals의 행 길이, diagonals_cols는 2차원 배열 diagonals의 열 길이입니다.
int solution(int width, int height, int** diagonals, size_t diagonals_rows, size_t diagonals_cols) {
    int answer = 0;
    int **map;
    int **temp_map;
    int dw, dh;
    int div = 10000019;
    width = width + 1;
    height = height + 1;
    map = (int**)malloc(sizeof(int*) * height);
    temp_map = (int**)malloc(sizeof(int*) * height);
    for(int i = 0; i < height; i++){
        map[i] = (int)calloc(width, sizeof(int));
        temp_map[i] = (int)calloc(width, sizeof(int));
    }

    for(int i = 1; i < width; i++) map[0][i] = 1;
    for(int i = 1; i < height; i++) map[i][0] = 1;
    for(int h = 1; h < height; h++){
        for(int w = 1; w < width; w++)
        map[h][w] = (map[h-1][w] + map[h][w-1]) % div;
    }

    for(int d = 0; d < diagonals_rows; d++){
        dw = diagonals[d][0];
        dh = diagonals[d][1];

        for(int h = 0; h < height; h++)
            for(int w = 0; w < width; w++)
                temp_map[h][w] = 0;
        
        temp_map[dh][dw-1] = map[dh-1][dw];
        temp_map[dh-1][dw] = map[dh][dw-1];
        for(int i = dh + 1; i < height; i++) temp_map[i][dw-1] = temp_map[dh][dw-1];
        for(int i = dw + 1; i < width; i++) temp_map[dh-1][i] = temp_map[dh-1][dw];
        for(int h = dh; h < height; h++){
            for(int w = dw; w < width; w++){
                temp_map[h][w] = (temp_map[h-1][w] + temp_map[h][w-1]) % div;
            }
        }

        answer += temp_map[height-1][width-1];
    }
    free(map);
    free(temp_map);
    return answer;
}