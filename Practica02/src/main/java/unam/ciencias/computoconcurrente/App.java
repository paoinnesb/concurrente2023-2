package unam.ciencias.computoconcurrente;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaforo = new Semaphore(4);
        Semaphore[] tenedores = new Semaphore[5];
        Filosofo[] filosofos = new Filosofo[5];

        for (int i = 0; i < 5; i++) {
            tenedores[i] = new Semaphore(1);
        }

        for (int i = 0; i < 5; i++) {
            filosofos[i] = new Filosofo(i, tenedores[i], tenedores[(i + 1) % 5], semaforo);
            filosofos[i].start();
        }

        boolean todosHanTerminadoDeComer = false;
        while (!todosHanTerminadoDeComer) {
            todosHanTerminadoDeComer = true;
            for (int i = 0; i < 5; i++) {
                if (!filosofos[i].haTerminadoDeComer()) {
                    todosHanTerminadoDeComer = false;
                    break;
                }
            }
            Thread.sleep(500);
        }

        System.out.println("Todos los filósofos han terminado de comer. Terminando la ejecución.");
        System.exit(0);
    }
}
