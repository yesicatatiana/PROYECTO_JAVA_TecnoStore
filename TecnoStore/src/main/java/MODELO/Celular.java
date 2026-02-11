
package MODELO;

/**
 *
 * @author camper
 */
public class Celular {

    private int id;
    private String marca;
    private String modelo;
    private String sistemaOperativo;
    private String gama; // Baja, Media, Alta
    private int precio;
    private int stock;

    public Celular(int id, String marca, String modelo, String sistemaOperativo, String gama, int precio, int stock) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.gama = gama;
        this.precio = precio;
        this.stock = stock;
    }

    public Celular() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getGama() {
        return gama;
    }

    public void setGama(String gama) {
        this.gama = gama;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        if (precio > 0) {
            this.precio = precio;
        } else {
            throw new IllegalArgumentException("El precio debe ser positivo");
        }
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        } else {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }

    @Override
    public String toString() {
        return """
               *****************************
               Id:              %s
               Marca:           %s
               Modelo:          %s
               Sistema Op:      %s
               Gama:            %s
               Precio:          %s
               Stock:           %s
               """.formatted(id, marca, modelo, sistemaOperativo, gama, precio, stock);
    }
}
