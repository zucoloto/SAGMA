package br.mil.eb.ccomsex.atv.controller.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.mil.eb.ccomsex.atv.model.entity.Fracao;
import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.atv.model.service.FracaoService;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jsf.FacesUtil;
import br.mil.eb.ccomsex.atv.util.jsf.UsuarioLogado;

@Named
@ViewScoped
public class PesquisarFracaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FracaoService fracaoService;

	private Fracao fracaoSelecionado;

	private List<Fracao> fracoes;

	private TreeNode raiz;
	
	@Inject
	@UsuarioLogado
	private Usuario usuarioLogado;

	public void consultar() {
		List<Fracao> fracaoRaizes = fracaoService.raizes();
		
		//List<Fracao> fracaoRaizes = fracaoService.listarFracaoPai(usuarioLogado.getFracoes());

		this.raiz = new DefaultTreeNode("Raiz", null);

		adicionarNos(fracaoRaizes, this.raiz);
	}

	private void adicionarNos(List<Fracao> fracoesNos, TreeNode pai) {
		for (Fracao fracao : fracoesNos) {
			TreeNode no = new DefaultTreeNode(fracao, pai);

			adicionarNos(fracao.getSubFracoes(), no);
		}
	}

	public TreeNode getRaiz() {
		return raiz;
	}

	public void pesquisar() {
		this.fracoes = fracaoService.listarTodos();
	}

	public void excluir() {
		try {
			fracaoService.excluir(fracaoSelecionado);
			this.fracoes.remove(fracaoSelecionado);
			FacesUtil.addInfoMessage(FacesUtil.getMensagemI18n("registro_excluido"));
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(FacesUtil.getMensagemI18n(e.getMessage()));
		}
	}

	public Fracao getFracaoSelecionado() {
		return fracaoSelecionado;
	}

	public void setFracaoSelecionado(Fracao fracaoSelecionado) {
		this.fracaoSelecionado = fracaoSelecionado;
	}

	public List<Fracao> getFracoes() {
		return fracoes;
	}

}
