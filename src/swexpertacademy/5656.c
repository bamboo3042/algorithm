#include <stdio.h>
#include <string.h>
int N, W, H, answer;

void check_map(int map[][12]){
    int cnt = 0;
    for(int h = 0; h < H; h++)
        for(int w = 0; w < W; w++)
            if(map[h][w]) cnt++;
    answer = cnt < answer ? cnt : answer;
};

void bomb(int map[][12], int w, int h){
    int temp = map[h][w] - 1, mx, Mx, my, My;
    mx = w - temp > 0 ? w - temp : 0;
    Mx = w + temp < W ? w + temp : W - 1;
    my = h - temp > 0 ? h - temp : 0;
    My = h + temp < H ? h + temp : H - 1;
    map[h][w] = 0;
    for(int x = mx; x <= Mx; x++){
        if(map[h][x] > 1) bomb(map, x, h);
        else map[h][x] = 0;
    }
    for(int y = my; y <= My; y++){
        if(map[y][w] > 1) bomb(map, w, y);
        else map[y][w] = 0;
    }
}

void drop(int map[][12]){
    for(int w = 0; w < W; w++){
        for(int h = H - 1; h > -1; h--){
            if(map[h][w] == 0){
                for(int y = h - 1; y > -1; y--){
                    if(map[y][w]){
                        map[h][w] = map[y][w];
                        map[y][w] = 0;
                        break;
                    }
                }
            }
        }
    }
}

void shot(int map[][12], int time){
    int temp_map[15][12];
    if(answer == 0) return;
    check_map(map);
    if(time == 0) return;
    for(int w = 0; w < W; w++){
        for(int h = 0; h < H; h++){
            if(map[h][w]){
                memcpy(temp_map, map, sizeof(int) * 15 * 12);
                bomb(temp_map, w, h);
                drop(temp_map);
                shot(temp_map, time - 1);
                break;
            }
        }
    }
}

int main(void)
{
	int test_case;
	int T;
	setbuf(stdout, NULL);
	scanf("%d", &T);
	for (test_case = 1; test_case <= T; ++test_case)
	{
        int map[15][12];
        answer = 100000;
        scanf("%d %d %d\n", &N, &W, &H);
        for(int h = 0; h < H; h++){
            for(int w = 0; w < W; w++){
                scanf("%d", &map[h][w]);
            }
        }
        shot(map, N);
        printf("#%d %d\n", test_case, answer);
	}
	return 0; //정상종료시 반드시 0을 리턴해야 합니다.
}