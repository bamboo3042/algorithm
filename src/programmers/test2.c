#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
int dir[2][4][2] = {{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}, {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}};

// n x n 배열을 할당해주는 보조함수입니다.
int** emptysquare(int size){
    int** result = (int**)malloc(sizeof(int*) * size);
    for(int i=0; i<size; i++) result[i] = (int*)calloc(size, sizeof(int));
    return result;
}

int** solution(int n, bool clockwise) {
    // return 값은 malloc 등 동적 할당을 사용해주세요. 할당 길이는 상황에 맞게 변경해주세요.
    int** answer = emptysquare(n);
    int dir_flag = 0;
    int clock_flag = clockwise ? 0 : 1;
    int d1 = 0, d2 = 1, d3 = 2, d4 = 3;
    int x1 = 0, y1 = 0;
    int x2 = clockwise ? n - 1 : 0, y2 = clockwise ? 0 : n-1;
    int x3 = n - 1, y3 = n-1;
    int x4 = clockwise ? 0 : n-1, y4 = clockwise ? n-1 : 0;
    int max = n * n / 4;
    int cnt = 0;
    if((n % 2)) max++; 
    for(int i = 1; i <= max; i++){
        if(answer[y1 + (cnt * dir[clock_flag][d1][0])][x1 + (cnt * dir[clock_flag][d1][1])] != 0){
            x1 = x1 + ((cnt-1) * dir[clock_flag][d1][1]);
            y1 = y1 + ((cnt-1) * dir[clock_flag][d1][0]);
            x2 = x2 + ((cnt-1) * dir[clock_flag][d2][1]);
            y2 = y2 + ((cnt-1) * dir[clock_flag][d2][0]);
            x3 = x3 + ((cnt-1) * dir[clock_flag][d3][1]);
            y3 = y3 + ((cnt-1) * dir[clock_flag][d3][0]);
            x4 = x4 + ((cnt-1) * dir[clock_flag][d4][1]);
            y4 = y4 + ((cnt-1) * dir[clock_flag][d4][0]);
            cnt = 1;
            d1 = (d1 + 1) % 4;
            d2 = (d2 + 1) % 4;
            d3 = (d3 + 1) % 4;
            d4 = (d4 + 1) % 4;
        }
        answer[y1 + (cnt * dir[clock_flag][d1][0])][x1 + (cnt * dir[clock_flag][d1][1])] = i;
        answer[y2 + (cnt * dir[clock_flag][d2][0])][x2 + (cnt * dir[clock_flag][d2][1])] = i;
        answer[y3 + (cnt * dir[clock_flag][d3][0])][x3 + (cnt * dir[clock_flag][d3][1])] = i;
        answer[y4 + (cnt * dir[clock_flag][d4][0])][x4 + (cnt * dir[clock_flag][d4][1])] = i;
        cnt++;
    }
    return answer;
}
