/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLAOR;

import MODELO.Cliente;
import java.util.List;

public interface GestionarCliente {

    void guardar(Cliente cliente);
    List<Cliente> listar();
    void actualizar(String identificacion, Cliente nuevoCliente);
    void eliminar(String identificacion);
}
