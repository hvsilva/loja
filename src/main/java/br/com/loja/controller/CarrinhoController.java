package br.com.loja.controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Endereco;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoletoHTML;
import br.com.loja.entity.Cliente;
import br.com.loja.entity.ItemPedido;
import br.com.loja.entity.Pedido;
import br.com.loja.entity.Produto;
import br.com.loja.facades.PedidoFacade;

@Named
@SessionScoped
public class CarrinhoController implements Serializable {

	private static final Logger LOG = LoggerFactory.getLogger(CarrinhoController.class.getName());

	private static final long serialVersionUID = 5621164283664406221L;

	private List<ItemPedido> itemPedidos = new ArrayList<>();

	private Double total = 0.0;

	@Inject
	private Produto produto;

	@Inject
	private PedidoFacade pedidoFacade;

	@Inject
	private Pedido pedido;

	@Inject
	private Cliente cliente;

	@Inject
	private LoginController loginController;

	@Inject
	private ItemPedido itemPedido;

	@Inject
	private ProdutoController produtoController;

	public String atualizaValorProduto() {
		//this.total += produtoController.getProduto().getValor() * itemPedido.getQuantidade();
		total = 0.0;
		total += itemPedido.getProduto().getValor() * itemPedido.getQuantidade();
		itemPedido.setPrecoTotal(total);
		System.out.println("Valor total " + itemPedido.getPrecoTotal());
		return "carrinho.xhtml";
	}

	public String adicionarProduto() {
		this.itemPedido = new ItemPedido();
		this.itemPedido.setProduto(produtoController.getProduto());
		this.itemPedido.setPrecoTotal(produtoController.getProduto().getValor());
		itemPedidos.add(itemPedido);		
		return "carrinho.xhtml";
	}
	
	public String addProduto(){		
		return "index.xhtml";
	}

	public void excluirProduto(ItemPedido itemPedido) {
		itemPedidos.remove(itemPedido);
	}

	public void finalizarCompra() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		cliente = loginController.GetClienteSession();

		try {

			if (cliente != null) {
				pedido.setData(new Timestamp(new Date().getTime()));

				pedido.setCliente(cliente);
				pedido.setTotal(itemPedido.getPrecoTotal());
				
				itemPedido.setPedido(pedido);
				itemPedido.setPrecoUnitario(itemPedido.getProduto().getValor());
				pedido.addItemPedido(itemPedido);

				pedidoFacade.salvarNovoRegistro(pedido);

				ec.redirect(ec.getRequestContextPath() + "/itens_comprados.xhtml");

			} else {
				System.out.println("Cliente não Cadastrado");
				itemPedidos.remove(itemPedido);
				ec.redirect(ec.getRequestContextPath() + "/guest.xhtml");
			}

		} catch (IOException e) {
			LOG.error("falha no redirecionamento para a url {} erro: {}", e.getMessage());
		}

	}

	public void geraBoleto() throws IOException {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

		Datas datas = Datas.novasDatas()
				.comDocumento(1, 5, 2008)
				.comProcessamento(1, 5, 2008).comVencimento(2, 5,2008);
				
		Endereco enderecoBeneficiario = Endereco.novoEndereco()
				.comLogradouro("Av das Empresas, 555")
				.comBairro("Bairro Grande")
				.comCep("01234-555")
				.comCidade("São Paulo")
				.comUf("SP");

		// Quem emite o boleto
		Beneficiario beneficiario = Beneficiario.novoBeneficiario()
				.comNomeBeneficiario("Inove Móveis")
				.comAgencia("1824")
				.comDigitoAgencia("4")
				.comCodigoBeneficiario("76000")
				.comDigitoCodigoBeneficiario("5")
				.comNumeroConvenio("1207113")
				.comCarteira("18")
				.comEndereco(enderecoBeneficiario)
				.comNossoNumero("9000206");

		Endereco enderecoPagador = Endereco.novoEndereco()
				.comLogradouro("Av dos testes, 111 apto 333")
				.comBairro("Bairro Teste")
				.comCep("01234-111")
				.comCidade("São Paulo")
				.comUf("SP");

		// Quem paga o boleto
		Pagador pagador = Pagador.novoPagador()
				.comNome(cliente.getNome())
				.comDocumento(cliente.getCpf())
				.comEndereco(enderecoPagador);

		Banco banco = new BancoDoBrasil();

		Boleto boleto = Boleto.novoBoleto().comBanco(banco)
				.comDatas(datas).comBeneficiario(beneficiario)
				.comPagador(pagador)
				.comValorBoleto(itemPedido.getPrecoTotal())
				.comNumeroDoDocumento("1234")
				.comInstrucoes("instrucao 1", "instrucao 2", "instrucao 3", "instrucao 4", "instrucao 5")
				.comLocaisDePagamento("local 1", "local 2");

//		GeradorDeBoletoHTML gerador = new GeradorDeBoletoHTML(boleto);
//		gerador.geraHTML(response.getWriter(), request);

		 GeradorDeBoletoHTML gerador = new GeradorDeBoletoHTML(boleto);
		 gerador.geraPDF(response.getOutputStream());
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ItemPedido getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}

	public List<ItemPedido> getItemPedidos() {
		return itemPedidos;
	}

	public void setItemPedidos(List<ItemPedido> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}

}
