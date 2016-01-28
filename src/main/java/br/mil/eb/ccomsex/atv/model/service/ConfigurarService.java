package br.mil.eb.ccomsex.atv.model.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.mil.eb.ccomsex.atv.model.entity.Configurar;
import br.mil.eb.ccomsex.atv.model.repository.ConfigurarRepository;
import br.mil.eb.ccomsex.atv.util.jpa.Transactional;

public class ConfigurarService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ConfigurarRepository configurarRepository;

	@Transactional
	public void salvar(Configurar configurar) {
		configurarRepository.salvar(configurar);
	}

	public Configurar buscarPorId(Long id) {
		return configurarRepository.buscarPorId(id);
	}

	public List<Configurar> listarTodos() {
		return configurarRepository.listarTodos();
	}

}
