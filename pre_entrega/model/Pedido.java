package pre_entrega.model;
import pre_entrega.service.ProductoService;
import java.util.ArrayList;
import java.util.List;
import pre_entrega.exeption.StockInsuficienteException;

public class Pedido {
    
    private ProductoService service;
    private List<Producto> productos = new ArrayList<>();

    public Pedido(ProductoService service) {
        this.service = service;
    }

    public void crearPedido(Producto p, int cant) {
        if (!p.hayStock(cant)) {
            throw new StockInsuficienteException("No hay suficiente stock para el producto " + p.getNombre());
        } else {
            p.setStock(p.getStock() - cant);
            System.out.println("El costo total del pedido es: $" + p.getPrecio() * cant);
        }
        productos.add(p);
    }

    @Override
    public String toString() {
        for (Producto p : productos) {
            System.out.println(p.toString());
        }
        return "";
    }    

}

