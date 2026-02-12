
package MODELO;

public class Cliente {

    private int id;
    private String nombre;
    private String identificacion;
    private String correo;
    private String telefono;

    public Cliente(int id, String nombre, String identificacion, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("El id debe ser positivo");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (!nombre.isBlank()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        if (!identificacion.isBlank()) {
            this.identificacion = identificacion;
        } else {
            throw new IllegalArgumentException("La identificacion no puede estar vacia");
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo.contains("@")) {
            this.correo = correo;
        } else {
            throw new IllegalArgumentException("Correo invalido");
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (!telefono.isBlank()) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("Telefono invalido");
        }
    }

    @Override
    public String toString() {
        return """
               *****************************
               Id:              %s
               Nombre:          %s
               Identificacion:  %s
               Correo:          %s
               Telefono:        %s
               """.formatted(id, nombre, identificacion, correo, telefono);
    }
}
