package unam.ciencias.computoconcurrente;

import java.util.Random;

public class Escritor implements Runnable {
   private int id;
   private Database database;

   public Escritor(int id, Database database) {
       this.id = id;
       this.database = database;
   }

   @Override
   public void run() {
       try {
            int i = 0;
           while (i < 5) {
               database.escribir(id);
               i++;
               Thread.sleep(500);
           }
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }
}
