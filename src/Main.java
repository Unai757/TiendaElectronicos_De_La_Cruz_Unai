import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Tienda tienda = new Tienda();

        // Llamamos al metodo cargarDesdeCSV para cargar la lista desde un archivo .csv
        try {
            tienda.cargarDesdeCSV();
            System.out.println("Inventario cargado correctamente.");
        } catch (Exception e) {
            System.out.println("Aviso: No se encontró el archivo de stock. La tienda está vacía.");
        }

        //Creamos esta variable para saber cuando salir del bucle while
        boolean salir = false;
        System.out.println("!Bienvenido a la tienda de electronica¡");
        //Creamos un menu cada uno haciendo una cosa

        while (!salir) {
            System.out.println("---------------------------");
            System.out.println("1-Ver el catalogo");
            System.out.println("2-Comprar");
            System.out.println("3-Buscar producto");
            System.out.println("4-Finalizar compra y sacar factura");
            System.out.println("0-Salir del programa");
            System.out.println("---------------------------");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    //Aqui ponemos que la lista de la tienda es la del metodo obtenerCatalogo de tienda
                    tienda.obtenerCatalogo();
                    break;
                case 2:
                    //En este caso pedimos él, id y la cantidad del producto después llamando al método añadirAlCarrito
                    //Comprobando que si no da problemas si se puede añadir al carrito esos productos
                    System.out.println("Dime el id del producto");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Dime la cantidad del producto");
                    int cantidad = sc.nextInt();
                    sc.nextLine();
                    boolean exito = tienda.anhadirAlCarrito(id, cantidad);
                    if (exito) {
                        System.out.println("El producto se ha añadido al carro sin problemas");
                    } else {
                        System.out.println("Ha habido un error añadiendo el producto");
                    }
                    break;
                case 3:
                    System.out.println("Dime el id del producto");
                    int idBuscar = sc.nextInt();
                    Producto productoEncontrado = tienda.buscarProducto(idBuscar);
                    //Si el producto no es null es que se ha encontrado
                    if (productoEncontrado != null) {
                        System.out.println("El producto se ha encontrado!" + productoEncontrado);
                    } else {
                        System.out.println("El producto con ese id no existe");
                    }
                    break;
                case 4:
                    //En este método llamamos a generarFactura
                    tienda.generarFactura();
                    System.out.println("La factura ha sido generada en el archivo llamado facturas.txt");
                    break;
                case 0:
                    System.out.println("Hasta luego muchas gracias por su visita");
                    salir = true;

            }
        }

    }
}
