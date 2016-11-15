/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import modelo.IntefaceGestion;
import persistencia.Cursosedicion;

/**
 *
 * @author Mañana
 */
@ManagedBean
@SessionScoped
public class MatricularAlumno {

    String dni;
    List<Cursosedicion> listarEdicionesActivas;
    String idcursoedicion;
    @ManagedProperty ("#{gestion}")
    IntefaceGestion g;

    public String getIdcursoedicion() {
        return idcursoedicion;
    }

    public void setIdcursoedicion(String idcursoedicion) {
        this.idcursoedicion = idcursoedicion;
    }

    public List<Cursosedicion> getListarEdicionesActivas() {
        listarEdicionesActivas=g.listarCursosActivos();
        return listarEdicionesActivas;
    }

    public void setListarEdicionesActivas(List<Cursosedicion> listarEdicionesActivas) {
        this.listarEdicionesActivas = listarEdicionesActivas;
    }

    public IntefaceGestion getG() {
        return g;
    }

    public void setG(IntefaceGestion g) {
        this.g = g;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public MatricularAlumno() {
    }
    public String matriAlumno(){
        System.out.println(dni +" " +idcursoedicion);
        boolean prueba = g.existeUsuario(dni);
        if (prueba){
            g.matriUsuario(dni, idcursoedicion);
            
            return "administrador";
        }else{
            return "error";
        }
    }
    
}
