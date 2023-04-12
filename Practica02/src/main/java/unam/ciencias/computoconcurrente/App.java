package unam.ciencias.computoconcurrente;

public class App {
    public static int n;
    public static void main(String[] args) throws InterruptedException {
        n = 5; //CANTIDAD DE FILÓSOFOS
        Semaphore[] tenedores = new Semaphore[n]; //declaramos nuestros this.n tenedores
        Filosofo[] filosofos = new Filosofo[n]; //declaramos nuestros filósofos

        for (int i = 0; i < n; i++) {
            tenedores[i] = new Semaphore(1); //inicializamos los semáforos con permisos = 1 porque solo hay un tenedor en cada casilla
        }

        for (int i = 0; i < n; i++) {
            filosofos[i] = new Filosofo(i, tenedores[i], tenedores[(i + 1) % n]); //creamos a los filósofos
            filosofos[i].start(); //empezamos a ejecutar su dinámica
        }

        boolean todosHanTerminadoDeComer = false; //inicialmente no han terminado de comer
        while (!todosHanTerminadoDeComer) { //mientras no hayan terminado de comer
            todosHanTerminadoDeComer = true; //suponemos que ya terminaron
            for (int i = 0; i < n; i++) { //revisamos a cada uno
                if (!filosofos[i].haTerminadoDeComer()) { //si alguno no ha terminado
                    todosHanTerminadoDeComer = false; //marcamos como false y nos salimos del for
                    break;
                }
            }
            Thread.sleep(500); //esperamos para que haya cmabios en el estado
        }

        System.out.println("Todos los filósofos han terminado de comer.");
        System.exit(0);
    }
}
