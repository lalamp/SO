class MinhaThread extends Thread {
    public MinhaThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public void run() {
        System.out.println(getName() + " iniciada.");
        try {
            Thread.sleep(2000); // Simula uma tarefa demorada
        } catch (InterruptedException e) {
            System.out.println(getName() + " interrompida.");
            return;
        }
        System.out.println(getName() + " concluída.");
    }
}

public class ExGrupoDeThreads {
    public static void main(String[] args) {
        // Criando um grupo de threads
        ThreadGroup grupo = new ThreadGroup("MeuGrupo");

        // Criando e iniciando várias threads no grupo
        MinhaThread thread1 = new MinhaThread(grupo, "Thread 1");
        MinhaThread thread2 = new MinhaThread(grupo, "Thread 2");
        MinhaThread thread3 = new MinhaThread(grupo, "Thread 3");
        thread1.start();
        thread2.start();
        thread3.start();

        // Aguardando até que todas as threads do grupo terminem
        while (grupo.activeCount() > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread principal interrompida.");
            }
        }

        System.out.println("Todas as threads do grupo concluídas.");
    }
}


