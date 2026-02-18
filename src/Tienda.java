import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Tienda {
    private final List<Producto> catalogo;
    private  final List<Producto> carrito;




    //Se inician aquí las lista, ya que si no nos dará la excepcion NullPointerException
    public Tienda() {
        this.catalogo = new ArrayList<>();
        this.carrito = new ArrayList<>();
    }

    //Añade al carrito los productos que se quiera añadir
    public boolean anhadirAlCarrito(int id, int cantidadSolicitada) {
        Producto p = buscarProducto(id);

        if (!p.restarStock(cantidadSolicitada)) {
            System.out.println("No hay suficiente stock");
            return false;
        }
        for (int i = 0; i < cantidadSolicitada; i++) {
            carrito.add(p);
        }
        return true;
    }

    //Recorre el catálogo entero y muestra su información
    public void obtenerCatalogo() {
        for (Producto catalogo : this.catalogo) {
            System.out.println(catalogo);
        }
    }

    //busca información dependiendo del ID si no encuentra ningún producto dice que no se ha encontrado nada
    public Producto buscarProducto(int id) {
        for (Producto producto : this.catalogo) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }
//En este metodo obtenemos los datos desde un csv
    public void cargarDesdeCSV() {

        try (BufferedReader br = new BufferedReader(new FileReader("stock.csv"))) {

            String linea;

            while ((linea = br.readLine()) != null) {


                String[] datos = linea.split(";");

                //Separamos los datos en huecos de un array separados por puntos y comas cada dato ocupando un espacio en el array
                String tipo = datos[0];
                int id = Integer.parseInt(datos[1]);
                String nombre = datos[2];
                String marca = datos[3];
                double precio = Double.parseDouble(datos[4]);
                int stock = Integer.parseInt(datos[5]);

                // Aquí comprobamos que si es móvil también se añada las variables que tiene el móvil
                if (tipo.equalsIgnoreCase("Movil")) {

                    int bateria = Integer.parseInt(datos[6]);
                    int camaraMPX = Integer.parseInt(datos[7]);

                    // Creamos el objeto de movil y lo metemos al catálogo
                    Producto p = new Movil(id, nombre, marca, precio, stock, bateria, camaraMPX);
                    this.catalogo.add(p);

                    //Aquí se comprueba que si el tipo de Producto es un portátil se usa se añade los dos datos que quedan

                } else if (tipo.equalsIgnoreCase("Portatil")) {
                    // Sacamos los datos exclusivos de Portátil
                    int ram = Integer.parseInt(datos[6]);
                    String procesador = datos[7];

                    // Creamos el objeto y lo metemos al catálogo
                    Producto p = new Portatil(id, nombre, marca, precio, stock, ram, procesador);
                    this.catalogo.add(p);
                } else if (tipo.equalsIgnoreCase("Auricular")) {
                    String tipoDeConexion = datos[6];
                    Producto p = new Auricular(id, nombre, marca, precio, stock, tipoDeConexion);
                    catalogo.add(p);
                }
            }
            System.out.println("Carga de datos completada con éxito.");

        } catch (IOException e) {

            System.out.println("Error leyendo el archivo CSV: " + e.getMessage());
        } catch (NumberFormatException e) {

            System.out.println("Error de formato en el CSV  " + e.getMessage());
        }

    }

    public void generarFactura() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("facturas.txt"))) {
            bw.write("-----------Factura de la compra----------------");
            bw.newLine();
            double precioTotal = 0;
            for (Producto p : this.carrito) {

                //Ponemos que la variable del precio de cada producto sea el metodo de calcularPrecio de cada objeto
                double precioProducto = p.calcularPrecioFinal();

                //Añadimos los datos al archivo
                bw.write(p.getNombre() + " (" + p.getMarca() + ")");
                bw.write(" ........................ ");
                bw.write(precioProducto + " €");


                bw.newLine();


                // Sumamos al precio total
                precioTotal += precioProducto;

            }
            bw.write("El precio total de todos los productos comprados es " + precioTotal + " €");
            bw.newLine();
            bw.write("-------------------------------------------");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

