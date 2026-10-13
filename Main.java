import java.util.Scanner;
import pre_entrega.service.ProductoService;
import pre_entrega.ui.MenuProducto;
import pre_entrega.util.Validador;
import pre_entrega.exeption.ProductoNoEncontradoException;
import pre_entrega.exeption.StockInsuficienteException;
import pre_entrega.model.Producto;

public class Main {
    public static void main(String[] args) {

        ProductoService service = new ProductoService();
        Scanner sc = new Scanner(System.in);
        MenuProducto menu = new MenuProducto(sc, service);
        cargarDatos(service); // Carga de datos iniciales
        
        int opcion;

        do {
            menu.mostrarMenu();
            opcion = Validador.leerEntero(sc, "Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 -> menu.agregarProducto();
                    case 2 -> menu.listarProductos();
                    case 3 -> menu.actualizarProducto();
                    case 4 -> menu.eliminarProducto();
                    case 5 -> menu.crearPedido();
                    case 6 -> menu.listarPedidos();
                    case 7 ->  System.out.println("Saliendo del programa..."); 
                    default -> System.out.println("Opción inválida. Por favor, ingrese un número entre 1 y 7.");
                }
            } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } while (opcion != 6);

        sc.close();
    }

    private static void cargarDatos(ProductoService service) {
        service.guardarProducto(new Producto("Coca cola", 100.0, 10, "Bebidas"));
        service.guardarProducto(new Producto("Pepsi", 90.0, 5, "Bebidas"));
        service.guardarProducto(new Producto("Fanta", 80.0, 8, "Bebidas"));
        service.guardarProducto(new Producto("Galletas", 150.0, 20, "Comida"));
        service.guardarProducto(new Producto("Chocolates", 200.0, 15, "Comida"));
        
    }

}
