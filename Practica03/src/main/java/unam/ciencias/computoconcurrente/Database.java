package unam.ciencias.computoconcurrente;


public class Database {
    private int numLectores = 0;
    private boolean escritorEscribiendo = false;

    public synchronized void leer(int idLector) throws InterruptedException {
        // Esperar antes de intentar acceder a la base de datos
        Thread.sleep((long) (Math.random() * 5000));

        while (escritorEscribiendo) {
            wait(); // Esperar a que el escritor termine de escribir
        }

        // Acceder a la base de datos
        System.out.println("Lector " + idLector + " está leyendo la base de datos.");
        Thread.sleep((long) (Math.random() * 5000));
        System.out.println("Lector " + idLector + " ha terminado de leer la base de datos.");

        // Notificar a cualquier escritor esperando que la base de datos está disponible
        notifyAll();
    }

    public synchronized void escribir(int idEscritor) throws InterruptedException {
        // Esperar antes de intentar acceder a la base de datos
        Thread.sleep((long) (Math.random() * 5000));

        while (numLectores > 0 || escritorEscribiendo) {
            wait(); // Esperar a que no haya lectores o escritores escribiendo
        }

        // Acceder a la base de datos
        escritorEscribiendo = true;
        System.out.println("Escritor " + idEscritor + " está escribiendo en la base de datos.");
        Thread.sleep((long) (Math.random() * 5000));
        System.out.println("Escritor " + idEscritor + " ha terminado de escribir en la base de datos.");
        escritorEscribiendo = false;

        // Notificar a cualquier lector o escritor esperando que la base de datos está disponible
        notifyAll();
    }
}
