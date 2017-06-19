package br.com.loja.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.loja.entity.Produto;

@FacesConverter("produtoConverter")
public class produtoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		 return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		 if(object != null) {
	            return String.valueOf(((Produto) object).getId());
	        }
	        else {
	            return null;
	        }
	}

	
	
	
	
}
