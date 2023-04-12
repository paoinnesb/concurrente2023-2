package unam.ciencias.computoconcurrente;
public class Filosofo extends Thread {
    private int id;
    private Semaphore tenedorIzq;
    private Semaphore tenedorDer;
    private Semaphore semaforo;
    private boolean haTerminadoDeComer;

    public Filosofo(int id, Semaphore tenedorIzq, Semaphore tenedorDer, Semaphore semaforo) {
        this.id = id;
        this.tenedorIzq = tenedorIzq;
        this.tenedorDer = tenedorDer;
        this.semaforo = semaforo;
        this.haTerminadoDeComer = false;
    }

    public void run() {
        while (!haTerminadoDeComer) {
            try {
                semaforo.acquire();
                tenedorIzq.acquire();
                tenedorDer.acquire();
                System.out.println("El filosofo " + id + " esta comiendo.");
                Thread.sleep(1000);
                tenedorDer.release();
                tenedorIzq.release();
                semaforo.release();
                System.out.println("El filosofo " + id + " esta pensando.");
                Thread.sleep(1000);
                haTerminadoDeComer = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean haTerminadoDeComer() {
        return haTerminadoDeComer;
    }
}
