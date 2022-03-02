#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int* answer;

void quadTree(int** arr, int left, int right, int top, int bottom){
    int check = 1;
    
    for(int i = left; i < right; i++){
        for(int j = top; j < bottom; j++){
            if(arr[i][j] != arr[left][top]){
                check = 0;
                break;
            }
        }
    }
    if(check) answer[arr[left][top]]++;
    else{
        quadTree(arr, left, (left + right)/2, top, (top + bottom)/2);
        quadTree(arr, (left + right)/2, right, top, (top + bottom)/2);
        quadTree(arr, left, (left + right)/2, (top + bottom)/2, bottom);
        quadTree(arr, (left + right)/2, right, (top + bottom)/2, bottom);
    }
}



// arr_rows는 2차원 배열 arr의 행 길이, arr_cols는 2차원 배열 arr의 열 길이입니다.
int* solution(int** arr, size_t arr_rows, size_t arr_cols) {
    // return 값은 malloc 등 동적 할당을 사용해주세요. 할당 길이는 상황에 맞게 변경해주세요.
    answer = (int*)malloc(sizeof(int) * 2);
    answer[0] = 0, answer[1] = 0;
    quadTree(arr, 0, arr_rows, 0, arr_cols);
    
    return answer;
}