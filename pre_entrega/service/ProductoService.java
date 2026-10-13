package pre_entrega.service;
import java.util.ArrayList;
import java.util.List;
import pre_entrega.exeption.ProductoNoEncontradoException;
import pre_entrega.model.Producto;
import pre_entrega.util.Validador;
import pre_entrega.model.Pedido;

public class ProductoService {
    private List<Producto> productos = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();
    private static int contadorId = 1;

    public Producto guardarProducto (Producto p) {
        Validador.validarNombre(p.getNombre());
        Validador.validarPrecio(p.getPrecio());
        Validador.validarStock(p.getStock());
        Validador.validarCategoria(p.getCategoria());

        p.setId(contadorId);
        contadorId++;
        productos.add(p);

        return p;

    }

    public List<Producto> listarProductos(){
        return productos;
    }
    
    public List<Pedido> listarPedidos(){
        return pedidos;
    }

    public Producto buscarProductoPorId(int id) {
        for(Producto p : productos){
            if( p.getId() == id ){
                return p;
            }
        }
        throw new ProductoNoEncontradoException("No se encontro un producto con el id " + id);
    }

    public Producto actualizar(int id,Producto datos){
        Producto p = buscarProductoPorId(id);

        Validador.validarNombre(datos.getNombre());
        Validador.validarPrecio(datos.getPrecio());
        Validador.validarStock(datos.getStock());
        Validador.validarCategoria(datos.getCategoria());

        p.setNombre(datos.getNombre());
        p.setPrecio(datos.getPrecio());
        p.setStock(datos.getStock());
        p.setCategoria(datos.getCategoria());

        return p;

    }

    public void eliminar(int id){
        Producto p = buscarProductoPorId(id);
        productos.remove(p);
    }

    public void crearPedido(int id, int cant) {
        Producto p = buscarProductoPorId(id); 
        Pedido pedido = new Pedido(this);
        pedido.crearPedido(p, cant);

        pedidos.add(pedido);
        this.pedidos.add(pedido);
    }
    

    
}