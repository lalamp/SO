#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>

void quickSort(int valor[], int esquerda, int direita){ 
    int i, j, x, y; 
    i = esquerda; j = direita; 
    x = valor[(esquerda + direita) / 2]; 
    
    while(i <= j){ 
        while(valor[i] < x && i < direita){ 
            i++; 
        } 
        while(valor[j] > x && j > esquerda){ 
            j--; 
        } 
        if(i <= j){ 
            y = valor[i]; 
            valor[i] = valor[j]; 
            valor[j] = y; i++; j--; 
        } 
    } 

    if(j > esquerda){ 
        quickSort(valor, esquerda, j); 
    } 
    if(i < direita){ 
        quickSort(valor, i, direita); 
    } 
} 

void insertionSort(int valor[], int n) {
    int i, j, aux;
    for(i = 1; i < n; i++) {
        aux = valor[i];
        j = i - 1;
        while (j >= 0 && valor[j] > aux) {
            valor[j + 1] = valor[j];
            j = j - 1;
        }
        valor[j + 1] = aux;
    }
}

int main(int argc, char **argv){
    if(argc != 11){
        printf("Voce deve passar 10 números inteiros\n");
        return 1;
    }

    int valor[10];
    for(int i = 0; i <= 9; i++){
        valor[i] = atoi(argv[i+1]);
    }

    clock_t c1, c2;
    float tmp;

    pid_t pid = fork();
    if (pid < 0) {
        printf("Falha ao criar processo filho.\n");
        return 1;
    } 
    else if (pid == 0) {
        // processo filho
        c1 = clock(); 
        quickSort(valor, 0, 9);
        c2 = clock();
        tmp = (c2 - c1)*1000/CLOCKS_PER_SEC;
        
        printf("Valores ordenados pelo processo filho (Quick Sort):\n");
        for (int i = 0; i < 10; i++) {
            printf("%d ", valor[i]);
        }
        printf("\n");

        printf("Tempo gasto pelo processo filho: %fms\n", tmp);
    } 
    else {
        // processo pai
        wait(NULL);
        printf("Processo filho concluído.\n");

        c1 = clock(); 
        insertionSort(valor, 10);
        c2 = clock();
        tmp = (c2 - c1)*1000/CLOCKS_PER_SEC;

        printf("Valores ordenados pelo processo pai (Insertion Sort):\n");
        for (int i = 0; i < 10; i++) {
            printf("%d ", valor[i]);
        }
        printf("\n");

        printf("Tempo gasto pelo processo pai: %fms\n", tmp);
    }
}