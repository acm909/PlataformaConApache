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
import persistencia.Cursosedicion;


@ManagedBean
@SessionScoped
public class CursosActivos {

    List<Cursosedicion> listaCursosActivos=new ArrayList<>();
    
    @ManagedProperty ("#{gestion}")
    IntefaceGestion g;

    public List<Cursosedicion> getListaCursosActivos() {
        listaCursosActivos=g.listarCursosActivos();
        return listaCursosActivos;
    }

    public void setListaCursosActivos(ArrayList<Cursosedicion> listaCursosActivos) {
        this.listaCursosActivos = listaCursosActivos;
    }

    public IntefaceGestion getG() {
        return g;
    }

    public void setG(IntefaceGestion g) {
        
        this.g = g;
    }
    
    public CursosActivos() {
       
    }
    
    
}
