/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
@ManagedBean
@SessionScoped
public class FileUploadMBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Part file1;
    private Part file2;
    private String message;
    
    @ManagedProperty("#{registrarNuevoCuso}")
    private RegistrarNuevoCuso bean1;

    public RegistrarNuevoCuso getBean1() {
        return bean1;
    }

    public void setBean1(RegistrarNuevoCuso bean1) {
        this.bean1 = bean1;
    }

    public Part getFile1() {
        return file1;
    }
    public void setFile1(Part file1) {
        this.file1 = file1;
    }
    public Part getFile2() {
        return file2;
    }
    public void setFile2(Part file2) {
        this.file2 = file2;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String uploadFile() throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context
                .getExternalContext().getContext();
        String path = servletContext.getRealPath("");
        boolean file1Success = false;
        if (file1.getSize() > 0) {
            String fileName = Utils.getFileNameFromPart(file1);
            bean1.contenido=("http://localhost:8080/plataforma/"+fileName) ;            
            /**
            * destination where the file will be uploaded
            */
            File outputFile = new File(path + File.separator 
                    + File.separator + fileName);
            inputStream = file1.getInputStream();
            outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[Constants.BUFFER_SIZE];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            file1Success = true;
        }
        boolean file2Success = false;
        if (file2.getSize() > 0) {
            String fileName = Utils.getFileNameFromPart(file2);

            File outputFile = new File(path + File.separator 
                    + File.separator + fileName);
            inputStream = file2.getInputStream();
            outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[Constants.BUFFER_SIZE];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            file2Success = true;
        }
        if (file1Success || file2Success) {
            System.out.println("Fichero subido a : " + path );
            /**
            * set the success message when the file upload is successful
            */
            setMessage("http://localhost:8080/plataforma/" + Utils.getFileNameFromPart(file1) +" -- "
                    +"http://localhost:8080/plataforma/"+ Utils.getFileNameFromPart(file2));
           
        } else {
            /**
            * set the error message when error occurs during the file upload
            */
            setMessage("Error, seleccione al menos un fichero!");
        }
        /**
        * return to the same view
        */
        return null;
    }
}