import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tienda tienda = new Tienda();

        // Cargamos desde el CSV
        try {
            tienda.cargarDesdeCSV();
            System.out.println("Inventario cargado correctamente.");
        } catch (Exception e) {
            System.out.println("Aviso: No se encontró el archivo de stock. La tienda está vacía.");
        }
//Creamos esta variable para salir del bucle
        boolean salir = false;
        System.out.println("!Bienvenido a la tienda de electronica¡");

        while (!salir) {
            System.out.println("---------------------------");
            System.out.println("1-Ver el catalogo");
            System.out.println("2-Comprar");
            System.out.println("3-Buscar producto");
            System.out.println("4-Finalizar compra y sacar factura");
            System.out.println("---------------------------");


            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    // Muestra el catálogo
                    tienda.obtenerCatalogo();
                    break;
                case 2:
                    //Añade al carrito lo que encontramos
                    tienda.anhadirAlCarrito();
                    break;
                case 3:
                    // Buscamos el producto por el id llamando al método de tienda
                    tienda.buscarProducto();
                    break;
                case 4:
                    //Generamos la factura
                    tienda.generarFactura();
                    System.out.println("La factura ha sido generada en el archivo llamado facturas.txt, !Muchas gracias por su visita¡");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}