package br.com.drogaria.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
	
	//Mensagem Genérica
	public static void addMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}
	
	//Mensagem de erro
	public static void errMensagem(String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensagem,mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null,msg);
	}
}
	