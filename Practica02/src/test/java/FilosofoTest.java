package unam.ciencias.computoconcurrente;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FilosofoTest {

    @Test
    void filosofoTerminaDeComer() throws InterruptedException {
        Semaphore semaforo = new Semaphore(4);
        Semaphore[] tenedores = new Semaphore[5];
        Filosofo[] filosofos = new Filosofo[5];

        for (int i = 0; i < 5; i++) {
            tenedores[i] = new Semaphore(1);
        }

        for (int i = 0; i < 5; i++) {
            filosofos[i] = new Filosofo(i, tenedores[i], tenedores[(i + 1) % 5]);
            filosofos[i].start();
        }

        Thread.sleep(10000); // Esperamos 10 segundos para que todos los filósofos tengan tiempo de comer.

        boolean todosHanTerminadoDeComer = true;
        for (int i = 0; i < 5; i++) {
            if (!filosofos[i].haTerminadoDeComer()) {
                todosHanTerminadoDeComer = false;
                break;
            }
        }

        assertTrue(todosHanTerminadoDeComer);
    }

    @Test
    void filosofosNoSeBloquean() throws InterruptedException {
        Semaphore semaforo = new Semaphore(4);
        Semaphore[] tenedores = new Semaphore[5];
        Filosofo[] filosofos = new Filosofo[5];

        for (int i = 0; i < 5; i++) {
            tenedores[i] = new Semaphore(1);
        }

        for (int i = 0; i < 5; i++) {
            filosofos[i] = new Filosofo(i, tenedores[i], tenedores[(i + 1) % 5]);
            filosofos[i].start();
        }

        Thread.sleep(1000); // Esperamos un segundo para dar tiempo a que los filósofos se sienten en la mesa.

        // Intentamos adquirir todos los tenedores simultáneamente.
        // Si los filósofos se bloquean, este código se quedará atascado.
        tenedores[0].acquire();
        tenedores[1].acquire();
        tenedores[2].acquire();
        tenedores[3].acquire();
        tenedores[4].acquire();

        tenedores[0].release();
        tenedores[1].release();
        tenedores[2].release();
        tenedores[3].release();
        tenedores[4].release();
    }
}
