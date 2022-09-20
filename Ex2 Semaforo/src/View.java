import java.util.concurrent.Semaphore;
public class View {

	public static void main(String[] args) {
Semaphore semaforo = new Semaphore(1);
		
		for (int i = 0; i < 5; i++){
			Controller tPrato = new Controller(semaforo);
			tPrato.start();
			
		}
	}

}
