package unam.ciencias.computoconcurrente;

import java.util.Random;

public class Lector implements Runnable {
   private int id;
   private Database database;

   public Lector(int id, Database database) {
       this.id = id;
       this.database = database;
   }

   @Override
   public void run() {
       try {
            int i = 0;
           while (true) {
               database.leer(id);
               i++;
               Thread.sleep(500);
           }
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }
}
