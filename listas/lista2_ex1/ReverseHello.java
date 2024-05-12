package listas.lista2_ex1;

public class ReverseHello{
    public static void main(String[] args){
        int num = 1;
        MsgThread thrdMsg = new MsgThread(num);
        Thread thrd = new Thread(thrdMsg);
        thrd.start(); 

        try{
            thrd.join();
        }catch(InterruptedException e){
            System.out.println("InterruptedException");
        }
    }
}

class MsgThread implements Runnable{
    int num;
    public MsgThread(int number){
        this.num = number;
    }

    public void run(){
        if(num <= 50){
            MsgThread thrdMsg = new MsgThread(num+1);
            Thread thrd = new Thread(thrdMsg);
            thrd.start(); 

            try{
                thrd.join();
            }catch(InterruptedException e){
                System.out.println("Thread " + (num+1) + " interrompida");
            }

            System.out.println("Ola da Thread " + num);
        }
    }   
}
