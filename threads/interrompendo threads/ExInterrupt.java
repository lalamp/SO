class MinhaThread extends Thread {
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread em execução: " + i);
                Thread.sleep(1000); // Espera por 1 segundo

                // Verifica se a thread foi interrompida
                if (Thread.interrupted()) {
                    System.out.println("Thread interrompida.");
                    return; // Sai do método run() se a thread foi interrompida
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrompida.");
            return; // Sai do método run() se a thread foi interrompida enquanto estava dormindo
        }
    }
}

public class ExInterrupt {
    public static void main(String[] args) {
        System.out.println("Iniciando a thread principal...");

        // Criando uma instância da Thread
        MinhaThread minhaThread = new MinhaThread();
        // Iniciando a execução da thread
        minhaThread.start();

        try {
            // Espera um pouco
            Thread.sleep(3000);
            // Interrompe a thread
            minhaThread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("Thread principal interrompida.");
        }

        System.out.println("Thread principal concluída.");
    }
}


