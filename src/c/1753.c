#include <stdio.h>
#include <stdlib.h>
int V, E, start, u, v, w, heap_size;
int ***vector, **heap;
int *d, *vector_size, *vector_max_size;

void print_vector(){
    printf("print vector\n");
    for(int i = 0; i < V; i++){
        printf("%d : ", i);
        for(int j = 0; j < vector_size[i]; j++){
            printf("(%d %d), ", vector[0][i][j], vector[1][i][j]);
        }
        printf("\n");
    }
    printf("\n");
}

void swap(int *a, int *b){
    int temp = *a;
    *a = *b;
    *b = temp;
}

void vector_full(int temp){
    (int*)realloc(vector[temp], sizeof(vector[temp]) * 2);
    vector_max_size[temp] *= 2;
}

void push_back(int start, int dest, int dis){
    int size = vector_size[start];
    if(vector_size[start] >= vector_max_size[start]) vector_full(start);
    vector[0][start][size] = dis;
    vector[1][start][size] = dest;
    vector_size[start]++;
}

void heap_push(int dis, int temp){
    int p;
    heap_size++;
    heap[0][heap_size] = dis;
    heap[1][heap_size] = temp;
    p = heap_size;
    while(p > 1 && heap[0][p] < heap[0][p/2]){
        swap(&heap[0][p], &heap[0][p/2]);
        swap(&heap[1][p], &heap[1][p/2]);
        p /= 2;
    }
}

void heap_pop(){
    int temp_dis, temp, p = 1;

    if(heap_size == 0) return;
    heap[0][1] = heap[0][heap_size];
    heap[1][1] = heap[1][heap_size];
    heap_size--;

    while((p*2 <= heap_size && heap[0][p*2] < heap[0][p]) || (p*2+1 <= heap_size && heap[0][p*2+1] < heap[0][p])){
        if(p*2+1 <= heap_size && heap[0][p*2+1] < heap[0][p] && heap[0][p*2+1] < heap[0][p*2]){
            swap(&heap[0][p], &heap[0][p*2+1]);
            swap(&heap[1][p], &heap[1][p*2+1]);
            p = p*2+1;
        }else{
            swap(&heap[0][p], &heap[0][p*2]);
            swap(&heap[1][p], &heap[1][p*2]);
            p = p*2;
        }
    }
}

int dijkstra(int s){
    int dis, temp, cost, next_d, next_t;
    heap[0][1] = 0;
    heap[1][1] = s;
    heap_size = 1;
    d[s] = 0;
    while(heap_size){
        dis = heap[0][1];
        temp = heap[1][1];
        heap_pop();

        if(d[temp] < dis) continue;

        for(int i = 0; i < vector_size[temp]; i++){
            next_d = vector[0][temp][i];
            next_t = vector[1][temp][i];
            cost = dis +next_d;
            if(cost < d[next_t]){
                d[next_t] = cost;
                heap_push(cost, next_t);
            }
        }
    }
}

int main(int argc, char const *argv[])
{
    scanf("%d %d", &V, &E);
    d = (int*) calloc(V, sizeof(int));
    heap = (int**)malloc(2 * sizeof(int*));
    heap[0] = (int*)malloc((E + 1) * sizeof(int));
    heap[1] = (int*)malloc((E + 1) * sizeof(int));

    scanf("%d", &start);
    start--;
    vector = (int***)malloc(2 * sizeof(int**));
    vector[0] = (int**)malloc(V * sizeof(int*));
    vector[1] = (int**)malloc(V * sizeof(int*));
    vector_size = (int*)calloc(V, sizeof(int));
    vector_max_size = (int*)malloc(V * sizeof(int));
    for(int i = 0; i < V; i++){
        d[i] = 123456789;
        vector[0][i] = (int*)malloc(10 * sizeof(int));
        vector[1][i] = (int*)malloc(10 * sizeof(int));
        vector_max_size[i] = 10;
    }
    for(int e = 0; e < E; e++){
        scanf("%d %d %d", &u, &v, &w);
        u--;
        v--;
        push_back(u, v, w);
    }
    print_vector();
    dijkstra(start);
    for(int i = 0; i < V; i++){
        if(d[i] == 123456789) printf("INF\n");
        else printf("%d\n", d[i]);
    }
    return 0;
}
