package br.mil.eb.ccomsex.atv.model.repository;

import java.util.List;

import br.mil.eb.ccomsex.atv.model.entity.AssuntoAtividade;
import br.mil.eb.ccomsex.atv.model.entity.Fracao;

public interface AssuntoAtividadeRepository {

	public AssuntoAtividade salvar(AssuntoAtividade assuntoAtividade);

	public void excluir(AssuntoAtividade assuntoAtividade);

	public AssuntoAtividade buscarPorId(Long id);

	public List<AssuntoAtividade> listarTodos();

	public List<AssuntoAtividade> listarPorFracao(List<Fracao> fracoes);

}
