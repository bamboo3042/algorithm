#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

typedef struct _node{
    struct _node *next;
    struct _node *prev;
}node;

node *head;

bool pop(){
    node *temp = head;
    if(head == NULL) return false;
    
    while(temp -> next != NULL) temp = temp -> next;
    
    if(temp == head){
        free(head);
        head = NULL;
    }else{
        temp -> prev -> next = NULL;
        free(temp);
    }
    return true;
}

void push(){
    node *temp = head;
    node *newNode = (node*)malloc(sizeof(node));
    newNode -> next = NULL;
    newNode -> prev = NULL;
    if(head == NULL) head = newNode;
    else{
        while(temp -> next != NULL) temp = temp -> next;
        newNode -> prev = temp;

        temp -> next = newNode;        
    }
}

void printNode(){
    node *temp = head;
    if(temp == NULL) return;
    
    while(temp -> next != NULL){
        printf("(");
        temp = temp -> next;
    }
}


// 파라미터로 주어지는 문자열은 const로 주어집니다. 변경하려면 문자열을 복사해서 사용하세요.
bool solution(const char* s) {
    bool answer = true;
        
    for(int i = 0; i < strlen(s); i++){
        if(s[i] == '(') pop();
        else{
            if(!pop()) return false;
        }
        printNode();
    }
    
    return answer;
}

int main(int argc, char const *argv[])
{
    /* code */
    return 0;
}
