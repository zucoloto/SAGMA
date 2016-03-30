package br.mil.eb.ccomsex.atv.controller.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.mil.eb.ccomsex.atv.model.entity.AssuntoAtividade;
import br.mil.eb.ccomsex.atv.model.entity.Fracao;
import br.mil.eb.ccomsex.atv.model.entity.Periodico;
import br.mil.eb.ccomsex.atv.model.entity.Pop;
import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.atv.model.service.AssuntoAtividadeService;
import br.mil.eb.ccomsex.atv.model.service.FracaoService;
import br.mil.eb.ccomsex.atv.model.service.PopService;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jsf.FacesUtil;
import br.mil.eb.ccomsex.atv.util.jsf.UsuarioLogado;

@Named
@ViewScoped
public class GerenciadorAssuntoAtividadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AssuntoAtividadeService assuntoAtividadeService;

	private AssuntoAtividade assuntoAtividade;

	private List<Fracao> fracoes = new ArrayList<>();

	// @Inject
	// private UsuarioService usuarioService;

	@Inject
	private FracaoService fracaoService;

	@Inject
	@UsuarioLogado
	private Usuario usuarioLogado;

	// private Usuario usuario;

	@Inject
	private ExternalContext externalContext;

	@Inject
	private PopService popService;

	private Pop pop;

	private UploadedFile fileUpload;

	private StreamedContent fileDownload;

	private @Inject GerenciadorConfigurarBean configurarBean;

	String diretorio = null;

	public void inicializar() {
		if (this.assuntoAtividade == null) {
			this.assuntoAtividade = new AssuntoAtividade();
		}

		if (this.pop == null) {
			this.pop = new Pop();
		}

		carregarFracao();
		carregarConfigurar();
	}

	public void carregarFracao() {
		// this.usuario = usuarioService.buscarPorId(usuarioLogado.getId());
		// fracoes = this.usuario.getFracoes();

		// fracoes =
		// fracaoService.listarFracoesDaDivisaoDoUsuario(usuarioLogado.getId());

		if (externalContext.isUserInRole("ADM_SISTEMA") || externalContext.isUserInRole("ADM_OM")) {
			this.fracoes = fracaoService.listarTodos();
		} else {
			this.fracoes = fracaoService.listarFracaoPai(usuarioLogado.getFracoes());
		}
	}

	public void carregarConfigurar() {
		diretorio = configurarBean.getConfigurar().getDiretorioPop();
	}

	public Periodico[] getPeriodico() {
		return Periodico.values();
	}

	public boolean isEditando() {
		return this.assuntoAtividade.getId() != null;
	}

	public void salvar() {
		try {
			this.assuntoAtividade = assuntoAtividadeService.salvar(this.assuntoAtividade);
			FacesUtil.addInfoMessage(FacesUtil.getMensagemI18n("registro_salvo"));
			this.assuntoAtividade = new AssuntoAtividade();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(FacesUtil.getMensagemI18n(e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addFatalMessage(FacesUtil.getMensagemI18n("contato_administrador"));
		}
	}

	public AssuntoAtividade getAssuntoAtividade() {
		return assuntoAtividade;
	}

	public void setAssuntoAtividade(AssuntoAtividade assuntoAtividade) {
		this.assuntoAtividade = assuntoAtividade;
	}

	public List<Fracao> getFracoes() {
		return fracoes;
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

			this.pop = popService.salvar(pop);

			this.assuntoAtividade = assuntoAtividadeService.buscarPorId(assuntoAtividade.getId());

			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update("form:arquivoPop");
			requestContext.update(":form:buttonDownload");

			FacesUtil.addInfoMessage(FacesUtil.getMensagemI18n("arquivo_enviado"));

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