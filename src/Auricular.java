public class Auricular extends Producto {
    private String tipoDeConexión;

    public Auricular(int id, String nombre, String marca, double precio, int stock, String tipoDeConexión) {
        super(id, nombre, marca, precio, stock);
        this.tipoDeConexión = tipoDeConexión;
    }

    public String getTipo() {
        return tipoDeConexión;
    }

    public void setTipoDeConexión(String tipoDeConexión) {
        this.tipoDeConexión = tipoDeConexión;
    }

    @Override
    public String toString() {
        return super.toString()+
                "tipoDeConexión='" + tipoDeConexión + '\'' +
                '}';
    }
}
