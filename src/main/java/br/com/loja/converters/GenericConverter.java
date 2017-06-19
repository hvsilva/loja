package br.com.loja.converters;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.loja.entity.AbstractEntity;



@FacesConverter("converterEntidadeGenerica")
//@Named(value = "converterEntidadeGenerica")
public class GenericConverter implements Converter, Serializable {

	private static final long serialVersionUID = 7501663245262161687L;

	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	public String getAsString(FacesContext ctx, UIComponent component, Object value) {

		if (value != null && !"".equals(value)) {

			AbstractEntity entity = (AbstractEntity) value;

			// adiciona item como atributo do componente
			this.addAttribute(component, entity);

			Integer id = entity.getId();
			if (id != null) {
				return String.valueOf(id);
			}
		}

		return (String) value;
	}

	protected void addAttribute(UIComponent component, AbstractEntity o) {
		String key = o.getId().toString(); 
											
		this.getAttributesFrom(component).put(key, o);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}