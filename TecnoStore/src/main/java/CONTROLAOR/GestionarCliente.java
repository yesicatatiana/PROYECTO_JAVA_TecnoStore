/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLAOR;

import MODELO.Cliente;
import java.util.ArrayList;

public interface GestionarCliente {

    void guardar(Cliente a);

    void actualizar(Cliente a, int id);

    void eliminar(int id);

    ArrayList<Cliente> listar();

    Cliente buscar(int id);
}
