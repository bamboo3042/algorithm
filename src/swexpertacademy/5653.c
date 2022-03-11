#include <stdio.h>

typedef struct{
    int wait_time;
    int active_time;
    int status;
    int base;
} cell;

int dir[4][2] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
cell map[2][352][352];
int N, M, K, maxN, maxM, x, answer;
int cur_map;

int main(void)
{
	int test_case;
	int T;
	setbuf(stdout, NULL);
	scanf("%d", &T);
	for (test_case = 1; test_case <= T; ++test_case)
	{
        int N, M, K, maxN, maxM, x, answer = 0;
        int cur_map = 0;

        scanf("%d %d %d", &N, &M, &K);
        for(int n = 0; n < N + K + 2; n++){
            for(int m = 0; m < M + K + 2; m++){
                map[0][n][m].status = 0;
                map[1][n][m].status = 0;
            }
        }
        for(int n = K/2 + 1; n < K/2+N + 1; n++){
            for(int m = K/2 + 1; m < K/2+M + 1; m++){
                scanf("%d", &x);
                if(x){
                    map[0][n][m].status = 2;
                    map[0][n][m].wait_time = x;
                    map[0][n][m].base = x;
                }
            }
        }
        maxN = N + K + 2;
        maxM = M + K + 2;
        cur_map = 0;
        for(int  k = 0; k < K; k++){
            for(int n = 0; n < maxN; n++){
                for(int m = 0; m < maxM; m++){
                    if(map[cur_map][n][m].status == 1){
                        map[1-cur_map][n][m].status = map[cur_map][n][m].status;
                    }
                    else if(map[cur_map][n][m].status == 2){
                        map[1-cur_map][n][m] = map[cur_map][n][m];
                        map[1-cur_map][n][m].wait_time--;
                        if(map[1-cur_map][n][m].wait_time == 0){
                            map[1-cur_map][n][m].status = 3;
                            map[1-cur_map][n][m].active_time = map[1-cur_map][n][m].base;
                        }
                    }
                    else if(map[cur_map][n][m].status == 3){
                        if(map[cur_map][n][m].active_time == map[cur_map][n][m].base){
                            for(int i = 0; i < 4; i++){
                                int n2 = n + dir[i][0];
                                int m2 = m + dir[i][1];
                                if(map[cur_map][n2][m2].status == 0){
                                    if(map[1-cur_map][n2][m2].status == 0){
                                        map[1-cur_map][n2][m2].status = 2;
                                        map[1-cur_map][n2][m2].base = map[cur_map][n][m].base;
                                        map[1-cur_map][n2][m2].wait_time = map[cur_map][n][m].base;
                                    }
                                    else if(map[1-cur_map][n2][m2].status == 2 && map[cur_map][n][m].base > map[1-cur_map][n2][m2].base){
                                        map[1-cur_map][n2][m2].base = map[cur_map][n][m].base;
                                        map[1-cur_map][n2][m2].wait_time = map[cur_map][n][m].base;
                                    }
                                }
                            }
                        }

                        map[1-cur_map][n][m].active_time = map[cur_map][n][m].active_time - 1;
                        map[1-cur_map][n][m].status = map[cur_map][n][m].status;
                        if(map[1-cur_map][n][m].active_time == 0) map[1-cur_map][n][m].status = 1;
                    }
                }
            }
            cur_map = 1 - cur_map;
        }
		answer = 0;
        for(int n = 0; n < maxN; n++){
            for(int m = 0; m < maxM; m++){
                if(map[cur_map][n][m].status > 1) answer++;
            }
        }

        printf("#%d %d\n", test_case, answer);
	}
	return 0; //정상종료시 반드시 0을 리턴해야 합니다.
}