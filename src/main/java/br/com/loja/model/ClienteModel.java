package br.com.loja.model;

import java.io.Serializable;

public class ClienteModel implements Serializable {

	private static final long serialVersionUID = -8963963328559549517L;

	private Integer id;

	private String email;

	private String senha;
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
