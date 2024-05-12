package aula4_ex2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListDirectoryContents {
    public static void main(String[] args) {
        try{
            // comando a ser executado pelo processo externo
            String[] command = {"ls"};

            // cria o processo externo
            ProcessBuilder pb;
            Process p;
            pb = new ProcessBuilder(command).inheritIO();

            pb.directory(new java.io.File("."));

            p = pb.start();

            // obtém a saída do processo
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            // exibe a saída do processo
            String line = reader.readLine();
            System.out.println("Conteúdo do diretório corrente:");
            while (line != null) {
                System.out.println(line);
            }

            // espera até que o processo externo termine
            int exitCode = p.waitFor();
            System.out.println("Processo terminado com código de saída: " + exitCode);
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}