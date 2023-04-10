package unam.ciencias.computoconcurrente;
import Utilities.*;
import Synchronization.*;

/**
 *  Cada filósofo se ejecuta en un hilo.
 */
public abstract class Filosopher implements Runnable {
   private int id = 0;
   private int napThink = 0; // both are in
   private int napEat = 0;   // milliseconds
   private DiningServer ds = null;

   public Filosopher(String name, int id, int napThink,
         int napEat, DiningServer ds) {
      super(name + " " + id);
      this.id = id;
      this.napThink = napThink;
      this.napEat = napEat;
      this.ds = ds;
      System.out.println(getName() + " is alive, napThink="
         + napThink + ", napEat=" + napEat);
      new Thread(this).start();
   }

   private void think() {
      int napping;
      napping = 1 + (int) random(napThink);
      System.out.println("age()=" + age() + ", " + getName()
         + " is thinking for " + napping + " ms");
      nap(napping);
   }

   private void eat() {
      int napping;
      napping = 1 + (int) random(napEat);
      System.out.println("age()=" + age() + ", " + getName()
         + " is eating for " + napping + " ms");
      nap(napping);
   }

   public void run() {
      while (true) {
         think();
         System.out.println("age()=" + age() + ", " + getName()
            + " wants to eat");
         ds.takeForks(id);
         eat();
         ds.putForks(id);
      }
   }

   public void nap(int napping){
        sleep(napping);
   }
}

class DiningServer {

   private static final int
      THINKING = 0, HUNGRY = 1, EATING = 2;
   private int numPhils = 0;
   private int[] state = null;
   private BinarySemaphore[] fork = null;

   public DiningServer(int numPhils) {
      super("DiningServer for " + numPhils + " philosophers");
      this.numPhils = numPhils;
      state = new int[numPhils];
      for (int i = 0; i < numPhils; i++) state[i] = THINKING;
      fork = new BinarySemaphore[numPhils];
      for (int i = 0; i < numPhils; i++) fork[i] = new BinarySemaphore(1);
      //System.out.println("Dining room has an `odd' eater");
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
}