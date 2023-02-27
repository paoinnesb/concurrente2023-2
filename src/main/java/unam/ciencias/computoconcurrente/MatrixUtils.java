package unam.ciencias.computoconcurrente;
import java.util.Arrays;;

public class MatrixUtils implements Runnable{
    private int threads;
    private double[] posiblesPromedios; // Arreglo para que cada hilo guarde su minimo encontrado
    private int[][] matrixGlobal;
    private int indice;

    public MatrixUtils() {
        this.threads = 1;
        this.posiblesPromedios = new double[1];
    }

    public MatrixUtils(int threads) {
        this.threads = threads;
        this.posiblesPromedios = new double[threads];
    }

    public MatrixUtils(int indice, double[] promedios, int[][] matrixGlobal){
        this.posiblesPromedios = promedios;
        this.indice = indice;
        this.matrixGlobal = matrixGlobal;
    }

    @Override
    public void run() {
        double prom = this.Average(matrixGlobal);
        this.posiblesPromedios[this.indice] = prom;
    }


    public double findAverage(int[][] matrix) throws InterruptedException{
        int divisiones = (matrix.length - (matrix.length % this.threads)) / this.threads;
        
        Thread[] threads = new Thread[this.threads];
        
        for (int i = 0; i < this.threads; i++) {
            int[][] div;
            if((matrix.length % this.threads) != 0 && i == this.threads - 1){
                div = Arrays.copyOfRange(matrix, (i*divisiones), matrix.length);
            } else{
                div = Arrays.copyOfRange(matrix, (i * divisiones), (i * divisiones) + divisiones);
            }
            MatrixUtils p = new MatrixUtils(i, this.posiblesPromedios, div);
            threads[i] = new Thread(p);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Ocurrió una excepción al esperar a que los threads terminen su ejecución.");
                e.printStackTrace();
            }
        }
        double p = 0;
        for (int i = 0; i < this.threads; i++) {
            p += this.posiblesPromedios[i];
        }
        return p / this.posiblesPromedios.length;
    }

    /**
     * Metodo que recorre una matriz de dos dimensiones 
     * @param matrix - matriz de dos dimensiones 
     * @return promedio - promedio de la matriz
     */
    public double Average(int[][] matrix){
        double prom = 0;
        int i = 0;
        int j = 0;
        for(i = 0; i < matrix.length; i++){
            for(j = 0; j < matrix[i].length; j++){
                prom += matrix[i][j];
            }
        }
        int elementos = i * j;
        return prom / elementos;
    }
}
