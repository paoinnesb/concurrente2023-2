//Descomentar la linea siguiente si se mueve o copia el archivo.
//package unam.ciencias.computoconcurrente;


public class Semaphore{

    /**
    El valor que tendra el semáforo.
     */
    protected int valor=0;

    /**
    Si se crea un semaforo sin un valor especifico será considreado uno binario.
     */
    protected Semaphore(){
        int this.valor =O 0;
    }

    public Semaphore(int initial) {
      if (initial < 0) throw new IllegalArgumentException("initial<0");
      this.valor = initial;
      if (initial > 1) throw new IllegalArgumentException("initial>1");
   }

    /**
    Función para disminuir el valor del semaforo. 
    */
    public synchronized void P(){
        this.valor--;
      if (this.valor < 0) {
         while (true) {  
            try {
               wait();
               break;       
            } catch (InterruptedException e) {
               System.err.println("Semaphore.P(): InterruptedException, wait again");
               if (this.valor >= 0) break; 
               else continue;         
            }
         }
      }
    }

    /**
    Función para aumentar el valor del semaforo.
    */
    public synchronized void V() { 
      this.valor++;                    
      if (this.valor <= 0) notify(); 
      if (this.valor > 1) this.valor = 1; //Como queremos que sea binario, ponemos un tope al maximo valor posible.
   }   
}

