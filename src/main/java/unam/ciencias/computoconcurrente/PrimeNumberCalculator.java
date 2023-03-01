package unam.ciencias.computoconcurrente;
import java.lang.Math;

public class PrimeNumberCalculator implements Runnable{

    private int threads;
    private static int numPrimo;
    public static boolean result;
    public static int longitudSubInter; //Dividimos el intervalo [2,N-1] en this.threads cantidad de sub interbalos, uno por cada hilo
    public int inicio;
    public int tope;

    public PrimeNumberCalculator() {
        this.threads = 1;
        this.result=true;
    }

    public PrimeNumberCalculator(int threads) {
        this.threads = threads > 1 ? threads : 1;
        this.result=true;
    }

    public PrimeNumberCalculator(int inicio,int tope,int numPrimo){
        this.inicio = inicio;
        this.tope = tope;
        this.numPrimo = numPrimo;
    }
    

    public boolean isPrime(int n) throws InterruptedException{
        this.result = true;
        if(n<0){
            return true;
        }
        this.numPrimo = n;
        this.longitudSubInter = (int)(Math.sqrt(this.numPrimo)+1)/this.threads;

        /**Creamos nuestros hilos. */
        Thread[] hilos = new Thread[this.threads];
        int contador = 0;

        /**Casos Base. */
        if(n==0 || n ==1){
            return false;
        }

        int top = (int)Math.floor(Math.sqrt(n));

        /**Ahora, dividimos el intervalo [2,N-1]. Y cada uno es asignado a un hilo. */
        for(int i = 2;n>1 && i<=top;i=i+this.longitudSubInter){
            /**Creamos los hilos y los agregamos a nuestro arreglo de hilos. */
            PrimeNumberCalculator aux = new PrimeNumberCalculator(i,i+this.longitudSubInter,this.numPrimo);
            hilos[contador]=new Thread(aux);
            contador++;
        }

        for(Thread t:hilos){
            t.start();
        }

        for (Thread th : hilos) {
            try {
                th.join();
            } catch (InterruptedException e) {
                System.out.println("Ocurrió una excepción al esperar a que los threads terminen su ejecución.");
                e.printStackTrace();
            }
        }
        
        return this.result;
    }
    

    @Override
    public void run(){
        for(int i = this.inicio;i<=this.tope;i++){
            if(this.numPrimo % i == 0){
                //this.result=false;
                this.result=false;
            }
        }
    }
}
