package listas.lista2_ex5;

import java.util.ArrayList;

public class Sort {
    public static void main(String[] args){
        if(args.length <= 1){
            System.out.println("Uso: java Sort <conjunto de numeros inteiros>");
            System.exit(1);
        }

        ArrayList<Integer> array = new ArrayList<>();
        for(int i = 0; i < args.length; i++){
            array.add(Integer.parseInt(args[i]));
        }
        System.out.println("Array original: " + array);

        QuickSortThreads thrdSort = new QuickSortThreads(array, 0, array.size() - 1);
        Thread thrd = new Thread(thrdSort);
        thrd.start();

        try {
            thrd.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Array ordenado: " + array);
    }
}

class QuickSortThreads implements Runnable{
    ArrayList<Integer> array;
    int left, right;
    public QuickSortThreads(ArrayList<Integer> array, int esquerda, int direita){
        this.array = array;
        this.left = esquerda;
        this.right = direita;
    }
    public void run(){
        quickSort(array, left, right);
    }

    void quickSort(ArrayList<Integer> valor, int esquerda, int direita){
        int i, j, x, y;
        i = esquerda;
        j = direita;
        x = valor.get((esquerda + direita) / 2);

        while(i <= j){
            while(valor.get(i) < x && i < direita){
                i++;
            }
            while(valor.get(j) > x && j > esquerda){
                j--;
            }
            if(i <= j){
                y = valor.get(i);
                valor.set(i, valor.get(j));
                valor.set(j, y);
                i++;
                j--;
            }
        }

        if(j > esquerda){
            QuickSortThreads thrdSort = new QuickSortThreads(valor, esquerda, j);
            Thread thrd = new Thread(thrdSort);
            thrd.start();

            try {
                thrd.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(i < direita){
            QuickSortThreads thrdSort = new QuickSortThreads(valor, i, direita);
            Thread thrd = new Thread(thrdSort);
            thrd.start();

            try {
                thrd.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}