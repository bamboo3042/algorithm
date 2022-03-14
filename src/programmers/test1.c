#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// costs_len은 배열 costs의 길이입니다.
int solution(int money, int costs[], size_t costs_len) {
    int answer = 0;
    double price[6] = {0};
    int arr[] = {1, 5, 10, 50, 100, 500};
    int temp_cost = costs_len, sum, temp = 0;
    double max;
    for(int i = 0; i < costs_len; i++){
        price[i] = (double) arr[i]/costs[i];
    }
    while((money != 0 && temp_cost != 0)){
        max = 0;
        for(int i = 0; i < temp_cost; i++){
            if(max < price[i]){
                max = price[i];
                temp = i;
            }
        }
        temp_cost = temp;
        answer = answer + ((money / arr[temp]) * costs[temp]);
        money = money % arr[temp];
    }
    return answer;
}
