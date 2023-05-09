package unam.ciencias.computoconcurrente;

import java.util.Random;

/**
 *  El producto de matrices debe ser paralelo.
 */
public class Matrices{

    private static class Worker implements Runnable {
		private int matrixA[][];
        private int matrixB[][];
        private int mat[][];
		private int i, j;

		public Worker(int[][] matrixA, int[][] matrixB, int[][] mat, int i, int j) {
			this.matrixA = matrixA;
			this.matrixB = matrixB;
			this.mat = mat;
			this.i = i;
			this.j = j;
		}

		@Override
		public void run() {
			int sum = 0;
			for(int k = 0; k < matrixB.length; k++) {
				sum += matrixA[i][k] * matrixB[k][j];
			}
			mat[i][j] = sum; 
		}
	}
    
    public static int[][] multiply(int[][] matrixA, int[][] matrixB) {
        int[][] matrix = new int[matrixA.length][matrixB[0].length];
        if(matrixA[0].length != matrixB.length) {
            for(int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix[0].length; j++)
                    matrix[i][j] = -1;
            return matrix;
        }
        Thread[] threads = new Thread[matrixA.length*matrixB[0].length];
        int count = 0;
		for(int i = 0; i < matrixA.length; i++) {
			for(int j = 0; j < matrixB[0].length; j++) {
                threads[count] = new Thread(new Worker(matrixA, matrixB, matrix, i, j));
                threads[count].start();
                count ++;
			}
		}
        for(int i = 0; i < count; i++) {
            try {threads[i].join();}
            catch(InterruptedException e) {}
		}
		return matrix;
    }


    public static int[][] generarMatriz(int rows, int columns) {
        Random random = new Random();
        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }

        return matrix;
    }

    public static void print(int[][] matrix){
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(ints[j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
}

