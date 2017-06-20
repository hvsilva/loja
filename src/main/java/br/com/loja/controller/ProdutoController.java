package br.com.loja.controller;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.loja.entity.Fornecedor;
import br.com.loja.entity.Produto;
import br.com.loja.facades.ProdutoFacade;
import br.com.loja.util.Uteis;

@Named
@SessionScoped
public class ProdutoController implements Serializable {

	private static final Logger LOG = LoggerFactory.getLogger(ProdutoController.class.getName());

	private static final long serialVersionUID = -1765867037304340862L;

	@Inject
	private FornecedorController fornecedorController;

	@Inject
	private ProdutoFacade produtoFacade;

	@Inject
	private Produto produto;

	private List<Produto> produtos;

	@Inject
	private Fornecedor fornecedor;

	public void salvar() {
		try {
			produtoFacade.salvarNovoRegistro(produto);
			Uteis.Mensagem("Produto gravado!");
		} catch (Exception ex) {
			addMessage(getMessageFromI18N("msg.erro.salvar.fornecedor"), ex.getMessage());
		}
	}

	public void produtoSelecionado(AjaxBehaviorEvent event) {
		System.out.println("produto codigo: " + produto.getId());
		System.out.println("produto nome  : " + produto.getNome());

		try {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/produto.xhtml");

		} catch (IOException e) {
			LOG.error("falha no redirecionamento para a url {} erro: {}", e.getMessage());
		}

	}

	public List<Produto> completeProduto() {
		produtos = produtoFacade.buscaTodos();
		return produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public FornecedorController getFornecedorController() {
		return fornecedorController;
	}

	public void setFornecedorController(FornecedorController fornecedorController) {
		this.fornecedorController = fornecedorController;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
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
