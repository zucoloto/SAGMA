package br.mil.eb.ccomsex.atv.model.repository;

import java.util.List;

import br.mil.eb.ccomsex.atv.model.entity.Pop;

public interface PopRepository {

	public Pop salvar(Pop pop);

	public void excluir(Pop pop);

	public Pop buscarPorId(Long id);

	public List<Pop> listarTodos();

}
