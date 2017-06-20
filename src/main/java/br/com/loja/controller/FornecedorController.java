package br.com.loja.controller;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.loja.entity.Fornecedor;
import br.com.loja.facades.FornecedorFacade;
import br.com.loja.util.Uteis;

@Named
@RequestScoped
public class FornecedorController implements Serializable {

	private static final long serialVersionUID = -6303875189544474632L;

	@Inject
	private FornecedorFacade fornecedorFacade;

	@Inject
	private Fornecedor fornecedor;

	private List<Fornecedor> listaFornecedores;

	@PostConstruct
	public void init() {
		listaFornecedores = fornecedorFacade.buscaTodos();
	}

	public void salvar() {
		try {
			fornecedorFacade.salvarNovoRegistro(fornecedor);		
			Uteis.Mensagem("Fornecedor gravado");
		} catch (Exception ex) {
			addMessage(getMessageFromI18N("msg.erro.salvar.fornecedor"), ex.getMessage());
		}
	}
	
	public void limparForm(String bean){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(bean);
    }

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getListaFornecedores() {
		return listaFornecedores;
	}

	public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
		this.listaFornecedores = listaFornecedores;
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
