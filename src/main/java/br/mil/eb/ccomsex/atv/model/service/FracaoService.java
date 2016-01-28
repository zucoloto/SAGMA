package br.mil.eb.ccomsex.atv.model.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.mil.eb.ccomsex.atv.model.entity.Fracao;
import br.mil.eb.ccomsex.atv.model.repository.FracaoRepository;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jpa.Transactional;

public class FracaoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FracaoRepository fracaoRepository;

	@Transactional
	public void salvar(Fracao fracao) {
		if (fracao.getNomeFracao() == null || fracao.getNomeFracao().trim().equals("")) {
			throw new NegocioException("salvar_msg_erro");
		}
		fracaoRepository.salvar(fracao);
	}

	@Transactional
	public void excluir(Fracao fracao) {
		fracao = buscarPorId(fracao.getId());
		try {
			fracaoRepository.excluir(fracao);
		} catch (PersistenceException e) {
			throw new NegocioException("exclusao_negada");
		}
	}

	public Fracao buscarPorId(Long id) {
		return fracaoRepository.buscarPorId(id);
	}

	public List<Fracao> listarTodos() {
		return fracaoRepository.listarTodos();
	}

	public List<Fracao> listarFracaoPai(List<Fracao> fracoes) {
		return fracaoRepository.listarFracaoPai(fracoes);
	}

	public List<Fracao> raizes() {
		return fracaoRepository.raizes();
	}

}
