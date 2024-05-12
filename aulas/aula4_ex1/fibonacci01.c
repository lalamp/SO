#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main(int argc, char **argv){
    if(argc != 2){
        printf("Numero invalido de argumentos!\n");
        exit(-1);
    }

    if(atoi(argv[1]) <= 0){
        printf("O valor de limite deve ser um inteiro positivo!\n");
        exit(-1);
    }

    pid_t pid;
    pid = fork();
    
    if(pid < 0){
        printf("Falha ao criar processo filho.\n");
        exit(-1);
    }
    else if(pid == 0){
        printf("Processo filho iniciado - pid = %d\n", pid);
        execlp("./fibonacci02", "fibonacci02", argv[1], NULL);
        exit(1);
    }
    else{
        printf("Processo pai iniciado - pid = %d\n", pid);
        wait(NULL);
        printf("Processo filho encerrado.\n");
        exit(0);
    }
}