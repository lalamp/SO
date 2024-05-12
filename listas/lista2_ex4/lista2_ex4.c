#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

int checkPrimo(long int number){
    if(number == 2){
        return 1;
    }
    else{
        if(number % 2 == 0)
            return 0;
        else{
            for(int i = 3; i*i <= number; i++){
                if(number % i == 0)
                    return 0;
            }
        }
        return 1;
    }
}

void thrdFunction(void *arg){
    long int num = *((long int*)arg);
    int result = checkPrimo(num);
    if(result){
        printf("%ld é primo.\n", num);
    } 
    else{
        printf("%ld não é primo.\n", num);
    }
    pthread_exit(NULL);
}

int main(int argc, char **argv){
    if(argc < 2){
        printf("Uso: %s <num1> <num2> ...\n", argv[0]);
        return 1;
    }

    pthread_t threads[argc-1];
    for(int i = 0; i < argc-1; i++){
        long int num = atol(argv[i + 1]);
        pthread_create(&threads[i], NULL, thrdFunction, &num);
    }
    for(int i = 0; i < argc-1; i++){
        pthread_join(threads[i], NULL);
    }

    return 0;    
}