

public class Semaphore{

    /**
    El valor que tendra el semáforo.
     */
    int valor;

    /**
    Si se crea un semaforo sin un valor especifico será considreado uno binario.
     */
    public Semaphore(){
        int valor = 1;
    }

    public Semaphore(int v){
        if(v<0){
            System.out.println("NO pude ser negativo el valor del semaforo.");
        }else{
            int valor = v;
        }
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

