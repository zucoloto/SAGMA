package br.mil.eb.ccomsex.atv.model.repository;

import java.util.List;

import br.mil.eb.ccomsex.atv.model.entity.Atividade;
import br.mil.eb.ccomsex.atv.model.entity.filter.AtividadeFilter;
import br.mil.eb.ccomsex.atv.model.entity.filter.FracaoFilter;

public interface AtividadeRepository {

	public void salvar(Atividade atividade);

	public void excluir(Atividade atividade);

	public Atividade buscarPorId(Long id);

	public List<Atividade> listarTodos();

	public List<Atividade> listarPorCriterio(AtividadeFilter filtroAtividade, FracaoFilter filtroFracao);

}
