package br.mil.eb.ccomsex.atv.model.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.atv.model.repository.UsuarioRepository;
import br.mil.eb.ccomsex.atv.util.jpa.Transactional;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Transactional
	public Usuario salvar(Usuario usuario) {
		// if (usuario.getSenha() != null ||
		// !usuario.getSenha().trim().equals("")) {
		// usuario.setSenha(convertStringToMd5(usuario.getSenha()));
		// }
		return usuarioRepository.salvar(usuario);
	}

	public List<Usuario> listarTodos() {
		return usuarioRepository.listarTodos();
	}

	public Usuario buscarPorId(Long id) {
		return usuarioRepository.buscarPorId(id);
	}

	public Usuario buscarPorIdentidade(String identidade) {
		return usuarioRepository.buscarPorIdentidade(identidade);
	}

	// private String convertStringToMd5(String valor) {
	// MessageDigest mDigest;
	// try {
	// // Instanciamos o nosso HASH MD5, poderíamos usar outro como
	// // SHA, por exemplo, mas optamos por MD5.
	// mDigest = MessageDigest.getInstance("MD5");
	//
	// // Convert a String valor para um array de bytes em MD5
	// byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));
	//
	// // Convertemos os bytes para hexadecimal, assim podemos salvar
	// // no banco para posterior comparação se senhas
	// StringBuffer sb = new StringBuffer();
	// for (byte b : valorMD5) {
	// sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,
	// 3));
	// }
	//
	// return sb.toString();
	//
	// } catch (NoSuchAlgorithmException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// return null;
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// return null;
	// }
	// }
}