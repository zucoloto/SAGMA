package br.mil.eb.ccomsex.atv.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.mil.eb.ccomsex.atv.model.entity.Atividade;
import br.mil.eb.ccomsex.atv.model.repository.AtividadeRepository;

@FacesConverter(forClass = Atividade.class)
public class AtividadeConverter implements Converter {

	@Inject
	private AtividadeRepository atividadeRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Atividade retorno = null;
		if (value != null && !value.equals("")) {
			retorno = atividadeRepository.buscarPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Atividade retorno = (Atividade) value;
			return retorno.getId() == null ? null : retorno.getId().toString();
		}
		return null;
	}
}
