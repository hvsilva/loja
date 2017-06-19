package br.com.loja.controller;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.loja.entity.Cliente;
import br.com.loja.facades.ClienteFacade;
import br.com.loja.util.Uteis;

@Named
@SessionScoped
public class ClienteController implements Serializable {

	private static final long serialVersionUID = -1765867037304340862L;

	@Inject
	private ClienteFacade clienteFacade;

	@Inject
	private Cliente cliente;

	public void salvar() {
		try {
			clienteFacade.salvarNovoRegistro(cliente);
			Uteis.MensagemInfo("Cadastro efetuado com sucesso");
		} catch (Exception ex) {
			addMessage(getMessageFromI18N("msg.erro.salvar.cliente"), ex.getMessage());
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @param key
	 * @return Recupera a mensagem do arquivo properties
	 * 
	 */
	private String getMessageFromI18N(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_labels",
				getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}

	/**
	 * Adiciona um mensagem no contexto do Faces
	 * 
	 * @param summary
	 * @param detail
	 */
	private void addMessage(String summary, String detail) {
		getCurrentInstance().addMessage(null, new FacesMessage(summary, summary.concat("<br/>").concat(detail)));
	}

}
