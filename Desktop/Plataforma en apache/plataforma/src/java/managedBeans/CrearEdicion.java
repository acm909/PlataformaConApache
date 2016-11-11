/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import modelo.IntefaceGestion;
import persistencia.Cursos;

/**
 *
 * @author Mañana
 */
@ManagedBean
@SessionScoped
public class CrearEdicion {
    ArrayList<Cursos> listaCursos =new ArrayList<>();

    @ManagedProperty ("#{gestion}")
    IntefaceGestion g;

    public IntefaceGestion getG() {
        return g;
    }

    public List<Cursos> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(ArrayList<Cursos> listaCursos) {
        this.listaCursos = listaCursos;
    }

    public void setG(IntefaceGestion g) {
        listaCursos=(ArrayList<Cursos>)g.listarCursos();
    }
    
    public CrearEdicion() {
        
        
    }
   
    
}
