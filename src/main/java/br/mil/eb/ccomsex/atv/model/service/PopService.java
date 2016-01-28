package br.mil.eb.ccomsex.atv.model.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.mil.eb.ccomsex.atv.model.entity.Pop;
import br.mil.eb.ccomsex.atv.model.repository.PopRepository;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jpa.Transactional;

public class PopService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PopRepository popRepository;

	@Transactional
	public Pop salvar(Pop pop) {
		return popRepository.salvar(pop);
	}

	@Transactional
	public void excluir(Pop pop) {
		pop = buscarPorId(pop.getId());
		try {
			popRepository.excluir(pop);
		} catch (PersistenceException e) {
			throw new NegocioException("exclusao_negada");
		}
	}

	public Pop buscarPorId(Long id) {
		return popRepository.buscarPorId(id);
	}

	public List<Pop> listarTodos() {
		return popRepository.listarTodos();
	}
}
