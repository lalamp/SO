#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv){
    int fib1;
    int fib2;
    int temp;
    int lim;

    lim = atoi(argv[1]);

    fib1 = 1;
    fib2 = 1;

    printf("Fibonacci: 1");

    while(fib1 <= lim){
        printf(" %d", fib1);
        temp = fib1;
        fib1 = fib1 + fib2;
        fib2 = temp;
    }
    printf("\n");
    exit(1);
}