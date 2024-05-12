package listas.lista1_ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleShell {
    public static void main(String[] args){
        if (args.length == 0) {
            System.out.println("Uso: java SimpleShell <comando>");
            System.exit(1);
        }

        try{         
            Process p;
            p = new ProcessBuilder(args).start(); 

            // obtém a saída do processo
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            // exibe a saída do processo
            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // espera até que o processo externo termine
            int exitCode = p.waitFor();
            System.out.println("Processo terminado com código de saída: " + exitCode);
        }
        catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}