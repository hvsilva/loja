package br.com.loja.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

@FacesValidator("cnpjValidator")
public class CnpjValidator implements Validator {

	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
		if (!StringUtils.isEmpty((String) value)) {
			if (!CNPJ.validaCNPJ(String.valueOf(value))) {
				FacesMessage msg = new FacesMessage("Formato de CNPJ invalido");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
		}

	}

}
