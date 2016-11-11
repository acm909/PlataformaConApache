/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.IntefaceGestion;
import persistencia.Usuarios;

/**
 *
 * @author Antonio1
 */
@ManagedBean
@RequestScoped
public class PruebaLogin {
    String dni;
    String password;
    
    @ManagedProperty ("#{gestion}")
    IntefaceGestion g;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }  
    
    public String validarUsuario(){
        return g.obtenerUsuario(dni, password);
        
    }  
    public PruebaLogin() {
    }
    
}
