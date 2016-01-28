package br.mil.eb.ccomsex.atv.controller.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.mil.eb.ccomsex.atv.model.entity.AssuntoAtividade;
import br.mil.eb.ccomsex.atv.model.entity.Pop;
import br.mil.eb.ccomsex.atv.model.service.PopService;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jsf.FacesUtil;

@Named
@ViewScoped
public class GerenciadorPopBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PopService popService;

	private Pop pop;

	private AssuntoAtividade assuntoAtividade;

	private UploadedFile fileUpload;

	private StreamedContent fileDownload;

	String diretorio = "/home/zucoloto/Imagens/uploaded/";

	public void inicializar() {
		if (this.pop == null) {
			limpar();
		}

		if (this.assuntoAtividade == null) {
			this.assuntoAtividade = new AssuntoAtividade();
		}
	}

	public void limpar() {
		this.pop = new Pop();
		// this.assuntoAtividade = new AssuntoAtividade();
	}

	public boolean isEditando() {
		return this.pop.getId() != null;
	}

	public void salvar() {
		try {
			popService.salvar(this.pop);
			limpar();
			FacesUtil.addInfoMessage(FacesUtil.getMensagemI18n("registro_salvo"));
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(FacesUtil.getMensagemI18n(e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addFatalMessage(FacesUtil.getMensagemI18n("contato_administrador"));
		}
	}

	public void enviarArquivo(FileUploadEvent event) {
		String nomeArquivo = event.getFile().getFileName();

		File file = new File(diretorio + nomeArquivo);

		try (InputStream inputStream = event.getFile().getInputstream();
				OutputStream out = new FileOutputStream(file)) {

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			if (assuntoAtividade.getPop() != null) {
				this.pop = popService.buscarPorId(assuntoAtividade.getPop().getId());
			}

			pop.setAssuntoAtividade(assuntoAtividade);
			pop.setPop(nomeArquivo);
			popService.salvar(pop);

			this.pop = new Pop();
			this.assuntoAtividade = new AssuntoAtividade();

			FacesUtil.addInfoMessage("Arquivo enviado com sucesso!");

		} catch (IOException e) {
			FacesUtil.addErrorMessage(FacesUtil.getMensagemI18n(e.getMessage()));
		}
	}

	public void baixarArquivo() throws FileNotFoundException {
		String nomeArquivo = assuntoAtividade.getPop().getPop();

		String arquivo = diretorio + nomeArquivo;

		FileInputStream stream = new FileInputStream(arquivo);
		fileDownload = new DefaultStreamedContent(stream, arquivo, nomeArquivo);

	}

	public Pop getPop() {
		return pop;
	}

	public void setPop(Pop pop) {
		this.pop = pop;
	}

	public AssuntoAtividade getAssuntoAtividade() {
		return assuntoAtividade;
	}

	public void setAssuntoAtividade(AssuntoAtividade assuntoAtividade) {
		this.assuntoAtividade = assuntoAtividade;
	}

	public UploadedFile getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(UploadedFile fileUpload) {
		this.fileUpload = fileUpload;
	}

	public StreamedContent getFileDownload() {
		return fileDownload;
	}

	public void setFileDownload(StreamedContent fileDownload) {
		this.fileDownload = fileDownload;
	}

}