package unam.ciencias.computoconcurrente;
import Utilities.*;
import Synchronization.*;

/**
 *  Cada filósofo se ejecuta en un hilo.
 */
public abstract class Filosofo implements Runnable {
   private int id = 0;
   private int napThink = 0; // both are in
   private int napEat = 0;   // milliseconds
   private DiningServer ds = null;

   public Filosofo(String name, int id, int napThink,
         int napEat, DiningServer ds) {
      super(name + " " + id);
      this.id = id;
      this.napThink = napThink;
      this.napEat = napEat;
      this.ds = ds;
      System.out.println(this.id + " is alive, napThink="
         + napThink + ", napEat=" + napEat);
      new Thread(this).start();
   }

   private void think() {
      int napping;
      napping = 1 + (int) random(napThink);
      System.out.println(this.id + ", is thinking for " + napping + " ms");
      nap(napping);
   }

   private void eat() {
      int napping;
      napping = 1 + (int) random(napEat);
      System.out.println(this.id + ", is eating for " + napping + " ms");
      nap(napping);
   }

   public void run() {
      while (true) {
         think();
         System.out.println(this.id + ", wants to eat");
         ds.takeForks(id);
         eat();
         ds.putForks(id);
      }
   }

   public void nap(int napping){
        sleep(napping);
   }
}