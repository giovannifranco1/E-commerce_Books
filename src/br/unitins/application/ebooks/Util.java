package br.unitins.application.ebooks;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

import com.sun.faces.component.visit.FullVisitContext;

public class Util {
	public static void addErrorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    public static void addWarningMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
    }
    public static void main(String[] args) {
		System.out.println(hash("giovanni@gmail.com"+"123"));
	}
    public static void addInfoMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }
    public static void redirect (String page) {
    	try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
		} catch (IOException e) {
			e.printStackTrace();
			addErrorMessage("Erro ao direcionar a pagina!!");
		}
    }
    public static UIComponent findComponent(final String id) {
	    FacesContext context = FacesContext.getCurrentInstance(); 
	    UIViewRoot root = context.getViewRoot();
	    final UIComponent[] found = new UIComponent[1];

	    root.visitTree(new FullVisitContext(context), new VisitCallback() {     
	        @Override
	        public VisitResult visit(VisitContext context, UIComponent component) {
	            if (component != null 
	                && component.getId() != null 
	                && component.getId().equals(id)) {
	                found[0] = component;
	                return VisitResult.COMPLETE;
	            }
	            return VisitResult.ACCEPT;              
	        }
	    });

	    return found[0];
	}	

	public static String hash(String valor) {
		// TODO Auto-generated method stub
		return DigestUtils.sha256Hex(valor);
	}
}
