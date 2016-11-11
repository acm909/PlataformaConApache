/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import persistencia.Cursos;

/**
 *
 * @author Antonio1
 */
public interface IntefaceGestion {
    String obtenerUsuario (String dni, String password);
    
    boolean introducirCurso (String idcurso, String nombre, String descripcion, String contenido);
    
    List <Cursos> listarCursos();
    
}


