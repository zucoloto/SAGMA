package br.mil.eb.ccomsex.atv.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

	public static boolean isPostback() {
		return FacesContext.getCurrentInstance().isPostback();
	}

	public static boolean isNotPostback() {
		return !isPostback();
	}

	public static String getMensagemI18n(String chave) {
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = context.getApplication().getResourceBundle(context, "msg").getString(chave);
		return msg;
	}

	// Mensagem - informação
	public static void addInfoMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	// Mensagem - aviso
	public static void addWarnMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
	}

	// Mensagem - erro
	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}

	// Mensagem - fatal
	public static void addFatalMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", message));
	}

}