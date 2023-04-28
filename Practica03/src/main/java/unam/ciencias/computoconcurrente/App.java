package unam.ciencias.computoconcurrente;

public class App {
   private static int NUM_LECTORES = 3;
   private static int NUM_ESCRITORES = 2;
   private static Thread[] lectores = new Thread[NUM_LECTORES];
   private static Thread[] escritores = new Thread[NUM_ESCRITORES];

   public static void main(String[] args) {
         Database database = new Database();
         if(args.length > 2){
            NUM_LECTORES = Integer.parseInt(args[0]);
            NUM_ESCRITORES = Integer.parseInt(args[1]);
         }
         // Crear lectores
         for (int i = 0; i < NUM_LECTORES; i++) {
            Lector lector = new Lector(i, database);
            Thread hiloLector = new Thread(lector);
            lectores[i] = hiloLector;
         }

         // Crear escritores
         for (int i = 0; i < NUM_ESCRITORES; i++) {
            Escritor escritor = new Escritor(i, database);
            Thread hiloEscritor = new Thread(escritor);
            escritores[i] = hiloEscritor;
         }

         for (int i = 0; i < NUM_ESCRITORES; i++) {
            escritores[i].start();
         }

         for (int i = 0; i < NUM_LECTORES; i++) {
            lectores[i].start();
         }
   }
}