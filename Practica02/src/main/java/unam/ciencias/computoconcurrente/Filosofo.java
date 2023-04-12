package unam.ciencias.computoconcurrente;
public class Filosofo extends Thread {
    private int id; 
    private Semaphore tenedorIzq;
    private Semaphore tenedorDer;
    private boolean haTerminadoDeComer; //indica si ya comió el filósofo

    public Filosofo(int id, Semaphore tenedorIzq, Semaphore tenedorDer) {
        this.id = id;
        this.tenedorIzq = tenedorIzq;
        this.tenedorDer = tenedorDer;
        this.haTerminadoDeComer = false;
    }

    public void run() {
        while (!haTerminadoDeComer) { //para que sólo se ejecute una vez
            try {
                tenedorIzq.acquire(); //se requiere el tenedor de la izquierda 
                tenedorDer.acquire(); // se riquiere el tenedor de la derecha
                System.out.println("El filósofo " + id + " está comiendo."); //cuando llega a este punto es porque los dos ya se pudieron tomar
                Thread.sleep(1000); //esperamos un segundo a que coma
                tenedorDer.release(); //desocupamos los tenedores
                tenedorIzq.release();
                System.out.println("El filósofo " + id + " está pensando."); //decimos que ahora está pensando
                Thread.sleep(1000);
                haTerminadoDeComer = true; //marcamos que ahora ya comió
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean haTerminadoDeComer() { //para que la clase principal pueda saber cuando haya termiando de comer
        return haTerminadoDeComer;
    }
}
