package br.mil.eb.ccomsex.atv.model.repository;

import java.util.List;

import br.mil.eb.ccomsex.atv.model.entity.Grupo;

public interface GrupoRepository {

	public void salvar(Grupo grupo);

	public void excluir(Grupo grupo);

	public Grupo buscarPorId(Long id);

	public List<Grupo> listarTodos();

}
