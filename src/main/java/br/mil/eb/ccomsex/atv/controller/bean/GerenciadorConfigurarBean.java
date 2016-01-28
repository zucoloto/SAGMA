package br.mil.eb.ccomsex.atv.controller.bean;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.mil.eb.ccomsex.atv.model.entity.Configurar;
import br.mil.eb.ccomsex.atv.model.service.ConfigurarService;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jsf.FacesUtil;

@Named
@SessionScoped
public class GerenciadorConfigurarBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ConfigurarService configurarService;

	private Configurar configurar;

	private UploadedFile uploadedFile;

	public void inicializar() {
		carregarConfigurar();
	}

	public void limpar() {
		this.configurar = new Configurar();
	}

	public void carregarConfigurar() {
		this.configurar = this.configurarService.buscarPorId(1L);
	}

	public void salvar() {
		try {
			if (this.uploadedFile != null) {
				this.configurar.setLogo(this.uploadedFile.getContents());
			}
			configurarService.salvar(this.configurar);
			FacesUtil.addInfoMessage(FacesUtil.getMensagemI18n("registro_salvo"));
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(FacesUtil.getMensagemI18n(e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addFatalMessage(FacesUtil.getMensagemI18n("contato_administrador"));
		}
	}

	public StreamedContent getFoto() {
		DefaultStreamedContent content = null;
		if (this.configurar != null && this.configurar.getLogo() != null && this.configurar.getLogo().length > 0) {
			byte[] imagem = this.configurar.getLogo();
			content = new DefaultStreamedContent(new ByteArrayInputStream(imagem), "image/png", "logo.png");
		}
		return content;
	}

	public Configurar getConfigurar() {
		return configurar;
	}

	public void setConfigurar(Configurar configurar) {
		this.configurar = configurar;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

}