package pre_entrega.ui;

import java.util.List;
import java.util.Scanner;
import pre_entrega.model.Pedido;
import pre_entrega.model.Producto;
import pre_entrega.service.ProductoService;
import pre_entrega.util.Validador;


public class MenuProducto {

    private final Scanner sc;
    private final ProductoService service;

    public MenuProducto(Scanner sc, ProductoService service) {
        this.sc = sc;
        this.service = service;
    }

    public void mostrarMenu() {
        System.out.println("======= TechLab - Gestión de Productos =======");
        System.out.println("1) Agregar producto");
        System.out.println("2) Listar productos");
        System.out.println("3) Buscar/actualizar producto por ID");
        System.out.println("4) Eliminar producto"); 
        System.out.println("5) Crear pedido");
        System.out.println("6) Listar pedidos (opcionales)");    
        System.out.println("7) Salir");
        System.out.println("==============================================");
    }

    public void agregarProducto() {
        System.out.println("--- Nuevo producto ---");
        String nombre = Validador.leerTexto(sc, "Nombre: ");
        double precio = Validador.leerDouble(sc, "Precio: ");
        int stock = Validador.leerEntero(sc, "Stock: ");
        String categoria = Validador.leerTexto(sc, "Categoría: ");


        Producto p = new Producto(nombre,precio,stock,categoria);
        Producto guardado = service.guardarProducto(p); 
        System.out.println("Producto agregado con el id " + guardado.getId());

    }

    public void listarProductos(){
        List<Producto> lista = service.listarProductos();
        if (lista.isEmpty()){
            System.out.println("No hay productos cargados.");
            return;
        }
    
        System.out.println("--- Catálogo ---");
        for(Producto p : lista){
            p.mostrar();
        }
    }

    private void buscarProducto(){ 
        int id = Validador.leerEntero(sc, "Ingrese el id del producto: ");

        Producto p = service.buscarProductoPorId(id);
        System.out.println("Encontrado: " + p);
    }

    public void actualizarProducto() {
        int id = Validador.leerEntero(sc, "Ingrese el id del producto a actualizar: ");

        Producto actual = service.buscarProductoPorId(id);
        System.out.println("Datos actuales: " + actual);

        System.out.println("--- Ingrese los nuevos datos ---");
        String nombre = Validador.leerTexto(sc, "Nombre: ");
        double precio = Validador.leerDouble(sc, "Precio: ");
        int stock = Validador.leerEntero(sc, "Stock: ");
        String categoria = Validador.leerTexto(sc, "Categoría: ");

        Producto datos = new Producto(nombre, precio, stock, categoria);
        Producto actualizado = service.actualizar(id, datos);

        System.out.println("Producto actualizado: " + actualizado);
    }

    public void eliminarProducto() {
        int id = Validador.leerEntero(sc, "Ingrese el id del producto a eliminar: ");
        Producto p = service.buscarProductoPorId(id);
    
        service.eliminar(id);
    }

    public void listarPedidos() {
        List<Pedido> pedidos = service.listarPedidos();
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos realizados.");
        } else {
            System.out.println("Pedidos realizados:");
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        }
    }

    public void crearPedido() {
        int id = Validador.leerEntero(sc, "Ingrese el id del producto a pedir: ");
        int cantidad = Validador.leerEntero(sc, "Ingrese la cantidad a pedir: ");
        service.crearPedido(id, cantidad);
    }

}

