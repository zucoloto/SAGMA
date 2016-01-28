package br.mil.eb.ccomsex.atv.model.repository;

import java.util.List;

import br.mil.eb.ccomsex.atv.model.entity.Usuario;

public interface UsuarioRepository {

	public Usuario salvar(Usuario usuario);

	public void excluir(Usuario usuario);

	public List<Usuario> listarTodos();

	public Usuario buscarPorId(Long id);

	public Usuario buscarPorNome(String nome);

	public Usuario buscarPorIdentidade(String identidade);

	public Usuario buscarPorNomeSenha(String nome, String senha);

}
