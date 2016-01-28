package br.mil.eb.ccomsex.atv.model.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.mil.eb.ccomsex.atv.model.entity.AssuntoAtividade;
import br.mil.eb.ccomsex.atv.model.entity.Fracao;
import br.mil.eb.ccomsex.atv.model.repository.AssuntoAtividadeRepository;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jpa.Transactional;

public class AssuntoAtividadeService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AssuntoAtividadeRepository assuntoAtividadeRepository;

	@Transactional
	public AssuntoAtividade salvar(AssuntoAtividade assuntoAtividade) {
		return assuntoAtividadeRepository.salvar(assuntoAtividade);
	}

	@Transactional
	public void excluir(AssuntoAtividade assuntoAtividade) {
		assuntoAtividade = buscarPorId(assuntoAtividade.getId());
		try {
			assuntoAtividadeRepository.excluir(assuntoAtividade);
		} catch (PersistenceException e) {
			throw new NegocioException("exclusao_negada");
		}
	}

	public AssuntoAtividade buscarPorId(Long id) {
		return assuntoAtividadeRepository.buscarPorId(id);
	}

	public List<AssuntoAtividade> listarTodos() {
		return assuntoAtividadeRepository.listarTodos();
	}

	public List<AssuntoAtividade> listarPorFracao(List<Fracao> fracoes) {
		return assuntoAtividadeRepository.listarPorFracao(fracoes);
	}
}
