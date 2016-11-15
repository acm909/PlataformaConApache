/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import managedBeans.FileUploadMBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import persistencia.Cursos;
import persistencia.Cursosedicion;
import persistencia.Matricula;
import persistencia.Usuarios;

/**
 *
 * @author Antonio1
 */
@Component (value="gestion")

public class GestionRecursos implements IntefaceGestion {
    @PersistenceContext (name="plataformaPU")
    EntityManager em;

    @Override
    public String obtenerUsuario(String dni, String password) {
        Usuarios us=em.find(Usuarios.class,dni);
        if (us!=null){
            if (us.getDni().equals(dni)&&us.getContraseña().equals(password)){
                //Inicia la sesion
                
                if (us.getFuncion().equals("alumno")){
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                    return "alumno";
                }else{
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("administrador", us);
                    return "administrador";
                }
                
                
            }else{
                return "error";
            }
        }else{
            return "error";
        }      

       }
    //Introduce un objeto curso
    @Transactional
    @Override
    public boolean introducirCurso(String idcurso, String nombre, String descripcion, String contenido) {
        Cursos cu=new Cursos();
        cu.setNombre(nombre);
        cu.setIdcurso(idcurso);      
        cu.setContenido(contenido);
        cu.setDescripcion(descripcion);
        em.persist(cu);
        return true;
    }
   
    
    @Override
    public List<Cursos> listarCursos(){
        Query sql=em.createNamedQuery("Cursos.findAll");
        ArrayList<Cursos> cur=(ArrayList<Cursos>)sql.getResultList();
        
        return cur;
        
        
    }
    @Override
    public List<Usuarios> listarProfesores(){
        
        Query q=em.createNamedQuery("Usuarios.findByFuncion").setParameter("funcion", "profesor");
        ArrayList<Usuarios> profesores=(ArrayList<Usuarios>)q.getResultList();
        return profesores;
    }
    
    public Usuarios obtenerUsuDni(String dni){
        Usuarios us=em.find(Usuarios.class, dni);
        return us;
    }
    public Cursos obtenerPorId(String id){
        Cursos cu=em.find(Cursos.class, id);
        return cu;
    }
   
  
   
    @Transactional
    @Override
    public boolean introEdicion(Date fIni, Date fFin, String idCurso, String idProfesor) {
        Cursosedicion ce= new Cursosedicion();
        Usuarios us=obtenerUsuDni(idProfesor);
        Cursos cu=obtenerPorId(idCurso);
        ce.setFechainicio(fIni);
        ce.setFechafin(fFin);
        ce.setIdprofesor(us);
        ce.setIdcurso(cu);
        Query q=em.createNamedQuery("Cursosedicion.contarEdiciones").setParameter("idcurso", cu);
        Object num= q.getSingleResult();
        long numero=(long)num;
        numero++;
        
        
        
        ce.setIdcursoedicion(idCurso+numero);
        ce.setNombre(idCurso);
        em.persist(ce); 
        return true;
    }
    
    @Override
    public List<Cursosedicion> listarCursosActivos(){
        
        Query query = em.createNamedQuery("Cursosedicion.findAll");
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = null;
        try {
            fechaActual = sd.parse(sd.format(new Date()));
        } catch (ParseException ex) {
            ex.getMessage();
        }
        List<Cursosedicion> aux = query.getResultList();
        List<Cursosedicion> lista = new ArrayList<>();
        for (Cursosedicion ce : aux) {
            
            if (ce.getFechafin().compareTo(fechaActual) > 0) {
                lista.add(ce);
            }
        }
        return lista;

    }

    @Override
    public boolean existeUsuario(String dni) {
        Usuarios usu=null;
        usu=em.find(Usuarios.class, dni);
        if (usu!=null){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public boolean matriUsuario (String dni, String idcursoedicion){
        Usuarios us=em.find(Usuarios.class, dni);
        Cursosedicion ce=em.find(Cursosedicion.class, idcursoedicion);
        Matricula m=new Matricula();
        m.setIdalumno(us);
        m.setIdedicion(ce);
        
        
        
        em.persist(m);
        
        
        return true;
    }
}
