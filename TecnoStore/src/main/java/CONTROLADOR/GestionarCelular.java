
package CONTROLADOR;

import MODELO.Celular;
import java.util.ArrayList;

public interface GestionarCelular {

    void guardar(Celular c);

    void actualizar(Celular c, int id);

    void eliminar(int id);

    ArrayList<Celular> listar();

    Celular buscar(int id);

    void mostrarStockBajo(); 
}

