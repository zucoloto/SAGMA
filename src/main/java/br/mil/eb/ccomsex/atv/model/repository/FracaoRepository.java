package br.mil.eb.ccomsex.atv.model.repository;

import java.util.List;

import br.mil.eb.ccomsex.atv.model.entity.Fracao;

public interface FracaoRepository {

	public void salvar(Fracao fracao);

	public void excluir(Fracao fracao);

	public Fracao buscarPorId(Long id);

	public List<Fracao> listarTodos();

	public List<Fracao> listarFracaoPai(List<Fracao> fracoes);

	public List<Fracao> raizes();

}
