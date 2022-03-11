#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

int* solution(const char* enroll[], size_t enroll_len, const char* referral[], size_t referral_len, const char* seller[], size_t seller_len, int amount[], size_t amount_len) {
    int *answer = (int*)calloc(enroll_len, sizeof(int));
    int *referral_num = (int*)malloc(referral_len*sizeof(int));
    int temp, next_pay;
    for(int i = 0; i < referral_len; i++){
        for(int j = 0; j < enroll_len; j++){
            if(!strcmp(enroll[j], referral[i])){
                referral_num[i] = j;
                break;
            }
        }
    }
    for(int i = 0; i < seller_len; i++){
        for(int j = 0; j < enroll_len; j++){
            if(strcmp(seller[i], enroll[j]) == 0){
                temp = j;
                break;
            }
        }
        answer[temp] += amount[i] * 90;
        next_pay = amount[i] * 10;
        while(next_pay > 0 && strcmp(referral[temp], "-")){
            temp = referral_num[temp];
            answer[temp] += next_pay - (next_pay/10);
            next_pay = next_pay/10;
        }
    }
    return answer;
}