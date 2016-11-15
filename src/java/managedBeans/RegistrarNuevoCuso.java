/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import modelo.IntefaceGestion;

/**
 *
 * @author Mañana
 */
@ManagedBean
@SessionScoped
public class RegistrarNuevoCuso {
    @ManagedProperty ("#{gestion}")
    IntefaceGestion g;
    
    String idCurso;
    String nombre;
    String descripcion;
    String contenido;

    public IntefaceGestion getG() {
        return g;
    }

    public void setG(IntefaceGestion g) {
        this.g = g;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
     
    
    public RegistrarNuevoCuso() {
        
    }
    public String registrarCurso(){
        if(g.introducirCurso(idCurso, nombre, descripcion, contenido)){
            return "administrador";
        }else{
            return "error";
        }
        
    }
    
}
