#include <stdio.h>

typedef struct {
    int x, y;
    int wait_time;
    int active_time;
    int status;
} cell;

cell *wait_cell[352];
cell *active_cell[352];
int wait_size;
int active_size;
int map[352][352] = {0};
int status[352][352] = {0};

void swap_cell(cell *a, cell *b){
    cell *temp = a;
    a = b;
    b = temp;
}

void wait_insert(int x, int y){
    cell *temp_cell = (cell*)malloc(sizeof(cell));
    temp_cell -> x = x;
    temp_cell -> y = y;
    temp_cell -> wait_time = map[x][y];
    wait_cell[++wait_size] = temp_cell;
    for(int i = wait_size; i > 0; i/2){
        if(wait_cell[i] -> wait_time < wait_cell[i/2] -> wait_time){
            swap_cell(wait_cell[i], wait_cell[i/2]);
        }
        else break;
    }
}

void active_insert(cell *c){
    c -> active_time = map[c -> x][c -> y];
    active_cell[++active_size] = c;
    for(int i = active_size; i > 0; i/2){
        if(active_cell[i] -> active_time < active_cell[i/2] -> active_time){
            swap_cell(active_cell[i], active_cell[i/2]);
        }
        else break;
    }
}

void wait_check(){
    int i;
    while(wait_cell[1] != NULL && wait_cell[1] -> wait_time == 0){
        active_insert(wait_cell[1]);
        wait_cell[1] = wait_cell[wait_size];
        wait_cell[wait_size--] = NULL;
        i = 1;
        while((i*2 <= wait_size && wait_cell[i*2] -> wait_time < wait_cell[i]) || (i*2 + 1 <= wait_size && wait_cell[i*2 + 1] -> wait_time < wait_cell[i])){
            if(wait_cell[i*2] -> wait_time < wait_cell[i] && wait_cell[i*2+1] -> wait_time > wait_cell[i*2]){
                swap_cell(wait_cell[i], wait_cell[i*2]);
                i *= 2;
            }
            else{
                swap_cell(wait_cell[i], wait_cell[i*2+1]);
                i = i*2 + 1;
            }
        }
    }
}

void active_check(){

}

int main(void)
{
	int test_case;
	int T;
	setbuf(stdout, NULL);
	scanf("%d", &T);
	for (test_case = 1; test_case <= T; ++test_case)
	{
	}
	return 0; //정상종료시 반드시 0을 리턴해야 합니다.
}