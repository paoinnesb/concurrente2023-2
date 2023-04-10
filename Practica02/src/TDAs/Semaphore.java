

public class Semaphore{

    /**
    El valor que tendra el semáforo.
     */
    protected int valor=0;

    /**
    Si se crea un semaforo sin un valor especifico será considreado uno binario.
     */
    protected Semaphore(){
        int valor = 0;
    }

    protected Semaphore(int initial) {
      if (initial < 0) throw new IllegalArgumentException("initial<0");
      value = initial;
   }



    /**
    Función para disminuir el valor del semaforo. 
    */
    public synchronized void P(){
        this.valor--;
    }

    /**
    Función para aumentar el valor del semaforo.
    */
    public synchronized void V() throws InterruptedException{
        while(this.valor==0){
            wait();
        }
        this.valor--;
    }
}

