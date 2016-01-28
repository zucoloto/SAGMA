package br.mil.eb.ccomsex.atv.model.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.mil.eb.ccomsex.atv.model.entity.Grupo;
import br.mil.eb.ccomsex.atv.model.repository.GrupoRepository;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jpa.Transactional;

public class GrupoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoRepository grupoRepository;

	@Transactional
	public void salvar(Grupo grupo) {
		if (grupo.getNomeGrupo() == null || grupo.getNomeGrupo().trim().equals("")) {
			throw new NegocioException("O nome da GRUPO é obrigatório!");
		}
		grupoRepository.salvar(grupo);
	}

	@Transactional
	public void excluir(Grupo grupo) {
		grupo = buscarPorId(grupo.getId());
		try {
			grupoRepository.excluir(grupo);
		} catch (PersistenceException e) {
			throw new NegocioException("exclusao_negada");
		}
	}

	public List<Grupo> listarTodos() {
		return grupoRepository.listarTodos();
	}

	public Grupo buscarPorId(Long id) {
		return grupoRepository.buscarPorId(id);
	}
}
