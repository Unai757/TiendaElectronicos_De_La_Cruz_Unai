public class Portatil extends Producto {
    private int ram;
    private String procesador;

    public Portatil(int id, String nombre, String marca, double precio, int stock, int ram, String procesador) {
        super(id, nombre, marca, precio, stock);
        this.ram = ram;
        this.procesador = procesador;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    @Override
    public String toString() {
        return super.toString() +
                "ram=" + ram + "GB"+
                ", procesador='" + procesador + '\'' +
                '}';
    }

    //Sobrescribimos el metodo de calcular preciofinal añadiendo que si la marca es apple que sume 100 al precio
    @Override
    public double calcularPrecioFinal() {
        if (getMarca().equalsIgnoreCase("Apple")) {

            return super.calcularPrecioFinal() + 100;
        } else {
            return super.calcularPrecioFinal();
        }

    }
}

