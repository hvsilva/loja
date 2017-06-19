package br.com.loja.converters;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

@FacesConverter("bigDecimalConverter")
public class BigDecimalConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if ((value != null) && (!StringUtils.isEmpty(value))) {
			value = value.replace(".", "").replace(",", ".");
			return new BigDecimal(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null || value.toString().isEmpty()) {
			return null;
		} else if (value.toString().contains(",")) {
			value = value.toString().replace(".", "").replace(",", ".");
		}
		String valor = new BigDecimal(value.toString()).setScale(2, RoundingMode.HALF_UP).toString().replaceAll("[^0-9]", "");
		StringBuilder stringBuilder = new StringBuilder(valor.toString());
		if (valor.toString().length() > 2) {
			stringBuilder.insert(valor.toString().length() - 2, ',');
		}
		if (valor.toString().length() > 5) {
			stringBuilder.insert(valor.toString().length() - 5, '.');
		}
		if (valor.toString().length() > 8) {
			stringBuilder.insert(valor.toString().length() - 8, '.');
		}
		if (valor.toString().length() > 11) {
			stringBuilder.insert(valor.toString().length() - 11, '.');
		}
		return stringBuilder.toString();
	}

}
