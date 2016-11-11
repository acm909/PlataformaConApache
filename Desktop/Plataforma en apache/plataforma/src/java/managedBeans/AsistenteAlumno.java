
/*package managedBeans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;



@ManagedBean
@SessionScoped
public class AsistenteAlumno implements Serializable{

    
    public AsistenteAlumno() {
        
    }
    
    private Usuario usuario = new Usuario();
     
    //private boolean saltarPaso;
     
    public Usuario getUsuario() {
        return usuario;
    }
 
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
     
    public void guardar() {        
        FacesMessage msg = new FacesMessage("Ha funcionado.", "Bienvenido :" + usuario.getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }    
}*/
