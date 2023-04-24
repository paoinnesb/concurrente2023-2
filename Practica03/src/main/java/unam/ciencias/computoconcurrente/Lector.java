

class Lector extends Lector_Escritor implements Runnable{
    /**
    Este será el id de nuestro lector o escritor.
     */
    private int id = 0;
    
    /**
    El tiempo en milisegundos que en general dormirá el lector 
     */
    private int rNap = 0;

    /**
    La db o el pizarron que compartirán lectores y escritores.
     */
    private Database db = null;

    //La idea que extendamos Lector_Escritor, es para no crear los atributos anteriores y aquí en vez de usar this, utilizar super. A ver si esto funciona.
    public Lector(int id, int rNap, Database db){
        this.id = id;
        this.rNap = rNap;
        this.db = db;
    }

    public void run() {
      int napping;
      while (true) {
         napping = 1 + (int) random(rNap);
         System.out.println("age=" + age() + ", " + getName()
            + " napping for " + napping + " ms");
         nap(napping);
         System.out.println("age=" + age() + ", " + getName()
            + " wants to read");
         db.startRead(id);
         napping = 1 + (int) random(rNap);
         System.out.println("age=" + age() + ", " + getName()
            + " reading for " + napping + " ms");
         nap(napping);
         db.endRead(id);
         System.out.println("age=" + age() + ", " + getName()
            + " finished reading");
      }
   }
}