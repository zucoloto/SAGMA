package br.mil.eb.ccomsex.atv.model.repository;

import java.util.List;

import br.mil.eb.ccomsex.atv.model.entity.Configurar;

public interface ConfigurarRepository {

	public void salvar(Configurar configurar);

	public Configurar buscarPorId(Long id);

	public List<Configurar> listarTodos();

}
