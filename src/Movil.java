public class Movil extends Producto {
    private int bateria;
    private int camaraMPX;

    public Movil(int id, String nombre, String marca, double precio, int stock, int bateria, int camaraMPX) {
        super(id, nombre, marca, precio, stock);
        this.bateria = bateria;
        this.camaraMPX = camaraMPX;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public int getCamaraMPX() {
        return camaraMPX;
    }

    public void setCamaraMPX(int camaraMPX) {
        this.camaraMPX = camaraMPX;
    }

    //Sobrescribimos el metodo de toString de producto para añadir la información de movil
    @Override
    public String toString() {
        return super.toString() +
                "bateria=" + bateria + "mAh"+
                ", camaraMPX=" + camaraMPX + "mpx"+
                '}';
    }

    @Override
    public double calcularPrecioFinal() {
        return super.calcularPrecioFinal();
    }
}
