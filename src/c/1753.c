#include <stdio.h>
#include <stdlib.h>
int V, E, start, u, v, w;
int **map;
int *d;
int *visit;

int minDistance(){
    int min = 123456789;
    int s;
    for(int i = 0; i < V; i++){
        if(!visit[i] && d[i] < min){
            min = d[i];
            s = i;
        }
    }
    return s;
}

int dijkstra(int s){
    int temp;
    for(int i = 0; i < V; i++) d[i] = map[s][i];
    visit[s] = 1;
    for(int i = 0; i < V - 2; i++){
        temp = minDistance();
        printf("min : %d\n", temp);
        visit[temp] = 1;
        for(int j = 0; j < V; j++){
            if(!visit[j] && d[temp] + map[temp][j] < d[j]) d[j] = d[temp] + map[temp][j];
        }
    }
}

int main(int argc, char const *argv[])
{
    scanf("%d %d", &V, &E);
    map = (int**)malloc(sizeof(int*) * V);
    d = (int*) calloc(V, sizeof(int));
    visit = (int*) calloc(V, sizeof(int));

    scanf("%d", &start);
    start--;
    for(int i = 0; i < V; i++){
        map[i] = (int*)calloc(V, sizeof(int));
        for(int j = 0; j < V; j++){
            if(i != j) map[i][j] = 123456789;
        }
    }
    for(int i = 0; i < V; i++) if(i != start) d[i] = -1;
    for(int e = 0; e < E; e++){
        scanf("%d %d %d", &u, &v, &w);
        u--;
        v--;
        if(map[u][v] != 0 && w < map[u][v]){
            map[u][v] = w;
        }
    }
    dijkstra(start);
    for(int i = 0; i < V; i++){
        if(d[i] == 123456789) printf("INF\n");
        else printf("%d\n", d[i]);
    }
    return 0;
}
