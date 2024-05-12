package aula5_ex1;

public class Numbers{
	public static void main(String[] args){
		Thread thrd;
		ThreadGroup group;

		if(args.length > 0){
			group = new ThreadGroup("Numbers");

			for(int i = 0; i < args.length; i++){
				thrd = new Thread(group, new PrimoCheck(Integer.parseInt(args[i]))); 
				thrd.start();
			}

			while(group.activeCount() > 0){
				try {
					Thread.sleep(5);
				} catch(InterruptedException ie) {
					System.err.println(ie);
				}
			}

			System.out.println("Comportamento main encerrado");
		}
		else{
			System.err.println("Nro de parametros deve ser pelo menos 1"); 
			System.exit(0);
		}
	}
}

class PrimoCheck implements Runnable{
		private int number;
		public PrimoCheck(int n){
			number = n;
		} 

		private boolean isPrimo(){
			if(number == 2){
				return true;
			}
			else{
				if(number % 2 == 0)
					return false;
				else{
					for(int i = 3; i*i <= number; i++){
						if(number % i == 0)
							return false;
					}
				}
				return true;
			}
		}

		public void run(){
			if(isPrimo())
				System.out.println(number + " e primo");
			else
				System.out.println(number + " nao e primo");
		}
}