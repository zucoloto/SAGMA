package br.mil.eb.ccomsex.atv.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.mil.eb.ccomsex.atv.model.entity.Configurar;
import br.mil.eb.ccomsex.atv.model.repository.ConfigurarRepository;

@FacesConverter(forClass = Configurar.class)
public class ConfigurarConverter implements Converter {

	@Inject
	private ConfigurarRepository configurarRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Configurar retorno = null;
		if (value != null && !value.equals("")) {
			retorno = configurarRepository.buscarPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Configurar retorno = (Configurar) value;
			return retorno.getId() == null ? null : retorno.getId().toString();
		}
		return null;
	}
}
