// esse código só funciona no UNIX
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void fibonacci(int limit) {
    int first = 0, second = 1, next;

    printf("Sequência de Fibonacci até %d:\n", limit);
    next = first + second;

    while (next < limit) {
        printf("%d ", next);
        next = first + second;
        first = second;
        second = next;
    }

    printf("\n");
}

int main(int argc, char *argv[]) {
    // conferir se o número de argumentos está correto
    if (argc != 2) {
        printf("Uso: %s <limite>\n", argv[0]);
        return 1;
    }

    // conferir se o limite inserido é maior que zero
    int limit = atoi(argv[1]);
    if (limit <= 0) {
        printf("Por favor, forneça um limite positivo.\n");
        return 1;
    }

    //criar processo
    pid_t pid = fork();

    if (pid < 0) {
        printf("Falha ao criar processo filho.\n");
        return 1;
    } 
    else if (pid == 0) {
        // processo filho
        fibonacci(limit);
    } 
    else {
        // processo pai
        wait(NULL);
        printf("Processo filho concluído.\n");
    }

    return 0;
}