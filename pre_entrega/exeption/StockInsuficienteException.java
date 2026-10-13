package pre_entrega.exeption;


public class StockInsuficienteException extends RuntimeException{
    public StockInsuficienteException (String mensaje){
        super(mensaje);
    }
    
}
