package br.com.loja.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the funcionario database table.
 * 
 */
@Entity
@Table(name = "funcionario")
@NamedQueries({ @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f"),
		@NamedQuery(name = "Funcionario.findByFuncionario", query = "SELECT f FROM Funcionario f WHERE f.login = :login AND f.senha = :senha") })
public class Funcionario implements Serializable, AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String cpf;

	private String email;

	private String endereco;

	private String login;

	private String nome;

	private String senha;

	private String telefone;

	public Funcionario() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}