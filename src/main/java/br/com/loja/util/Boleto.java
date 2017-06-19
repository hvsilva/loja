package br.com.loja.util;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Endereco;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;

public class Boleto {
	
	  Datas datas = Datas.novasDatas()
              .comDocumento(1, 5, 2008)
              .comProcessamento(1, 5, 2008)
              .comVencimento(2, 5, 2008); 
	  

      Endereco enderecoBeneficiario = Endereco.novoEndereco()
      		.comLogradouro("Av das Empresas, 555")  
      		.comBairro("Bairro Grande")  
      		.comCep("01234-555")  
      		.comCidade("São Paulo")  
      		.comUf("SP");  
      
    //Quem emite o boleto
      Beneficiario beneficiario = Beneficiario.novoBeneficiario()  
              .comNomeBeneficiario("Fulano de Tal")  
              .comAgencia("1824").comDigitoAgencia("4")  
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
      
      //Quem paga o boleto
      Pagador pagador = Pagador.novoPagador()  
              .comNome("Fulano da Silva")  
              .comDocumento("111.222.333-12")
              .comEndereco(enderecoPagador);

      Banco banco = new BancoDoBrasil();  

     
  

}
