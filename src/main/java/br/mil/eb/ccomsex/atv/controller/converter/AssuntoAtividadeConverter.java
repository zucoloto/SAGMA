package br.mil.eb.ccomsex.atv.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.mil.eb.ccomsex.atv.model.entity.AssuntoAtividade;
import br.mil.eb.ccomsex.atv.model.repository.AssuntoAtividadeRepository;

@FacesConverter(forClass = AssuntoAtividade.class)
public class AssuntoAtividadeConverter implements Converter {

	@Inject
	private AssuntoAtividadeRepository assuntoAtividadeRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		AssuntoAtividade retorno = null;
		if (value != null && !value.equals("")) {
			retorno = assuntoAtividadeRepository.buscarPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			AssuntoAtividade retorno = (AssuntoAtividade) value;
			return retorno.getId() == null ? null : retorno.getId().toString();
		}
		return null;
	}

}
