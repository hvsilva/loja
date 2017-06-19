package br.com.loja.controller;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.loja.entity.Cliente;
import br.com.loja.entity.Funcionario;
import br.com.loja.facades.ClienteFacade;
import br.com.loja.facades.FuncionarioFacade;
import br.com.loja.model.ClienteModel;
import br.com.loja.model.FuncionarioModel;
import br.com.loja.util.Uteis;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioModel funcionarioModel;

	@Inject
	private ClienteModel clienteModel;

	@Inject
	private FuncionarioFacade funcionarioFacade;

	@Inject
	private ClienteFacade clienteFacade;

	@Inject
	private Funcionario funcionario;

	@Inject
	private Cliente cliente;

	public String efetuarLogin() {
		if (StringUtils.isEmpty(funcionarioModel.getLogin()) || StringUtils.isBlank(funcionarioModel.getLogin())) {
			Uteis.Mensagem("Favor informar o login!");
			return null;
		} else if (StringUtils.isEmpty(funcionarioModel.getSenha())
				|| StringUtils.isBlank(funcionarioModel.getSenha())) {
			Uteis.Mensagem("Favor informara senha!");
			return null;
		} else {
			funcionario = funcionarioFacade.validaFuncionario(funcionarioModel);
			if (funcionario != null) {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.getExternalContext().getSessionMap().put("funcionarioAutenticado", funcionario);
				return "sistema/home?faces-redirect=true";

			} else {
				//Uteis.MensagemErroLogin("msg.erro.efetuar.login");
				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");		
				return null;
			}
		}
	}

	public String efetuarLoginGuest() {
		if (StringUtils.isEmpty(clienteModel.getEmail()) || StringUtils.isBlank(clienteModel.getEmail())) {
			Uteis.Mensagem("Favor informar o login!");
			return null;
		} else if (StringUtils.isEmpty(clienteModel.getSenha()) || StringUtils.isBlank(clienteModel.getSenha())) {
			Uteis.Mensagem("Favor informara senha!");
			return null;
		} else {			
			cliente = clienteFacade.validaCliente(clienteModel);
			if (cliente != null) {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.getExternalContext().getSessionMap().put("clienteAutenticado", cliente);
				System.out.println("Usuario Logado " + cliente.getNome());
				return "index?faces-redirect=true";

			} else {
				//Uteis.MensagemErroLogin("msg.erro.efetuar.login");
				//addMessage(getMessageFromI18N("msg.erro.remover.mercadoria"));
				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}
	}

	public Funcionario GetFuncionarioSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (Funcionario) facesContext.getExternalContext().getSessionMap().get("funcionarioAutenticado");
	}

	public Cliente GetClienteSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (Cliente) facesContext.getExternalContext().getSessionMap().get("clienteAutenticado");
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index.xhtml";
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public FuncionarioModel getFuncionarioModel() {
		return funcionarioModel;
	}

	public void setFuncionarioModel(FuncionarioModel funcionarioModel) {
		this.funcionarioModel = funcionarioModel;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteModel getClienteModel() {
		return clienteModel;
	}

	public void setClienteModel(ClienteModel clienteModel) {
		this.clienteModel = clienteModel;
	}

	
	/**
	 * @param key
	 * @return Recupera a mensagem do arquivo properties <code>ResourceBundle</code>.
	 */
	private String getMessageFromI18N(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_labels", getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}
	
	/**
	 * Adiciona um mensagem no contexto do Faces (<code>FacesContext</code>).
	 * @param summary
	 * @param detail
	 */
	private void addMessage(String summary, String detail) {
		getCurrentInstance().addMessage(null, new FacesMessage(summary, summary.concat("<br/>").concat(detail)));
	}
	
}