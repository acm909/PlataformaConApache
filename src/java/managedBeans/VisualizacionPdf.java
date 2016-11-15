
package managedBeans;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.swing.text.Document;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


@ManagedBean
@SessionScoped
public class VisualizacionPdf implements Serializable{
    private static final long serialVersionUID = 1L;  
  
    private StreamedContent contenido;

    public VisualizacionPdf() {
    }
    
    public void onPrerender(ComponentSystemEvent event) {  
  
        try {  
            ByteArrayOutputStream out = new ByteArrayOutputStream();  
            Document document = new Document();  
            PdfWriter.getInstance(document, out);  
            document.open();
            document.close();  
            contenido = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public StreamedContent getContent() {  
        return contenido;  
    }  
  
    public void setContent(StreamedContent content) {  
        this.contenido = content;  
    }    
    
}
