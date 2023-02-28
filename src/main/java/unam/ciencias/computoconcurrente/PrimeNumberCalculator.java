package unam.ciencias.computoconcurrente;
import java.lang.Math;

public class PrimeNumberCalculator implements Runnable{

    private int threads;
    private static int numPrimo;
    public static boolean result;
    public static int longitudSubInter; //Dividimos el intervalo [2,N-1] en this.threads cantidad de sub interbalos, uno por cada hilo
    public static int inicio;
    public static int tope;

    public PrimeNumberCalculator() {
        this.threads = 1;
    }

    public PrimeNumberCalculator(int threads) {
        this.threads = threads > 1 ? threads : 1;
    }

    public PrimeNumberCalculator(int inicio,int tope,int numPrimo){
        this.inicio = inicio;
        this.tope = tope;
        this.numPrimo = numPrimo;
    }
    

    public boolean isPrime(int n) throws InterruptedException{

        this.longitudSubInter = this.threads;
        this.numPrimo = n;
        int contador = 0;

        /**Casos Base. */
        if(n==0 || n ==1){
            this.result=false;
        }

        /**Creamos nuestros hilos. */
        Thread[] hilos = new Thread[this.threads];

        /**Dividimos nuestro conjunto de  2-sqrt(n) en conjuntos de tamaño this.longitudSubInter*/
        for(int i = 2; i<=(int)Math.sqrt(n);i=i+this.longitudSubInter){
            /**Aquí la idea es indicarle a nuestros hilos donde empezar a verificar y donde terminan. */
            PrimeNumberCalculator hilo = new PrimeNumberCalculator(i,i+this.longitudSubInter,this.numPrimo);
            hilos[contador]=new Thread(hilo);
            contador++;
        }

        for (Thread thread : hilos) {
            thread.start();
        }


        /**for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Ocurrió una excepción al esperar a que los threads terminen su ejecución.");
                e.printStackTrace();
            }
        }*/
        return this.result;

    }
    

    @Override
    public void run(){
        /** Cada hilo, lo que debería hacer es verificar que en su inicio y tope no haya algun entero que divida al numero que queremos verificar que es this.numPrimo. */
        for(int i = this.inicio;i<=tope;i++){
            if(i%this.numPrimo==0){
                this.result=false;
            }
        }
        this.result = true;
    }
}
