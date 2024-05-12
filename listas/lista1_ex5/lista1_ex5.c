#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdint.h>
#include <time.h>

int main(int argc, char **argv){
    if(argc != 3){
        printf("Uso: %s <tamanho do array> <elemento>\n", argv[0]);
        return 1;
    }

    int elem = atoi(argv[2]);
    int size = atoi(argv[1]);
    if (size <= 0 || size > 128) {
        printf("Tamanho do array inválido. Deve ser entre 1 e 128.\n");
        return 1;
    }

    srand(time(NULL));
    int array[size];
    for(int i = 0; i < size; i++){
        array[i] = rand();
    }

    pid_t pid1, pid2;
    pid1 = fork();
    if(pid1 == 0){
        int8_t result = -1;
        usleep(rand() % 1000);
        for(int j = 0; j < size; j++){
            if(array[j] == elem){
                result = j;
                exit(result);
            }
        }
        exit(result);
    }
    else if(pid1 > 0){
        pid2 = fork();
        if(pid2 == 0){
            int8_t result = -1;
            for(int j = size-1; j >= 0; j--){
                if(array[j] == elem){
                    result = j;
                    exit(result);
                }
            }
            exit(result);
        }
        else if(pid2 > 0){
            int status;
            pid_t terminated_pid = wait(&status);
            int8_t index = WEXITSTATUS(status);
            if(index != -1){
                printf("Elemento encontrado no índice: %d\n", index);
            } 
            else {
                printf("Elemento não encontrado\n");
            }
        }
        else{
            printf("Erro ao criar o segundo processo filho");
            return 1;
        }
    }
    else{
        printf("Erro ao criar o primeiro processo filho");
        return 1;
    }

    return 0;
}