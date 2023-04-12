package unam.ciencias.computoconcurrente;

public class Semaphore {
    private int permits; //variable que nos indica si el semáforo está o no bloqueado, siempre debe ser mayor a 0

    public Semaphore(int permits) { //permits es para saber cuántos "valores" puede tener el semáforo, como cuántos recursos se pueden usar, para los filósofos es 1 por ejemplo
        //porque tenemos un semáforo por tenedor y sólo un tenedor
        this.permits = permits;
    }

    //aquí estamos usando los monitores de java, con wait
    public synchronized void acquire() throws InterruptedException { //función que solicita usar el recurso
        while (permits == 0) { //esto significa que no se puede usar porque está ocupado
            wait(); // bloqueamos la ejecucion que esperamos hasta que otro hilo llame notify()
        }
        permits--; //cuando se desbloquea, restamos 1 a los permisos para indicar que se usó otro
    }

    public synchronized void release() { //funcion que se manda a llamar cuando el tenedor ya se desocupó
        permits++; // indica que ya tenemos un reurso más disponible
        notify(); // se envía el aviso para los hilos que están esperando en wait()
    }
}
