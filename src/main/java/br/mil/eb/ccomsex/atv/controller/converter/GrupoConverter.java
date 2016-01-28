package br.mil.eb.ccomsex.atv.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.mil.eb.ccomsex.atv.model.entity.Grupo;
import br.mil.eb.ccomsex.atv.model.repository.GrupoRepository;

@FacesConverter(forClass = Grupo.class)
public class GrupoConverter implements Converter {

	@Inject
	private GrupoRepository grupoRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Grupo retorno = null;
		if (value != null && !value.equals("")) {
			retorno = grupoRepository.buscarPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Grupo retorno = (Grupo) value;
			return retorno.getId() == null ? null : retorno.getId().toString();
		}
		return null;
	}

}
