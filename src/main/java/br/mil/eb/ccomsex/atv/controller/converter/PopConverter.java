package br.mil.eb.ccomsex.atv.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.mil.eb.ccomsex.atv.model.entity.Pop;
import br.mil.eb.ccomsex.atv.model.repository.PopRepository;

@FacesConverter(forClass = Pop.class)
public class PopConverter implements Converter {

	@Inject
	private PopRepository popRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pop retorno = null;
		if (value != null && !value.equals("")) {
			retorno = popRepository.buscarPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pop retorno = (Pop) value;
			return retorno.getId() == null ? null : retorno.getId().toString();
		}
		return null;
	}

}
