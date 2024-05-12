class MinhaThread extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread em execução: " + i);
            try {
                Thread.sleep(1000); // Espera por 1 segundo
            } catch (InterruptedException e) {
                System.out.println("Thread interrompida.");
            }
        }
    }
}

public class ExThread {
    public static void main(String[] args) {
        System.out.println("Iniciando a thread principal...");

        // Criando uma instância da Thread
        MinhaThread minhaThread = new MinhaThread();

        // Iniciando a execução da thread
        minhaThread.start();

        System.out.println("Thread principal concluída.");
    }
}