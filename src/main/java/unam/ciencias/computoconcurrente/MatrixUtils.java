package unam.ciencias.computoconcurrente;


public class MatrixUtils implements Runnable{
    private int threads;
    private static int[] posiblesPromedios; // Arreglo para que cada hilo guarde su minimo encontrado
    private static int[] matrixGlobal; 
    private static int secciones; 

    public MatrixUtils() {
        this.threads = 1;
    }

    public MatrixUtils(int threads) {
        this.threads = threads;
    }

    @Override
    public void run() {
        
    }

    private void setMatrixGlobal(int[] matrix){
        this.matrixGlobal = matrix;
        return;
    }

    public double findAverage(int[][] matrix) throws InterruptedException{

        Thread[] threads = new Thread[this.threads];
        
        for (int i = 0; i < this.threads; i++) {
            MatrixUtils p = new MatrixUtils();
            threads[i] = new Thread(p);
        }

        

        return 1.0;
       
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
