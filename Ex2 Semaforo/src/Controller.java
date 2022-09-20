import java.util.concurrent.Semaphore;

public class Controller extends Thread {
	
	private Semaphore semaforo;
	private int id;
	private String nomePrato;
	
	public Controller(Semaphore semaforo){
		this.semaforo = semaforo;
		
		if (this.getId() % 2 == 1){
			this.id = 1;
			this.nomePrato = "Sopa de Cebola";
		} else {
			this.id = 2;
			this.nomePrato = "Lasanha a Bolonhesaa";
		}
	}
	
	@Override
	public void run() {
		
		prepararPrato(id);
		
		try {
			semaforo.acquire();
			entregarPrato();
		} catch (InterruptedException e){
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void entregarPrato() {
		try {
			sleep(500);
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Prato #" + this.getId() + ": " + this.nomePrato + " ==> ENTREGUE!");
			System.out.println("-----------------------------------------------------------------");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void prepararPrato(int id) {
		double tempo;
		int percentual = 0;
		
		if (id == 1){
			tempo = (Math.random() * ((0.8 - 0.5))) + 0.5;
		} else {
			tempo = (Math.random() * ((1.2 - 0.6))) + 0.6;
		}
		
		System.out.println("Cozinheiro INICIANDO prato #" + this.getId() + ": " + this.nomePrato);
		
		for (double i = 0.1; i <= tempo; i+=0.1){
			try {
				sleep(100);
				percentual = (int) ((i / tempo) * 100);
				System.out.println("Percentual de cozimento do prato #" + this.getId() + ": " + this.nomePrato + " ==> " + percentual + "%");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Prato #" + this.getId() + ": " + this.nomePrato + " ==> PRONTO!");
		
	}
}