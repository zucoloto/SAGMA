package br.mil.eb.ccomsex.atv.model.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.mil.eb.ccomsex.atv.model.entity.Atividade;
import br.mil.eb.ccomsex.atv.model.entity.filter.AtividadeFilter;
import br.mil.eb.ccomsex.atv.model.entity.filter.FracaoFilter;
import br.mil.eb.ccomsex.atv.model.repository.AtividadeRepository;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jpa.Transactional;

public class AtividadeService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AtividadeRepository atividadeRepository;

	@Transactional
	public void salvar(Atividade atividade) {
		atividadeRepository.salvar(atividade);
	}

	@Transactional
	public void excluir(Atividade atividade) {
		atividade = buscarPorId(atividade.getId());
		try {
			atividadeRepository.excluir(atividade);
		} catch (PersistenceException e) {
			throw new NegocioException("exclusao_negada");
		}
	}

	public Atividade buscarPorId(Long id) {
		return atividadeRepository.buscarPorId(id);
	}

	public List<Atividade> listarTodos() {
		return atividadeRepository.listarTodos();
	}

	public List<Atividade> listarPorCriterio(AtividadeFilter filtroAtividade, FracaoFilter filtroFracao) {
		return atividadeRepository.listarPorCriterio(filtroAtividade, filtroFracao);
	}
}
