import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tienda {
    Scanner sc = new Scanner(System.in);
    //Creamos las dos listas
     List<Producto> catalogo = new ArrayList<>();
    List<Producto> carrito = new ArrayList<>();



    //Metodo para añadir objetos al carrito
    public void anhadirAlCarrito() {
        System.out.println("--- COMPRAR PRODUCTO ---");


        System.out.println("Dime el id del producto:");
        int id = sc.nextInt();
        sc.nextLine();


        Producto p = buscarProductoInterno(id);

        //Salimos del metodo al no encontrar ningun producto con ese id
        if (p == null) {
            System.out.println(" Error: No existe ningún producto con este ID " + id);
            return;
        }


        System.out.println("Has seleccionado: " + p.getNombre());
        System.out.println("Dime la cantidad que quieres:");
        int cantidadSolicitada = sc.nextInt();
        sc.nextLine();

        // En este metodo llamos al metodo restarStock si hay suficiente stock lo añadimos y si no lo hay nos lanza un mensaje de que no hay stock
        if (!p.restarStock(cantidadSolicitada)) {
            System.out.println("No hay suficiente stock. Solo quedan " + p.getStock());
        } else {
            for (int i = 0; i < cantidadSolicitada; i++) {
                carrito.add(p);
            }
            System.out.println("El producto se ha añadido al carrito sin problemas.");
        }
    }

//Nos deja buscar el producto por id
    public void buscarProducto() {
        System.out.println("Dime el id del producto a buscar:");
        int id = sc.nextInt();
        sc.nextLine();

        Producto p = buscarProductoInterno(id);

        if (p != null) {
            System.out.println("¡El producto se ha encontrado!");
            System.out.println(p);
        } else {
            System.out.println("El producto con ese id no existe.");
        }
    }

    //Metodo para buscar producto por id para no tener que escribir el bucle cada vez que queramos buscar por id
    public Producto buscarProductoInterno(int id) {
        for (Producto producto : this.catalogo) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }
//Nos muestra el catálogo
    public void obtenerCatalogo() {
        System.out.println("--- CATÁLOGO DISPONIBLE ---");
        if (catalogo.isEmpty()) {
            System.out.println("El catálogo está vacío.");
        }
        for (Producto p : this.catalogo) {
            System.out.println(p);
        }
    }
//Metodo para cargar desde CSV separando los datos haciendo con un split con ; para luego meterlo en un array
    public void cargarDesdeCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("stock.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                String tipo = datos[0];
                int id = Integer.parseInt(datos[1]);
                String nombre = datos[2];
                String marca = datos[3];
                double precio = Double.parseDouble(datos[4]);
                int stock = Integer.parseInt(datos[5]);
                //Dependiendo si es movil auricular o portatil añade las variables de cada clase

                if (tipo.equalsIgnoreCase("Movil")) {
                    int bateria = Integer.parseInt(datos[6]);
                    int camaraMPX = Integer.parseInt(datos[7]);
                    Producto p = new Movil(id, nombre, marca, precio, stock, bateria, camaraMPX);
                    this.catalogo.add(p);

                } else if (tipo.equalsIgnoreCase("Portatil")) {
                    int ram = Integer.parseInt(datos[6]);
                    String procesador = datos[7];
                    Producto p = new Portatil(id, nombre, marca, precio, stock, ram, procesador);
                    this.catalogo.add(p);

                } else if (tipo.equalsIgnoreCase("Auricular")) {
                    String tipoDeConexion = datos[6];
                    Producto p = new Auricular(id, nombre, marca, precio, stock, tipoDeConexion);
                    this.catalogo.add(p);
                }
            }
            System.out.println("Carga de datos completada con éxito.");

        } catch (IOException e) {
            System.out.println("Error leyendo el archivo CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error de formato en el CSV  " + e.getMessage());
        }
    }
//Este metodo genera la factura con los objetos que se ha comprado
    public void generarFactura() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("facturas.txt"))) {
            bw.write("-----------Factura de la compra----------------");
            bw.newLine();
            double precioTotal = 0;



            for (Producto p : this.carrito) {
                //Creamos esta variable para que la variable sea el resultado de calcularPrecioFinal
                double precioProducto = p.calcularPrecioFinal();

                bw.write(p.getNombre() + " (" + p.getMarca() + ")");
                bw.write(" ........................ ");
                bw.write(precioProducto + " €");
                bw.newLine();

                precioTotal += precioProducto;
            }
            bw.write("-------------------------------------------");
            bw.newLine();
            bw.write("El precio total de todos los productos comprados es " + precioTotal + " €");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

