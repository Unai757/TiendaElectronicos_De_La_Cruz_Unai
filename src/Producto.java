import java.util.Scanner;

public abstract class Producto {
    Scanner sc = new Scanner(System.in);
    private final int id;
    private final String nombre;
    private final double precio;
    private final String marca;
    private int stock;

    public Producto(int id, String nombre, String marca, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getMarca() {
        return marca;
    }

    public double calcularPrecioFinal() {
        return getPrecio() + (getPrecio() * 0.21);
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", marca='" + marca + '\'' +
                ", stock=" + stock;
    }

    //Método que resta la cantidad que decida el usuario que quiere comprar del stock
    public boolean restarStock(int cantidad) {
        if (cantidad > stock) {
            return false;
        } else {
            stock -= cantidad;
            return true;
        }
    }
}

