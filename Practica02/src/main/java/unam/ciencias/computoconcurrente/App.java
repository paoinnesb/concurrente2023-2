package unam.ciencias.computoconcurrente;

public class App {

    //Este es el codigo del DININGSERVER.
   private static final int
      THINKING = 0, HUNGRY = 1, EATING = 2;
   private int numPhils = 0;
   private int[] state = null;
   private Semaphore[] fork = null;

   public DiningServer(int numPhils) {
      System.out.println("El comedor tendrá:"+numPhils);
      this.numPhils = numPhils;
      state = new int[numPhils];
      for (int i = 0; i < numPhils; i++) state[i] = THINKING;
      fork = new Semaphore[numPhils];
      for (int i = 0; i < numPhils; i++) fork[i] = new Semaphore(1);
   }

   private final int left(int i) { return (numPhils + i - 1) % numPhils; }

   private final int right(int i) { return (i + 1) % numPhils; }

   public void takeForks(int i) {
      state[i] = HUNGRY;
      if (i > 0) { P(fork[i]); P(fork[right(i)]); }
      else       { P(fork[right(i)]); P(fork[i]); }
      state[i] = EATING;
   }

   public void putForks(int i) {
      V(fork[i]); V(fork[right(i)]);
      state[i] = THINKING;
   }

   //AQUI TERMINA EL CODIGO DEL DINING SERVER. ESTO SE EJECUTA EN MAIN.

    public static void main(String[] a) throws InterruptedException {
        
    }
}
