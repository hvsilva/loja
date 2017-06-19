package br.com.loja.model;

public class FornecedorModel {

	private String cnpj;
	private String endereco;
	private String nome;

	private FuncionarioModel funcionarioModel;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public FuncionarioModel getFuncionarioModel() {
		return funcionarioModel;
	}

	public void setFuncionarioModel(FuncionarioModel funcionarioModel) {
		this.funcionarioModel = funcionarioModel;
	}

}
