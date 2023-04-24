package unam.ciencias.computoconcurrente;

/**
 *  Cada lector y escritor se ejecuta en un hilo.
 */
public abstract class Lector_Escritor implements Runnable {
    public static int DEFAULT_NUMERO_ESCRITORES = 5;
    public static int DEFAULT_NUMERO_LECTORES = 5;

    /**
    Este será el id de nuestro lector o escritor.
     */
    private int id = 0;
    
    /**
    El tiempo en milisegundos que en general dormirá el lector o escritor.
     */
    private int leNap = 0;

    /**
    La db o el pizarron que compartirán lectores y escritores.
     */
    private Database db = null;
}
